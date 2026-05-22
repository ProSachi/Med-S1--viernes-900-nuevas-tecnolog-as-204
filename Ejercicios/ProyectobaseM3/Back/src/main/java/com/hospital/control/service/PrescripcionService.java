package com.hospital.control.service;

import lombok.NonNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hospital.control.dto.PrescripcionRequestDTO;
import com.hospital.control.dto.PrescripcionResponseDTO;
import com.hospital.control.exception.RecursoNoEncontradoException;
import com.hospital.control.mapper.PrescripcionMapper;
import com.hospital.control.model.Medicamento;
import com.hospital.control.model.Medico;
import com.hospital.control.model.Paciente;
import com.hospital.control.model.Prescripcion;
import com.hospital.control.repository.MedicamentoRepository;
import com.hospital.control.repository.MedicoRepository;
import com.hospital.control.repository.PacienteRepository;
import com.hospital.control.repository.PrescripcionRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio para gestionar las prescripciones médicas.
 * Permite que un médico relacione medicamentos con pacientes.
 */
@Service
@RequiredArgsConstructor
public class PrescripcionService {

    private final PrescripcionMapper prescripcionMapper;
    private final PrescripcionRepository prescripcionRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    /**
     * Método para crear una nueva prescripción.
     * Un médico prescribe un medicamento a un paciente.
     * 
     * @param dto - Datos de la prescripción
     * @return - DTO con la información de la prescripción creada
     */
    public PrescripcionResponseDTO registrar(@NonNull PrescripcionRequestDTO dto) {
        validarDatosPrescripcion(dto);

        // Verificar que el medicamento existe
        Medicamento medicamento = medicamentoRepository.findById(dto.getMedicamentoId())
            .orElseThrow(() -> new RecursoNoEncontradoException(
                "Medicamento no encontrado con ID: " + dto.getMedicamentoId()));

        // Verificar que el paciente existe
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new RecursoNoEncontradoException(
                "Paciente no encontrado con ID: " + dto.getPacienteId()));

        // Verificar que el médico existe
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new RecursoNoEncontradoException(
                "Médico no encontrado con ID: " + dto.getMedicoId()));

        // Crear la prescripción
        Prescripcion prescripcion = new Prescripcion();
        prescripcion.setMedicamento(medicamento);
        prescripcion.setPaciente(paciente);
        prescripcion.setMedico(medico);
        prescripcion.setDosis(dto.getDosis());
        prescripcion.setFrecuencia(dto.getFrecuencia());
        prescripcion.setDuracion(dto.getDuracion());
        prescripcion.setObservaciones(dto.getObservaciones());
        prescripcion.setFechaPrescripcion(LocalDateTime.now());

        @SuppressWarnings("null")
        Prescripcion prescripcionGuardada = prescripcionRepository.save(prescripcion);

        return prescripcionMapper.toPrescripcionResponseDTO(prescripcionGuardada);
    }

    /**
     * Método para obtener todas las prescripciones.
     * 
     * @return - Lista de DTOs con las prescripciones
     */
    public List<PrescripcionResponseDTO> obtenerTodas() {
        return prescripcionRepository.findAll()
            .stream()
            .map(prescripcionMapper::toPrescripcionResponseDTO)
            .collect(Collectors.toList());
    }

    /**
     * Método para obtener una prescripción por su ID.
     * 
     * @param id - Identificador de la prescripción
     * @return - DTO de la prescripción
     */
    public PrescripcionResponseDTO obtenerPorId(@NonNull Integer id) {
        Prescripcion prescripcion = prescripcionRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException(
                "Prescripción no encontrada con ID: " + id));

        return prescripcionMapper.toPrescripcionResponseDTO(prescripcion);
    }

    /**
     * Método para obtener las prescripciones de un paciente específico.
     * 
     * @param pacienteId - ID del paciente
     * @return - Lista de prescripciones del paciente
     */
    public List<PrescripcionResponseDTO> obtenerPorPaciente(@NonNull Integer pacienteId) {
        // Verificar que el paciente existe
        if (!pacienteRepository.existsById(pacienteId)) {
            throw new RecursoNoEncontradoException("Paciente no encontrado con ID: " + pacienteId);
        }

        return prescripcionRepository.findByPacienteId(pacienteId)
            .stream()
            .map(prescripcionMapper::toPrescripcionResponseDTO)
            .collect(Collectors.toList());
    }

    /**
     * Método para obtener las prescripciones realizadas por un médico.
     * 
     * @param medicoId - ID del médico
     * @return - Lista de prescripciones del médico
     */
    public List<PrescripcionResponseDTO> obtenerPorMedico(@NonNull Integer medicoId) {
        // Verificar que el médico existe
        if (!medicoRepository.existsById(medicoId)) {
            throw new RecursoNoEncontradoException("Médico no encontrado con ID: " + medicoId);
        }

        return prescripcionRepository.findByMedicoId(medicoId)
            .stream()
            .map(prescripcionMapper::toPrescripcionResponseDTO)
            .collect(Collectors.toList());
    }

    /**
     * Método para obtener las prescripciones de un medicamento específico.
     * 
     * @param medicamentoId - ID del medicamento
     * @return - Lista de prescripciones del medicamento
     */
    public List<PrescripcionResponseDTO> obtenerPorMedicamento(@NonNull Integer medicamentoId) {
        // Verificar que el medicamento existe
        if (!medicamentoRepository.existsById(medicamentoId)) {
            throw new RecursoNoEncontradoException(
                "Medicamento no encontrado con ID: " + medicamentoId);
        }

        return prescripcionRepository.findByMedicamentoId(medicamentoId)
            .stream()
            .map(prescripcionMapper::toPrescripcionResponseDTO)
            .collect(Collectors.toList());
    }

    /**
     * Método para eliminar una prescripción.
     * 
     * @param id - ID de la prescripción a eliminar
     */
    public void eliminar(@NonNull Integer id) {
        if (!prescripcionRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                "No se puede eliminar, prescripción no encontrada con ID: " + id);
        }

        prescripcionRepository.deleteById(id);
    }

    /**
     * Método privado para validar los datos de una prescripción.
     * 
     * @param dto - Datos de la prescripción a validar
     */
    private void validarDatosPrescripcion(PrescripcionRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Los datos de la prescripción no pueden ser nulos.");
        }

        if (dto.getMedicamentoId() == null || dto.getMedicamentoId() <= 0) {
            throw new IllegalArgumentException("El ID del medicamento es requerido y debe ser mayor que 0.");
        }

        if (dto.getPacienteId() == null || dto.getPacienteId() <= 0) {
            throw new IllegalArgumentException("El ID del paciente es requerido y debe ser mayor que 0.");
        }

        if (dto.getMedicoId() == null || dto.getMedicoId() <= 0) {
            throw new IllegalArgumentException("El ID del médico es requerido y debe ser mayor que 0.");
        }

        if (dto.getDosis() == null || dto.getDosis().trim().isEmpty()) {
            throw new IllegalArgumentException("La dosis es requerida.");
        }

        if (dto.getFrecuencia() == null || dto.getFrecuencia().trim().isEmpty()) {
            throw new IllegalArgumentException("La frecuencia es requerida.");
        }
    }
}
