package com.hospital.control.repository;

import com.hospital.control.model.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescripcionRepository extends JpaRepository<Prescripcion, Integer> {
    List<Prescripcion> findByPacienteId(Integer pacienteId);
    List<Prescripcion> findByMedicoId(Integer medicoId);
    List<Prescripcion> findByMedicamentoId(Integer medicamentoId);
}
