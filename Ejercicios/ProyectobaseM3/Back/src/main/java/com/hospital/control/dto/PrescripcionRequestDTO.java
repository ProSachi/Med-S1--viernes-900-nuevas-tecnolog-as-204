package com.hospital.control.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrescripcionRequestDTO {
    private Integer medicamentoId;
    private Integer pacienteId;
    private Integer medicoId;
    private String dosis;
    private String frecuencia;
    private String duracion;
    private String observaciones;
}
