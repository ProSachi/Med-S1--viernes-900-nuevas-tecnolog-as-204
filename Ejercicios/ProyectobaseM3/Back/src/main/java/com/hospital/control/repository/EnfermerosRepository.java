package com.hospital.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.control.model.Enfemeros;

@Repository
public interface EnfermerosRepository extends JpaRepository<Enfemeros, Integer> {
    List<Enfemeros> findByNombre(String nombre); 
    List<Enfemeros> findById(int id);    
    
}
