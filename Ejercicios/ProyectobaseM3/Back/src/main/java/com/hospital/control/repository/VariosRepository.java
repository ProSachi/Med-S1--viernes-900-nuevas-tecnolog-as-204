package com.hospital.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.control.model.Varios;

@Repository
public interface VariosRepository extends JpaRepository<Varios, Integer> {
    
    List<Varios> findById(int id);

    List<Varios> findByNombre(String nombre);

}
