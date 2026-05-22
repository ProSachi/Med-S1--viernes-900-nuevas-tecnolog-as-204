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

import com.hospital.control.dto.MedicoRequestDTO;
import com.hospital.control.dto.MedicoResponseDTO;
import com.hospital.control.service.MedicoService;

import lombok.RequiredArgsConstructor;

// La anotación @RestController indica que esta clase es un controlador REST.
// Maneja las solicitudes HTTP y devuelve respuestas en formato JSON.
@RestController
// @RequestMapping define la ruta base para todos los endpoints de este controlador.
// Todos los endpoints estarán bajo /api/medicos
@RequestMapping("/api/medicos")
// @RequiredArgsConstructor genera automáticamente un constructor con las dependencias.
@RequiredArgsConstructor
public class MedicoController {

    // Inyección de dependencia del servicio de Medico.
    // El servicio contiene la lógica de negocio para las operaciones CRUD.
    private final MedicoService medicoService;

    /**
     * Endpoint POST para crear un nuevo médico.
     * @param medicoRequestDTO - Datos del médico a crear (enviados en el cuerpo de la solicitud)
     * @return - ResponseEntity con el médico creado y código de estado 201 (CREATED)
     * Ruta: POST /api/medicos
     */
    @PostMapping
    public ResponseEntity<MedicoResponseDTO> crear(
            @RequestBody MedicoRequestDTO medicoRequestDTO) {
        // Llamamos al servicio para registrar el nuevo médico.
        MedicoResponseDTO medicoCreado = medicoService.registrarMedico(medicoRequestDTO);

        // Retornamos una respuesta con código 201 (CREATED) y el médico creado.
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCreado);
    }

    /**
     * Endpoint GET para obtener todos los médicos.
     * @return - ResponseEntity con la lista de todos los médicos y código de estado 200 (OK)
     * Ruta: GET /api/medicos
     */
    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> obtenerTodos() {
        // Llamamos al servicio para obtener todos los médicos.
        List<MedicoResponseDTO> medicos = medicoService.obtenerTodos();

        // Retornamos una respuesta con código 200 (OK) y la lista de médicos.
        return ResponseEntity.ok(medicos);
    }

    /**
     * Endpoint GET para obtener un médico específico por su ID.
     * @param id - Identificador del médico (parte de la ruta URL)
     * @return - ResponseEntity con el médico encontrado y código de estado 200 (OK)
     * Ruta: GET /api/medicos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        // Llamamos al servicio para obtener el médico por su ID.
        MedicoResponseDTO medico = medicoService.obtenerPorId(id);

        // Retornamos una respuesta con código 200 (OK) y el médico encontrado.
        return ResponseEntity.ok(medico);
    }

    /**
     * Endpoint PUT para actualizar un médico existente.
     * @param id - Identificador del médico a actualizar (parte de la ruta URL)
     * @param medicoRequestDTO - Nuevos datos del médico (enviados en el cuerpo de la solicitud)
     * @return - ResponseEntity con el médico actualizado y código de estado 200 (OK)
     * Ruta: PUT /api/medicos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody MedicoRequestDTO medicoRequestDTO) {
        // Llamamos al servicio para actualizar el médico.
        MedicoResponseDTO medicoActualizado = medicoService.actualizar(id, medicoRequestDTO);

        // Retornamos una respuesta con código 200 (OK) y el médico actualizado.
        return ResponseEntity.ok(medicoActualizado);
    }


    /**
     * Endpoint DELETE para eliminar un médico por su ID.
     * @param id - Identificador del médico a eliminar (parte de la ruta URL)
     * @return - ResponseEntity con código de estado 204 (NO CONTENT) si la eliminación fue exitosa
     * Ruta: DELETE /api/medicos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        // Llamamos al servicio para eliminar el médico.
        medicoService.eliminar(id);

        // Retornamos una respuesta con código 204 (NO CONTENT) indicando que se eliminó exitosamente.
        return ResponseEntity.noContent().build();
    }
}
