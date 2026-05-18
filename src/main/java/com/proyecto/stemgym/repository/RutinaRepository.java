package com.proyecto.stemgym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.stemgym.entity.Rutina;

public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    /** Devuelve todas las rutinas que contienen el ejercicio con el id dado */
    List<Rutina> findByEjerciciosId(Long ejercicioId);

}
