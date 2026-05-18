package com.proyecto.stemgym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    List<Ejercicio> findByMusculosSecundariosContaining(Musculo musculo);

    List<Ejercicio> findByMusculoPrincipal(Musculo musculo);
}
