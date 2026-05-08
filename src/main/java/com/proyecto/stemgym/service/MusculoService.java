package com.proyecto.stemgym.service;

import java.util.List;

import com.proyecto.stemgym.entity.Musculo;

/**
 * Interfaz para {@link MusculoServiceImpl}
 * <p>
 * Esta interfaz permite visualizar más rápidamente los métodos que contiene
 * {@link MusculoServiceImpl}
 * </p>
 */
public interface MusculoService {
    List<Musculo> obtenerTodos();

    Musculo obtenerMusculoPorId(Long id);

    void crearMusculo(Musculo musculo);

    Musculo actualizarMusculo(Musculo musculoActualizado, Long id);

    void eliminarMusculo(Long id);
}
