package com.hospital.control.dto;

import lombok.Data;

/**
 * DTO para enviar datos de un enfermero en las respuestas (GET, POST, PUT).
 * Incluye todos los campos visibles para el cliente.
 */
@Data
public class EnfermerosResponseDTO {
    
    // ID del enfermero (generado automáticamente por la base de datos)
    private Integer id;

    // Nombre del enfermero (heredado de Persona)
    private String nombre;

    // Apellido del enfermero (heredado de Persona)
    private String apellido;

    // Especialidad específica del enfermero
    private String especialidad;
}
