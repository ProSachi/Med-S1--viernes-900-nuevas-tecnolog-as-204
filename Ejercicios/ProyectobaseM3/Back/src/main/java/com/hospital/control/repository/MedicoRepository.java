package com.hospital.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.control.model.Medico;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    List<Medico> findById(int id);

    List<Medico> findByNombre(String nombre);

}
