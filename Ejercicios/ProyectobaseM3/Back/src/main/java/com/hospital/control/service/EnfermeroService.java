package com.hospital.control.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import com.hospital.control.dto.EnfermerosRequestDTO;
import com.hospital.control.dto.EnfermerosResponseDTO;
import com.hospital.control.exception.RecursoNoEncontradoException;
import com.hospital.control.mapper.EnfermerosMapper;
import com.hospital.control.model.Enfemeros;
import com.hospital.control.repository.EnfermerosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnfermeroService {

    // Inyección de dependencia: Mapper externo para convertir entre entidad y DTO
    private final EnfermerosMapper enfermerosMapper;
    // Inyección de dependencia: Repositorio para acceso a datos
    private final EnfermerosRepository enfermerosRepository;

    /**
     * Método para registrar (crear) un nuevo enfermero en la base de datos.
     */
    public EnfermerosResponseDTO registrarEnfermero(@NonNull EnfermerosRequestDTO dto) {
        validarDatosEnfermero(dto);

        // Usamos el mapper externo para convertir el DTO a entidad
        Enfemeros enfermeros = enfermerosMapper.toEnfermeros(dto);

    
        // Guardamos la entidad en la base de datos
        @SuppressWarnings("null")
        Enfemeros enfermeroGuardado = enfermerosRepository.save(enfermeros);

        // Convertimos la entidad guardada a DTO usando el mapper externo
        return enfermerosMapper.toResponseDTO(enfermeroGuardado);
    }

    /**
     * Método para obtener la lista de todos los enfermeros.
     */
    public List<EnfermerosResponseDTO> obtenerEnfermeros() {
        // Usamos el mapper externo para convertir cada entidad a DTO
        return enfermerosRepository.findAll()
                .stream()
                .map(enfermerosMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Método para eliminar un enfermero existente por su ID.
     */
    public void eliminarEnfermero(@NonNull Integer id) {
        // Validar que el id no sea nulo ni menor o igual a cero
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del enfermero debe ser mayor que 0.");
        }
        if (!enfermerosRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. El enfermero no existe.");
        }
        enfermerosRepository.deleteById(id);
    }

    /**
     * Método para actualizar un enfermero existente.
     */
    @SuppressWarnings("null")
    public EnfermerosResponseDTO actualizarEnfermero(@NonNull Integer id, @NonNull EnfermerosRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del enfermero debe ser mayor que 0.");
        }
        validarDatosEnfermero(dto);
        // Buscamos el enfermero existente
        Enfemeros enfermeroExistente = enfermerosRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró enfermero con id: " + id));

        // Usamos el mapper externo para actualizar la entidad desde el DTO
        enfermerosMapper.actualizarEntidadDesdeDto(dto, enfermeroExistente);

        // Guardamos los cambios
        Enfemeros enfermeroActualizado = enfermerosRepository.save(enfermeroExistente);
        // Convertimos la entidad actualizada a DTO usando el mapper externo
        return enfermerosMapper.toResponseDTO(enfermeroActualizado);
    }

    // Eliminamos el método interno de conversión, ahora usamos el mapper externo
    /**
     * Valida que los datos del DTO sean correctos.
     * 
     * @param dto - Datos a validar
     */
    private void validarDatosEnfermero(EnfermerosRequestDTO dto) {
        // Verifica que el DTO no sea nulo
        if (dto == null) {
            throw new IllegalArgumentException("Los datos del enfermero son obligatorios.");
        }
        // Verifica que el nombre no sea nulo ni vacío
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        // Verifica que el apellido no sea nulo ni vacío
        if (dto.getApellido() == null || dto.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        if (dto.getEspecialidad() == null || dto.getEspecialidad().trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria.");
        }
    }

    /**
     * Método para obtener un enfermero por su ID.
     * Si el enfermero no existe, lanza una excepción personalizada.
     * 
     * @param id Identificador del enfermero a buscar
     * @return DTO de respuesta con los datos del enfermero
     * @throws RecursoNoEncontradoException si no existe el enfermero
     */
    public EnfermerosResponseDTO obtenerEnfermeroPorId(@NonNull Integer id) {
        // Validar que el id no sea nulo ni menor o igual a cero
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del enfermero debe ser mayor que 0.");
        }
        // Buscar el enfermero en la base de datos, orElseThrow garantiza que nunca será null
        Enfemeros enfermero = enfermerosRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró enfermero con id: " + id));
        
        // Convertir la entidad a DTO usando el mapper
        return enfermerosMapper.toResponseDTO(enfermero);
    }


    

}
