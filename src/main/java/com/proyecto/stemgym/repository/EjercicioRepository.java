package com.proyecto.stemgym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.stemgym.entity.Ejercicio;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {

}
