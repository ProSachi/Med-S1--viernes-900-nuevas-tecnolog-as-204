package com.hospital.control.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para recibir datos de creación o actualización de pacientes.
 */
@Setter
@Getter
public class PacienteRequestDTO {
    private String nombre;
    private String apellido;
    private Integer medicoId;
}