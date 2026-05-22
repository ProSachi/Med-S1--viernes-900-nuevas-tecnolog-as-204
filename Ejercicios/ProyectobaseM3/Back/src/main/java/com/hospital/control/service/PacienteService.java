
package com.hospital.control.service;

import lombok.NonNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hospital.control.dto.PacienteRequestDTO;
import com.hospital.control.dto.PacienteResponseDTO;
import com.hospital.control.exception.RecursoNoEncontradoException;
import com.hospital.control.mapper.PacienteMapper;
import com.hospital.control.model.Medico;
import com.hospital.control.model.Paciente;
import com.hospital.control.repository.MedicoRepository;
import com.hospital.control.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

// La anotación @Service indica que esta clase es un componente de servicio.
// En ella implementamos la lógica de negocio para la gestión de pacientes.
@Service
// @RequiredArgsConstructor es de Lombok y genera automáticamente un constructor
// con los atributos finales, lo que permite inyectar las dependencias.
@RequiredArgsConstructor
public class PacienteService {

    // Inyección de dependencia: El mapper que convierte entre entidades y DTOs.
    // Se usa para transformar datos de forma segura.
    private final PacienteMapper pacienteMapper;

    // Inyección de dependencia: El repositorio que accede a la base de datos para pacientes.
    // Aquí realizamos todas las operaciones CRUD de pacientes.
    private final PacienteRepository pacienteRepository;

    // Inyección de dependencia: El repositorio para acceder a los médicos.
    // Es necesario porque un paciente se relaciona con un médico.
    private final MedicoRepository medicoRepository;

    /**
     * Método para registrar (crear) un nuevo paciente en la base de datos.
     * @param dto - Datos del paciente recibidos desde el cliente
     * @return - DTO con la información del paciente creado
     */
    public PacienteResponseDTO registrar(@NonNull PacienteRequestDTO dto) {
        // Validamos los datos del DTO antes de procesar.
        validarDatosPaciente(dto);

        // Validamos explícitamente el ID del médico para null safety
        Integer medicoId = dto.getMedicoId();
        if (medicoId == null || medicoId <= 0) {
            throw new IllegalArgumentException("El ID del médico debe ser válido.");
        }
        // Buscamos el médico asociado al paciente usando el ID del DTO.
        Medico medico = medicoRepository.findById(medicoId)
            // Si el médico no existe, lanzamos una excepción personalizada.
            .orElseThrow(() -> new RecursoNoEncontradoException("Médico no encontrado con ID: " + medicoId));

        // El mapper convierte el DTO de solicitud a una entidad Paciente.
        // Se pasa también el médico para establecer la relación.
        Paciente paciente = pacienteMapper.toPaciente(dto, medico);

        // Guardamos la entidad en la base de datos a través del repositorio.
        // save() devuelve la entidad guardada con el ID generado.
        @SuppressWarnings("null")
        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        // Convertimos la entidad guardada a un DTO de respuesta.
        // Esto asegura que solo exponemos los datos que el cliente necesita saber.
        return pacienteMapper.toPacienteResponseDTO(pacienteGuardado);
    }

    /**
     * Método para obtener la lista de todos los pacientes.
     * @return - Lista de DTOs con la información de los pacientes
     */
    public List<PacienteResponseDTO> obtenerTodos() {
        // findAll() obtiene todas las entidades Paciente de la base de datos.
        return pacienteRepository.findAll()
                // Convertimos la lista a un Stream para procesarla de forma funcional.
                .stream()
                // map() transforma cada entidad usando el método del mapper.
                .map(pacienteMapper::toPacienteResponseDTO)
                // collect() recolecta los resultados en una nueva Lista.
                .collect(Collectors.toList());
    }

    /**
     * Método para obtener un paciente específico por su ID.
     * @param id - Identificador único del paciente
     * @return - DTO del paciente encontrado
     */
    public PacienteResponseDTO obtenerPorId(@NonNull Integer id) {
        // Buscamos el paciente en la base de datos.
        Paciente paciente = pacienteRepository.findById(id)
                // Si no existe, lanzamos una excepción personalizada.
                .orElseThrow(() -> new RecursoNoEncontradoException("Paciente no encontrado con ID: " + id));

        // Convertimos la entidad encontrada a un DTO de respuesta.
        return pacienteMapper.toPacienteResponseDTO(paciente);
    }

    /**
     * Método para actualizar un paciente existente.
     * @param id - Identificador único del paciente
     * @param dto - Nuevos datos del paciente
     * @return - DTO con la información del paciente actualizado
     */
    @SuppressWarnings("null")
    public PacienteResponseDTO actualizar(@NonNull Integer id, @NonNull PacienteRequestDTO dto) {
        // Validamos que el ID sea válido (no nulo y mayor que 0).
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del paciente debe ser mayor que 0.");
        }

        // Validamos que el DTO tenga todos los datos requeridos.
        validarDatosPaciente(dto);

        // Buscamos el paciente existente en la base de datos.
        Paciente pacienteExistente = pacienteRepository.findById(id)
                // Si no existe, lanzamos una excepción personalizada.
                .orElseThrow(() -> new RecursoNoEncontradoException("Paciente no encontrado con ID: " + id));

        // Utilizamos el mapper para actualizar la entidad existente con los datos del DTO.
        // @MappingTarget se encarga de fusionar los datos sin crear una nueva instancia.
        pacienteMapper.actualizarEntidadDesdeDto(dto, pacienteExistente);

        // Guardamos la entidad actualizada en la base de datos.
        Paciente pacienteActualizado = pacienteRepository.save(pacienteExistente);

        // Convertimos la entidad actualizada a un DTO de respuesta.
        return pacienteMapper.toPacienteResponseDTO(pacienteActualizado);
    }

    /**
     * Método para eliminar un paciente por su ID.
     * @param id - Identificador único del paciente a eliminar
     */
    public void eliminar(@NonNull Integer id) {
        // Verificamos si el paciente existe antes de intentar eliminarlo.
        if (!pacienteRepository.existsById(id)) {
            // Si no existe, lanzamos una excepción con un mensaje descriptivo.
            throw new RecursoNoEncontradoException("No se puede eliminar, paciente no encontrado con ID: " + id);
        }

        // Si existe, procedemos a eliminarlo de la base de datos.
        pacienteRepository.deleteById(id);
    }

    /**
     * Método privado para validar los datos de un paciente.
     * Se utiliza en los métodos de creación y actualización.
     * @param dto - Datos del paciente a validar
     * @throws IllegalArgumentException - Si algún dato no es válido
     */
    private void validarDatosPaciente(PacienteRequestDTO dto) {
        // Verificamos que el DTO no sea nulo.
        if (dto == null) {
            throw new IllegalArgumentException("Los datos del paciente son obligatorios.");
        }

        // Verificamos que el nombre no sea nulo ni esté vacío.
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        // Verificamos que el apellido no sea nulo ni esté vacío.
        if (dto.getApellido() == null || dto.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }

        // Verificamos que el ID del médico no sea nulo ni sea menor o igual a 0.
        if (dto.getMedicoId() == null || dto.getMedicoId() <= 0) {
            throw new IllegalArgumentException("El ID del médico debe ser válido.");
        }
    }
}
