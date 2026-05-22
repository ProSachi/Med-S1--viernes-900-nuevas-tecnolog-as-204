package com.hospital.control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de prueba para verificar que la aplicación está correctamente inicializada.
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    /**
     * Endpoint simple para verificar que la aplicación está corriendo.
     * 
     * @return Mensaje de estado
     * 
     * Uso: GET http://localhost:8080/api/health
     */
    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("✅ Aplicación Hospital Control - OK");
    }

    /**
     * Endpoint para verificar estado detallado.
     * 
     * @return Estado detallado en JSON
     * 
     * Uso: GET http://localhost:8080/api/health/status
     */
    @GetMapping("/status")
    public ResponseEntity<HealthStatus> status() {
        return ResponseEntity.ok(new HealthStatus(
                "UP",
                "Hospital Control API",
                "1.0.0",
                System.currentTimeMillis()
        ));
    }

    /**
     * Clase interna para representar el estado de la aplicación.
     */
    static class HealthStatus {
        public String status;
        public String application;
        public String version;
        public long timestamp;

        public HealthStatus(String status, String application, String version, long timestamp) {
            this.status = status;
            this.application = application;
            this.version = version;
            this.timestamp = timestamp;
        }

        // Getters para Jackson
        public String getStatus() { return status; }
        public String getApplication() { return application; }
        public String getVersion() { return version; }
        public long getTimestamp() { return timestamp; }
    }
}
