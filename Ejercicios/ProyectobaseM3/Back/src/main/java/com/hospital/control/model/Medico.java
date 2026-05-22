package com.hospital.control.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "Medicos")
@Entity
public class Medico extends Persona {
    
    @Column(name = "gerencia", nullable = false, length = 100)
    private String gerencia;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Paciente> pacientes = new ArrayList<>();

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Prescripcion> prescripciones = new ArrayList<>();

    public Medico(String gerencia) {
        this.gerencia = gerencia;
    }

    public Medico(String nombre, String apellido, String gerencia) {
        super(nombre, apellido);
        this.gerencia = gerencia;
    }

    public String getGerencia() {
        return gerencia;
    }

    public void setGerencia(String gerencia) {
        this.gerencia = gerencia;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Prescripcion> getPrescripciones() {
        return prescripciones;
    }

    public void setPrescripciones(List<Prescripcion> prescripciones) {
        this.prescripciones = prescripciones;
    }
}
