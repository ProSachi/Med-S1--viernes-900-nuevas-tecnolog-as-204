package com.hospital.control.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para recibir datos de creación o actualización de medicamentos.
 */
@Setter
@Getter
public class MedicamentoRequestDTO {
    private String nombreMedicamento;
    private String descripcion;
}