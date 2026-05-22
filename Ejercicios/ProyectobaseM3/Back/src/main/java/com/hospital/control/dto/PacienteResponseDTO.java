package com.hospital.control.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para enviar datos de un paciente en las respuestas (GET, POST, PUT).
 */
@Setter
@Getter
public class PacienteResponseDTO {

    // ID del paciente (generado automáticamente por la base de datos)
    private Integer id;

    // Nombre del paciente (heredado de Persona)
    private String nombre;

    // Apellido del paciente (heredado de Persona)
    private String apellido;
}