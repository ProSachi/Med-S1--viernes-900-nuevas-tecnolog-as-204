package com.hospital.control.dto;

import lombok.Data;

/**
 * DTO para recibir datos de creación o actualización de enfermeros.
 */
@Data
public class EnfermerosRequestDTO {
    private String nombre;
    private String apellido;
    private String especialidad;
}


