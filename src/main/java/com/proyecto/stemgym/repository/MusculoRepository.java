package com.proyecto.stemgym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.stemgym.entity.Musculo;

public interface MusculoRepository extends JpaRepository<Musculo, Long>{
    
}
