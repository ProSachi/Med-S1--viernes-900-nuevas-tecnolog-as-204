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

import com.hospital.control.dto.MedicamentoRequestDTO;
import com.hospital.control.dto.MedicamentoResponseDTO;
import com.hospital.control.service.MedicamentoService;

import lombok.RequiredArgsConstructor;

// La anotación @RestController indica que esta clase es un controlador REST.
// Maneja las solicitudes HTTP y devuelve respuestas en formato JSON.
@RestController
// @RequestMapping define la ruta base para todos los endpoints de este controlador.
// Todos los endpoints estarán bajo /api/medicamentos

@RequestMapping("/api/medicamentos")
// @RequiredArgsConstructor genera automáticamente un constructor con las dependencias.
@RequiredArgsConstructor
public class MedicamentoController {

    // Inyección de dependencia del servicio de Medicamento.
    // El servicio contiene la lógica de negocio para las operaciones CRUD.
    private final MedicamentoService medicamentoService;

    /**
     * Endpoint POST para crear un nuevo medicamento.
     * @param medicamentoRequestDTO - Datos del medicamento a crear (enviados en el cuerpo de la solicitud)
     * @return - ResponseEntity con el medicamento creado y código de estado 201 (CREATED)
     * Ruta: POST /api/medicamentos
     */
    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> crear(
            @RequestBody MedicamentoRequestDTO medicamentoRequestDTO) {
        // Llamamos al servicio para registrar el nuevo medicamento.
        MedicamentoResponseDTO medicamentoCreado = medicamentoService.registrar(medicamentoRequestDTO);

        // Retornamos una respuesta con código 201 (CREATED) y el medicamento creado.
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoCreado);
    }

    /**
     * Endpoint GET para obtener todos los medicamentos.
     * @return - ResponseEntity con la lista de todos los medicamentos y código de estado 200 (OK)
     * Ruta: GET /api/medicamentos
     */
    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> obtenerTodos() {
        // Llamamos al servicio para obtener todos los medicamentos.
        List<MedicamentoResponseDTO> medicamentos = medicamentoService.obtenerTodos();

        // Retornamos una respuesta con código 200 (OK) y la lista de medicamentos.
        return ResponseEntity.ok(medicamentos);
    }

    /**
     * Endpoint GET para obtener un medicamento específico por su ID.
     * @param id - Identificador del medicamento (parte de la ruta URL)
     * @return - ResponseEntity con el medicamento encontrado y código de estado 200 (OK)
     * Ruta: GET /api/medicamentos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        // Llamamos al servicio para obtener el medicamento por su ID.
        MedicamentoResponseDTO medicamento = medicamentoService.obtenerPorId(id);

        // Retornamos una respuesta con código 200 (OK) y el medicamento encontrado.
        return ResponseEntity.ok(medicamento);
    }

    /**
     * Endpoint PUT para actualizar un medicamento existente.
     * @param id - Identificador del medicamento a actualizar (parte de la ruta URL)
     * @param medicamentoRequestDTO - Nuevos datos del medicamento (enviados en el cuerpo de la solicitud)
     * @return - ResponseEntity con el medicamento actualizado y código de estado 200 (OK)
     * Ruta: PUT /api/medicamentos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody MedicamentoRequestDTO medicamentoRequestDTO) {
        // Llamamos al servicio para actualizar el medicamento.
        MedicamentoResponseDTO medicamentoActualizado = medicamentoService.actualizar(id, medicamentoRequestDTO);

        // Retornamos una respuesta con código 200 (OK) y el medicamento actualizado.
        return ResponseEntity.ok(medicamentoActualizado);
    }

    /**
     * Endpoint DELETE para eliminar un medicamento por su ID.
     * @param id - Identificador del medicamento a eliminar (parte de la ruta URL)
     * @return - ResponseEntity con código de estado 204 (NO CONTENT) si la eliminación fue exitosa
     * Ruta: DELETE /api/medicamentos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        // Llamamos al servicio para eliminar el medicamento.
        medicamentoService.eliminar(id);

        // Retornamos una respuesta con código 204 (NO CONTENT) indicando que se eliminó exitosamente.
        return ResponseEntity.noContent().build();
    }
}
