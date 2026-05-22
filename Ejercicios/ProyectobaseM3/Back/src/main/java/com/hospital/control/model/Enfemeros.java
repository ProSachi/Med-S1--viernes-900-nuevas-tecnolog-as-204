package com.hospital.control.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "Enfermeros")
@Entity
public class Enfemeros extends Persona{
    
    @Column(name = "especialidad", nullable = false, length = 100)
    private String especialidad;

    public Enfemeros(String especialidad) {
        this.especialidad = especialidad;
    }

    public Enfemeros(String nombre, String apellido, String especialidad) {
        super(nombre, apellido);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
