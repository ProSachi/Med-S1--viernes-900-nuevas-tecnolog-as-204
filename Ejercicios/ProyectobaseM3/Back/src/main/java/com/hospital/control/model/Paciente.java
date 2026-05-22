package com.hospital.control.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pacientes")
public class Paciente extends Persona {

  @ManyToOne
  @JoinColumn(name = "medico_id")
  private Medico medico;

  @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
  private List<Prescripcion> prescripciones = new ArrayList<>();
}
