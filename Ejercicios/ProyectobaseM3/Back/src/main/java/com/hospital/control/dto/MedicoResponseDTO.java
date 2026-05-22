package com.hospital.control.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para enviar datos de un médico en las respuestas (GET, POST, PUT).
 * Incluye la lista de pacientes asociados al médico.
 */
@Setter
@Getter
public class MedicoResponseDTO {

    // ID del médico (generado automáticamente por la base de datos)
    private Integer id;

    // Nombre del médico (heredado de Persona)
    private String nombre;

    // Apellido del médico (heredado de Persona)
    private String apellido;

    // Gerencia a la que pertenece el médico
    private String gerencia;

    // Lista de pacientes asociados a este médico
    // Se incluye para mostrar la relación 1:N
    private List<PacienteResponseDTO> pacientes;
}