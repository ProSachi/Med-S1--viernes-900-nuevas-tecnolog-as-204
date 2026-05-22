package com.hospital.control.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.control.dto.PrescripcionRequestDTO;
import com.hospital.control.dto.PrescripcionResponseDTO;
import com.hospital.control.service.PrescripcionService;

import lombok.RequiredArgsConstructor;

/**
 * Controlador REST para gestionar las prescripciones médicas.
 * Permite que un médico prescriba medicamentos a pacientes.
 */
@RestController
@RequestMapping("/api/prescripciones")
@RequiredArgsConstructor
public class PrescripcionController {

    private final PrescripcionService prescripcionService;

    /**
     * Endpoint POST para crear una nueva prescripción.
     * Un médico prescribe un medicamento a un paciente.
     * 
     * @param prescripcionRequestDTO - Datos de la prescripción
     * @return - ResponseEntity con la prescripción creada y código 201 (CREATED)
     * Ruta: POST /api/prescripciones
     */
    @PostMapping
    public ResponseEntity<PrescripcionResponseDTO> crear(
            @RequestBody PrescripcionRequestDTO prescripcionRequestDTO) {
        PrescripcionResponseDTO prescripcionCreada = prescripcionService.registrar(prescripcionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(prescripcionCreada);
    }

    /**
     * Endpoint GET para obtener todas las prescripciones.
     * 
     * @return - ResponseEntity con la lista de todas las prescripciones y código 200 (OK)
     * Ruta: GET /api/prescripciones
     */
    @GetMapping
    public ResponseEntity<List<PrescripcionResponseDTO>> obtenerTodas() {
        List<PrescripcionResponseDTO> prescripciones = prescripcionService.obtenerTodas();
        return ResponseEntity.ok(prescripciones);
    }

    /**
     * Endpoint GET para obtener una prescripción específica por su ID.
     * 
     * @param id - Identificador de la prescripción
     * @return - ResponseEntity con la prescripción encontrada y código 200 (OK)
     * Ruta: GET /api/prescripciones/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PrescripcionResponseDTO> obtenerPorId(@PathVariable Integer id) {
        PrescripcionResponseDTO prescripcion = prescripcionService.obtenerPorId(id);
        return ResponseEntity.ok(prescripcion);
    }

    /**
     * Endpoint GET para obtener todas las prescripciones de un paciente específico.
     * 
     * @param pacienteId - ID del paciente
     * @return - ResponseEntity con la lista de prescripciones del paciente y código 200 (OK)
     * Ruta: GET /api/prescripciones/paciente/{pacienteId}
     */
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<PrescripcionResponseDTO>> obtenerPorPaciente(@PathVariable Integer pacienteId) {
        List<PrescripcionResponseDTO> prescripciones = prescripcionService.obtenerPorPaciente(pacienteId);
        return ResponseEntity.ok(prescripciones);
    }

    /**
     * Endpoint GET para obtener todas las prescripciones realizadas por un médico.
     * 
     * @param medicoId - ID del médico
     * @return - ResponseEntity con la lista de prescripciones del médico y código 200 (OK)
     * Ruta: GET /api/prescripciones/medico/{medicoId}
     */
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<PrescripcionResponseDTO>> obtenerPorMedico(@PathVariable Integer medicoId) {
        List<PrescripcionResponseDTO> prescripciones = prescripcionService.obtenerPorMedico(medicoId);
        return ResponseEntity.ok(prescripciones);
    }

    /**
     * Endpoint GET para obtener todas las prescripciones de un medicamento específico.
     * 
     * @param medicamentoId - ID del medicamento
     * @return - ResponseEntity con la lista de prescripciones del medicamento y código 200 (OK)
     * Ruta: GET /api/prescripciones/medicamento/{medicamentoId}
     */
    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<List<PrescripcionResponseDTO>> obtenerPorMedicamento(@PathVariable Integer medicamentoId) {
        List<PrescripcionResponseDTO> prescripciones = prescripcionService.obtenerPorMedicamento(medicamentoId);
        return ResponseEntity.ok(prescripciones);
    }

    /**
     * Endpoint DELETE para eliminar una prescripción por su ID.
     * 
     * @param id - Identificador de la prescripción a eliminar
     * @return - ResponseEntity con código 204 (NO CONTENT) si la eliminación fue exitosa
     * Ruta: DELETE /api/prescripciones/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        prescripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
