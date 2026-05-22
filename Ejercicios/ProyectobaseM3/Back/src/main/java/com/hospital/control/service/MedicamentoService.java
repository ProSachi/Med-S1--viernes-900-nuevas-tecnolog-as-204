
package com.hospital.control.service;

import lombok.NonNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hospital.control.dto.MedicamentoRequestDTO;
import com.hospital.control.dto.MedicamentoResponseDTO;
import com.hospital.control.exception.RecursoNoEncontradoException;
import com.hospital.control.mapper.MedicamentoMapper;
import com.hospital.control.model.Medicamento;
import com.hospital.control.repository.MedicamentoRepository;

import lombok.RequiredArgsConstructor;

// La anotación @Service indica que esta clase es un componente de servicio.
// En ella implementamos la lógica de negocio para la gestión de medicamentos.
@Service
// @RequiredArgsConstructor es de Lombok y genera automáticamente un constructor
// con los atributos finales, lo que permite inyectar las dependencias.
@RequiredArgsConstructor
public class MedicamentoService {

    // Inyección de dependencia: El mapper que convierte entre entidades y DTOs.
    // Se usa para transformar datos de forma segura.
    private final MedicamentoMapper medicamentoMapper;

    // Inyección de dependencia: El repositorio que accede a la base de datos.
    // Aquí realizamos todas las operaciones CRUD.
    private final MedicamentoRepository medicamentoRepository;

    /**
     * Método para registrar (crear) un nuevo medicamento en la base de datos.
     * 
     * @param dto - Datos del medicamento recibidos desde el cliente
     * @return - DTO con la información del medicamento creado
     */
    public MedicamentoResponseDTO registrar(@NonNull MedicamentoRequestDTO dto) {
        // Validamos los datos del DTO antes de procesar.
        validarDatosMedicamento(dto);

        // El mapper convierte el DTO de solicitud a una entidad Medicamento.
        // Esto permite que el mapper maneje la traducción automática entre objetos.
        Medicamento medicamento = medicamentoMapper.toMedicamento(dto);

        // Guardamos la entidad en la base de datos a través del repositorio.
        // save() devuelve la entidad guardada con el ID generado.
        @SuppressWarnings("null")
        Medicamento medicamentoGuardado = medicamentoRepository.save(medicamento);

        // Convertimos la entidad guardada a un DTO de respuesta.
        // Esto asegura que solo exponemos los datos que el cliente necesita saber.
        return medicamentoMapper.toMedicamentoResponseDTO(medicamentoGuardado);
    }

    /**
     * Método para obtener la lista de todos los medicamentos.
     * 
     * @return - Lista de DTOs con la información de los medicamentos
     */
    public List<MedicamentoResponseDTO> obtenerTodos() {
        // findAll() obtiene todas las entidades Medicamento de la base de datos.
        return medicamentoRepository.findAll()
                // Convertimos la lista a un Stream para procesarla de forma funcional.
                .stream()
                // map() transforma cada entidad usando el método del mapper.
                .map(medicamentoMapper::toMedicamentoResponseDTO)
                // collect() recolecta los resultados en una nueva Lista.
                .collect(Collectors.toList());
    }

    /**
     * Método para obtener un medicamento específico por su ID.
     * 
     * @param id - Identificador único del medicamento
     * @return - DTO del medicamento encontrado
     */
    public MedicamentoResponseDTO obtenerPorId(@NonNull Integer id) {
        // Buscamos el medicamento en la base de datos.
        Medicamento medicamento = medicamentoRepository.findById(id)
                // Si no existe, lanzamos una excepción personalizada.
                .orElseThrow(() -> new RecursoNoEncontradoException("Medicamento no encontrado con ID: " + id));

        // Convertimos la entidad encontrada a un DTO de respuesta.
        return medicamentoMapper.toMedicamentoResponseDTO(medicamento);
    }

    /**
     * Método para actualizar un medicamento existente.
     * 
     * @param id  - Identificador único del medicamento
     * @param dto - Nuevos datos del medicamento
     * @return - DTO con la información del medicamento actualizado
     */
    @SuppressWarnings("null")
    public MedicamentoResponseDTO actualizar(@NonNull Integer id, @NonNull MedicamentoRequestDTO dto) {
        // Validamos que el ID sea válido (no nulo y mayor que 0).
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del medicamento debe ser mayor que 0.");
        }

        // Validamos que el DTO tenga todos los datos requeridos.
        validarDatosMedicamento(dto);

        // Buscamos el medicamento existente en la base de datos.
        Medicamento medicamentoExistente = medicamentoRepository.findById(id)
                // Si no existe, lanzamos una excepción personalizada.
                .orElseThrow(() -> new RecursoNoEncontradoException("Medicamento no encontrado con ID: " + id));

        // Utilizamos el mapper para actualizar la entidad existente con los datos del
        // DTO.
        // @MappingTarget se encarga de fusionar los datos sin crear una nueva
        // instancia.
        medicamentoMapper.actualizarEntidadDesdeDto(dto, medicamentoExistente);

        // Guardamos la entidad actualizada en la base de datos.
        Medicamento medicamentoActualizado = medicamentoRepository.save(medicamentoExistente);

        // Convertimos la entidad actualizada a un DTO de respuesta.
        return medicamentoMapper.toMedicamentoResponseDTO(medicamentoActualizado);
    }

    /**
     * Método para eliminar un medicamento por su ID.
     * 
     * @param id - Identificador único del medicamento a eliminar
     */
    public void eliminar(@NonNull Integer id) {
        // Verificamos si el medicamento existe antes de intentar eliminarlo.
        if (!medicamentoRepository.existsById(id)) {
            // Si no existe, lanzamos una excepción con un mensaje descriptivo.
            throw new RecursoNoEncontradoException("No se puede eliminar, medicamento no encontrado con ID: " + id);
        }

        // Si existe, procedemos a eliminarlo de la base de datos.
        medicamentoRepository.deleteById(id);
    }

    /**
     * Método privado para validar los datos de un medicamento.
     * Se utiliza en los métodos de creación y actualización.
     * 
     * @param dto - Datos del medicamento a validar
     * @throws IllegalArgumentException - Si algún dato no es válido
     */
    private void validarDatosMedicamento(MedicamentoRequestDTO dto) {
        // Verificamos que el DTO no sea nulo.
        if (dto == null) {
            throw new IllegalArgumentException("Los datos del medicamento son obligatorios.");
        }

        // Verificamos que el nombre del medicamento no sea nulo ni esté vacío.
        if (dto.getNombreMedicamento() == null || dto.getNombreMedicamento().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del medicamento es obligatorio.");
        }

        // Verificamos que la descripción no sea nula ni esté vacía.
        if (dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria.");
        }
    }
}
