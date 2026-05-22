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

import com.hospital.control.dto.EnfermerosRequestDTO;
import com.hospital.control.dto.EnfermerosResponseDTO;
import com.hospital.control.service.EnfermeroService;

import lombok.RequiredArgsConstructor;

// La anotación @RestController indica que esta clase es un controlador REST.
// Maneja las solicitudes HTTP y devuelve respuestas en formato JSON.
@RestController
// @RequestMapping define la ruta base para todos los endpoints de este controlador.
// Todos los endpoints estarán bajo /api/enfermeros
//localhost:8080/api/


@RequestMapping("/api/enfermeros")
// @RequiredArgsConstructor genera automáticamente un constructor con las dependencias.
@RequiredArgsConstructor
public class EnfermerosController {

    // Inyección de dependencia del servicio de Enfermeros.
    // El servicio contiene la lógica de negocio para las operaciones CRUD.
    private final EnfermeroService enfermeroService;

    /**
     * Endpoint POST para crear un nuevo enfermero.
     * @param enfermerosRequestDTO - Datos del enfermero a crear (enviados en el cuerpo de la solicitud)
     * @return - ResponseEntity con el enfermero creado y código de estado 201 (CREATED)
     * Ruta: POST /api/enfermeros
     */
    @PostMapping
    public ResponseEntity<EnfermerosResponseDTO> crear(
            @RequestBody EnfermerosRequestDTO enfermerosRequestDTO) {
        // Llamamos al servicio para registrar el nuevo enfermero.
        EnfermerosResponseDTO enfermeroCreado = enfermeroService.registrarEnfermero(enfermerosRequestDTO);

        // Retornamos una respuesta con código 201 (CREATED) y el enfermero creado.
        return ResponseEntity.status(HttpStatus.CREATED).body(enfermeroCreado);
    }

    /**
     * Endpoint GET para obtener todos los enfermeros.
     * @return - ResponseEntity con la lista de todos los enfermeros y código de estado 200 (OK)
     * Ruta: GET /api/enfermeros
     */
    @GetMapping
    public ResponseEntity<List<EnfermerosResponseDTO>> obtenerTodos() {
        // Llamamos al servicio para obtener todos los enfermeros.
        List<EnfermerosResponseDTO> enfermeros = enfermeroService.obtenerEnfermeros();

        // Retornamos una respuesta con código 200 (OK) y la lista de enfermeros.
        return ResponseEntity.ok(enfermeros);
    }

    /**
     * Endpoint GET para obtener un enfermero específico por su ID.
     * @param id - Identificador del enfermero (parte de la ruta URL)
     * @return - ResponseEntity con el enfermero encontrado y código de estado 200 (OK)
     * Ruta: GET /api/enfermeros/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EnfermerosResponseDTO> obtenerPorId(@PathVariable Integer id) {
        // Buscamos el enfermero por su ID usando el método del servicio.
        // El servicio retorna directamente el DTO de respuesta.
        EnfermerosResponseDTO enfermero = enfermeroService.obtenerEnfermeroPorId(id);

        // Retornamos una respuesta con código 200 (OK) y el enfermero encontrado.
        return ResponseEntity.ok(enfermero);
    }

    /**
     * Endpoint PUT para actualizar un enfermero existente.
     * @param id - Identificador del enfermero a actualizar (parte de la ruta URL)
     * @param enfermerosRequestDTO - Nuevos datos del enfermero (enviados en el cuerpo de la solicitud)
     * @return - ResponseEntity con el enfermero actualizado y código de estado 200 (OK)
     * Ruta: PUT /api/enfermeros/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<EnfermerosResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody EnfermerosRequestDTO enfermerosRequestDTO) {
        // Llamamos al servicio para actualizar el enfermero.
        EnfermerosResponseDTO enfermeroActualizado = enfermeroService.actualizarEnfermero(id, enfermerosRequestDTO);

        // Retornamos una respuesta con código 200 (OK) y el enfermero actualizado.
        return ResponseEntity.ok(enfermeroActualizado);
    }


    /**
     * Endpoint DELETE para eliminar un enfermero por su ID.
     * @param id - Identificador del enfermero a eliminar (parte de la ruta URL)
     * @return - ResponseEntity con código de estado 204 (NO CONTENT) si la eliminación fue exitosa
     * Ruta: DELETE /api/enfermeros/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        // Llamamos al servicio para eliminar el enfermero.
        enfermeroService.eliminarEnfermero(id);

        // Retornamos una respuesta con código 204 (NO CONTENT) indicando que se eliminó exitosamente.
        return ResponseEntity.noContent().build();
    }
}
