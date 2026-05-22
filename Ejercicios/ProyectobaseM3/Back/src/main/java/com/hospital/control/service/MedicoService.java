
package com.hospital.control.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import com.hospital.control.dto.MedicoRequestDTO;
import com.hospital.control.dto.MedicoResponseDTO;
import com.hospital.control.exception.RecursoNoEncontradoException;
import com.hospital.control.mapper.MedicoMapper;
import com.hospital.control.model.Medico;
import com.hospital.control.repository.MedicoRepository;

import lombok.RequiredArgsConstructor;

// La anotación @Service indica que esta clase es un componente de servicio.
// En ella implementamos la lógica de negocio para la gestión de médicos.
@Service
// @RequiredArgsConstructor es de Lombok y genera automáticamente un constructor
// con los atributos finales, lo que permite inyectar las dependencias.
@RequiredArgsConstructor
public class MedicoService {

    // Inyección de dependencia: El mapper que convierte entre entidades y DTOs.
    // Se usa para transformar datos de forma segura.
    private final MedicoMapper medicoMapper;

    // Inyección de dependencia: El repositorio que accede a la base de datos.
    // Aquí realizamos todas las operaciones CRUD.
    private final MedicoRepository medicoRepository;

    /**
     * Método para registrar (crear) un nuevo médico en la base de datos.
     * @param dto - Datos del médico recibidos desde el cliente
     * @return - DTO con la información del médico creado
     */
    public MedicoResponseDTO registrarMedico(@NonNull MedicoRequestDTO dto) {
        // Validamos los datos del DTO antes de procesar.
        validarDatosMedico(dto);

        // El mapper convierte el DTO de solicitud a una entidad Medico.
        // Esto permite que el mapper maneje la traducción automática entre objetos.
        Medico medico = medicoMapper.toMedico(dto);

        // Guardamos la entidad en la base de datos a través del repositorio.
        // save() devuelve la entidad guardada con el ID generado.
        @SuppressWarnings("null")
        Medico medicoGuardado = medicoRepository.save(medico);

        // Convertimos la entidad guardada a un DTO de respuesta.
        // Esto asegura que solo exponemos los datos que el cliente necesita saber.
        return medicoMapper.toMedicoResponseDTO(medicoGuardado);
    }

    /**
     * Método para obtener la lista de todos los médicos.
     * @return - Lista de DTOs con la información de los médicos
     */
    public List<MedicoResponseDTO> obtenerTodos() {
        // findAll() obtiene todas las entidades Medico de la base de datos.
        return medicoRepository.findAll()
                // Convertimos la lista a un Stream para procesarla de forma funcional.
                .stream()
                // map() transforma cada entidad usando el método del mapper.
                .map(medicoMapper::toMedicoResponseDTO)
                // collect() recolecta los resultados en una nueva Lista.
                .collect(Collectors.toList());
    }

    /**
     * Método para obtener un médico específico por su ID.
     * @param id - Identificador único del médico
     * @return - DTO del médico encontrado
     */
    public MedicoResponseDTO obtenerPorId(@NonNull Integer id) {
        // Buscamos el médico en la base de datos.
        Medico medico = medicoRepository.findById(id)
                // Si no existe, lanzamos una excepción personalizada.
                .orElseThrow(() -> new RecursoNoEncontradoException("Médico no encontrado con ID: " + id));

        // Convertimos la entidad encontrada a un DTO de respuesta.
        return medicoMapper.toMedicoResponseDTO(medico);
    }

    /**
     * Método para actualizar un médico existente.
     * @param id - Identificador único del médico
     * @param dto - Nuevos datos del médico
     * @return - DTO con la información del médico actualizado
     */
    @SuppressWarnings("null")
    public MedicoResponseDTO actualizar(@NonNull Integer id, @NonNull MedicoRequestDTO dto) {
        // Validamos que el ID sea válido (no nulo y mayor que 0).
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del médico debe ser mayor que 0.");
        }

        // Validamos que el DTO tenga todos los datos requeridos.
        validarDatosMedico(dto);

        // Buscamos el médico existente en la base de datos.
        Medico medicoExistente = medicoRepository.findById(id)
                // Si no existe, lanzamos una excepción personalizada.
                .orElseThrow(() -> new RecursoNoEncontradoException("Médico no encontrado con ID: " + id));

        // Utilizamos el mapper para actualizar la entidad existente con los datos del DTO.
        // @MappingTarget se encarga de fusionar los datos sin crear una nueva instancia.
        medicoMapper.actualizarEntidadDesdeDto(dto, medicoExistente);

        // Guardamos la entidad actualizada en la base de datos.
        Medico medicoActualizado = medicoRepository.save(medicoExistente);

        // Convertimos la entidad actualizada a un DTO de respuesta.
        return medicoMapper.toMedicoResponseDTO(medicoActualizado);
    }

    /**
     * Método para eliminar un médico por su ID.
     * @param id - Identificador único del médico a eliminar
     */
    public void eliminar(@NonNull Integer id) {
        // Verificamos si el médico existe antes de intentar eliminarlo.
        if (!medicoRepository.existsById(id)) {
            // Si no existe, lanzamos una excepción con un mensaje descriptivo.
            throw new RecursoNoEncontradoException("No se puede eliminar, médico no encontrado con ID: " + id);
        }

        // Si existe, procedemos a eliminarlo de la base de datos.
        medicoRepository.deleteById(id);
    }

    /**
     * Método privado para validar los datos de un médico.
     * Se utiliza en los métodos de creación y actualización.
     * @param dto - Datos del médico a validar
     * @throws IllegalArgumentException - Si algún dato no es válido
     */
    private void validarDatosMedico(MedicoRequestDTO dto) {
        // Verificamos que el DTO no sea nulo.
        if (dto == null) {
            throw new IllegalArgumentException("Los datos del médico son obligatorios.");
        }

        // Verificamos que el nombre no sea nulo ni esté vacío.
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        // Verificamos que el apellido no sea nulo ni esté vacío.
        if (dto.getApellido() == null || dto.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }

        // Verificamos que la gerencia no sea nula ni esté vacía.
        if (dto.getGerencia() == null || dto.getGerencia().trim().isEmpty()) {
            throw new IllegalArgumentException("La gerencia es obligatoria.");
        }
    }
}
