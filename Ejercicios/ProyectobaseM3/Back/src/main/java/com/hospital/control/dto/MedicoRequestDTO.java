package com.hospital.control.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para recibir datos de creación o actualización de médicos.
 */
@Setter
@Getter
public class MedicoRequestDTO {
    private String nombre;
    private String apellido;
    private String gerencia;
}