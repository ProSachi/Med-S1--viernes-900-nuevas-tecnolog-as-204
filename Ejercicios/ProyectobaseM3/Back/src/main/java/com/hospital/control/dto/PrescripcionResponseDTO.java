package com.hospital.control.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrescripcionResponseDTO {
    private Integer id;
    private String nombreMedicamento;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String nombreMedico;
    private String apellidoMedico;
    private String dosis;
    private String frecuencia;
    private String duracion;
    private LocalDateTime fechaPrescripcion;
    private String observaciones;
}
