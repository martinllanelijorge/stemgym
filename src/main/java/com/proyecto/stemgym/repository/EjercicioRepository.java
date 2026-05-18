package com.proyecto.stemgym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;

import jakarta.transaction.Transactional;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    List<Ejercicio> findByMusculosSecundariosContaining(Musculo musculo);

    List<Ejercicio> findByMusculoPrincipal(Musculo musculo);

    /**
     * Elimina de ejercicio_musculo_secundario todas las filas del ejercicio
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ejercicio_musculo_secundario WHERE ejercicio_id = :ejercicioId", nativeQuery = true)
    void eliminarMusculosSecundarios(@Param("ejercicioId") Long ejercicioId);

    /** 
     * Elimina de ejercicio_musculo_secundario todas las filas del músculo dado 
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ejercicio_musculo_secundario WHERE musculo_id = :musculoId", nativeQuery = true)
    void eliminarPorMusculoSecundario(@Param("musculoId") Long musculoId);

    /** 
     * Elimina de rutina_ejercicio todas las filas de los ejercicios dados 
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM rutina_ejercicio WHERE ejercicio_id IN :ids", nativeQuery = true)
    void eliminarDeRutinasPorLista(@Param("ids") List<Long> ids);
}
