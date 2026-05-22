package com.hospital.control.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/graficos")
public class DashboardController {

    private final RestClient restClient;

    public DashboardController() {
        // URL base del microservicio analítico de Python
        this.restClient = RestClient.create("http://localhost:8000");
    }

    // ── HEADLESS: devuelven list[dict] como JSON ──────────────────────────────

    @GetMapping("/headless/pacientes-por-medico")
    public ResponseEntity<?> headlessPacientesPorMedico() {
        try {
            List<?> respuesta = restClient.get()
                    .uri("/api/graficos/headless/pacientes-por-medico")
                    .retrieve()
                    .body(List.class);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Servicio analítico no disponible."));
        }
    }

    @GetMapping("/headless/prescripciones-por-medico")
    public ResponseEntity<?> headlessPrescripcionesPorMedico() {
        try {
            List<?> respuesta = restClient.get()
                    .uri("/api/graficos/headless/prescripciones-por-medico")
                    .retrieve()
                    .body(List.class);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Servicio analítico no disponible."));
        }
    }

    @GetMapping("/headless/prescripciones-por-paciente")
    public ResponseEntity<?> headlessPrescripcionesPorPaciente() {
        try {
            List<?> respuesta = restClient.get()
                    .uri("/api/graficos/headless/prescripciones-por-paciente")
                    .retrieve()
                    .body(List.class);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Servicio analítico no disponible."));
        }
    }

    @GetMapping("/headless/medicamentos-mas-prescritos")
    public ResponseEntity<?> headlessMedicamentosMasPrescritos(
            @RequestParam(defaultValue = "10") int top) {
        try {
            List<?> respuesta = restClient.get()
                    .uri("/api/graficos/headless/medicamentos-mas-prescritos?top=" + top)
                    .retrieve()
                    .body(List.class);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Servicio analítico no disponible."));
        }
    }

    // ── PULL: devuelven PNG bytes directos ────────────────────────────────────

    @GetMapping(value = "/pull/pacientes-por-medico", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> pullPacientesPorMedico() {
        try {
            byte[] imagen = restClient.get()
                    .uri("/api/graficos/pull/pacientes-por-medico")
                    .retrieve()
                    .body(byte[].class);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imagen);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/pull/prescripciones-por-medico", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> pullPrescripcionesPorMedico() {
        try {
            byte[] imagen = restClient.get()
                    .uri("/api/graficos/pull/prescripciones-por-medico")
                    .retrieve()
                    .body(byte[].class);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imagen);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/pull/prescripciones-por-paciente", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> pullPrescripcionesPorPaciente() {
        try {
            byte[] imagen = restClient.get()
                    .uri("/api/graficos/pull/prescripciones-por-paciente")
                    .retrieve()
                    .body(byte[].class);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imagen);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/pull/medicamentos-mas-prescritos", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> pullMedicamentosMasPrescritos(
            @RequestParam(defaultValue = "10") int top) {
        try {
            byte[] imagen = restClient.get()
                    .uri("/api/graficos/pull/medicamentos-mas-prescritos?top=" + top)
                    .retrieve()
                    .body(byte[].class);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imagen);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
