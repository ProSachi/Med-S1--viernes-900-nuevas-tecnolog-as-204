package com.hospital.control.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para enviar datos de un medicamento en las respuestas (GET, POST, PUT).
 */
@Setter
@Getter
public class MedicamentoResponseDTO {

    // ID del medicamento (generado automáticamente por la base de datos)
    private Integer id;

    // Nombre del medicamento
    private String nombreMedicamento;

    // Descripción del medicamento
    private String descripcion;
}