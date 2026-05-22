package com.hospital.control.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * DTO (Data Transfer Object) para representar errores en las respuestas de la API.
 * 
 * Se utiliza para estandarizar la forma en que se envían los errores al cliente.
 * Esto permite que el cliente entienda y maneje los errores de manera consistente.
 * 
 * Ejemplo de respuesta de error en JSON:
 * {
 *   "codigo": 400,
 *   "mensaje": "Error de validación en los datos proporcionados",
 *   "detalles": {
 *     "nombre": "El nombre del enfermero no puede estar vacío",
 *     "especialidad": "La especialidad del enfermero no puede estar vacía"
 *   },
 *   "timestamp": "2024-05-07T14:30:00"
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {

    // Código HTTP numérico (200, 201, 400, 404, 500, etc.)
    // Usar Integer en lugar de String es más eficiente para comparaciones.
    private Integer codigo;

    // Mensaje de error general/descriptivo.
    // Explica qué salió mal de manera clara para el usuario.
    private String mensaje;

    // Campo opcional que contiene detalles de errores de validación específicos.
    // Clave: nombre del campo
    // Valor: mensaje de error para ese campo
    // Ejemplo: "nombre" -> "El nombre no puede estar vacío"
    private Map<String, String> detalles;

    // Marca de tiempo indicando cuándo ocurrió el error.
    // Útil para auditoría y debugging.
    private LocalDateTime timestamp;

    // Constructor simplificado para casos sin detalles de validación.
    public ErrorDTO(Integer codigo, String mensaje, LocalDateTime timestamp) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.timestamp = timestamp;
    }
}

