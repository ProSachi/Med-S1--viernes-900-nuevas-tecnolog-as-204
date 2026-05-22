package com.hospital.control.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.control.dto.PacienteRequestDTO;
import com.hospital.control.dto.PacienteResponseDTO;
import com.hospital.control.service.PacienteService;

import lombok.RequiredArgsConstructor;

// La anotación @RestController indica que esta clase es un controlador REST.
// Maneja las solicitudes HTTP y devuelve respuestas en formato JSON.
@RestController
// @RequestMapping define la ruta base para todos los endpoints de este controlador.
// Todos los endpoints estarán bajo /api/pacientes
@RequestMapping("/api/pacientes")
// @RequiredArgsConstructor genera automáticamente un constructor con las dependencias.
@RequiredArgsConstructor
public class PacienteController {

    // Inyección de dependencia del servicio de Paciente.
    // El servicio contiene la lógica de negocio para las operaciones CRUD.
    private final PacienteService pacienteService;

    /**
     * Endpoint POST para crear un nuevo paciente.
     * @param pacienteRequestDTO - Datos del paciente a crear (enviados en el cuerpo de la solicitud)
     * @return - ResponseEntity con el paciente creado y código de estado 201 (CREATED)
     * Ruta: POST /api/pacientes
     */
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> crear(
            @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        // Llamamos al servicio para registrar el nuevo paciente.
        PacienteResponseDTO pacienteCreado = pacienteService.registrar(pacienteRequestDTO);

        // Retornamos una respuesta con código 201 (CREATED) y el paciente creado.
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCreado);
    }

    /**
     * Endpoint GET para obtener todos los pacientes.
     * @return - ResponseEntity con la lista de todos los pacientes y código de estado 200 (OK)
     * Ruta: GET /api/pacientes
     */
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> obtenerTodos() {
        // Llamamos al servicio para obtener todos los pacientes.
        List<PacienteResponseDTO> pacientes = pacienteService.obtenerTodos();

        // Retornamos una respuesta con código 200 (OK) y la lista de pacientes.
        return ResponseEntity.ok(pacientes);
    }

    /**
     * Endpoint GET para obtener un paciente específico por su ID.
     * @param id - Identificador del paciente (parte de la ruta URL)
     * @return - ResponseEntity con el paciente encontrado y código de estado 200 (OK)
     * Ruta: GET /api/pacientes/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> obtenerPorId(@PathVariable Integer id) {
        // Llamamos al servicio para obtener el paciente por su ID.
        PacienteResponseDTO paciente = pacienteService.obtenerPorId(id);

        // Retornamos una respuesta con código 200 (OK) y el paciente encontrado.
        return ResponseEntity.ok(paciente);
    }

    /**
     * Endpoint PUT para actualizar un paciente existente.
     * @param id - Identificador del paciente a actualizar (parte de la ruta URL)
     * @param pacienteRequestDTO - Nuevos datos del paciente (enviados en el cuerpo de la solicitud)
     * @return - ResponseEntity con el paciente actualizado y código de estado 200 (OK)
     * Ruta: PUT /api/pacientes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        // Llamamos al servicio para actualizar el paciente.
        PacienteResponseDTO pacienteActualizado = pacienteService.actualizar(id, pacienteRequestDTO);

        // Retornamos una respuesta con código 200 (OK) y el paciente actualizado.
        return ResponseEntity.ok(pacienteActualizado);
    }

    /**
     * Endpoint DELETE para eliminar un paciente por su ID.
     * @param id - Identificador del paciente a eliminar (parte de la ruta URL)
     * @return - ResponseEntity con código de estado 204 (NO CONTENT) si la eliminación fue exitosa
     * Ruta: DELETE /api/pacientes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        // Llamamos al servicio para eliminar el paciente.
        pacienteService.eliminar(id);

        // Retornamos una respuesta con código 204 (NO CONTENT) indicando que se eliminó exitosamente.
        return ResponseEntity.noContent().build();
    }
}
