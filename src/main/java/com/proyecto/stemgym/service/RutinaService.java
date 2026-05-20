package com.proyecto.stemgym.service;

import java.util.List;

import com.proyecto.stemgym.entity.Rutina;

/**
 * Interfaz para {@link com.proyecto.stemgym.service.impl.RutinaServiceImpl}
 * <p>
 * Esta interfaz permite visualizar más rápidamente los métodos que contiene
 * </p>
 */
public interface RutinaService {
    List<Rutina> obtenerTodos();

    Rutina obtenerRutinaPorId(Long id);

    Rutina crearRutina(Rutina rutina);

    Rutina actualizarRutina(Rutina rutinaActualizada, Long id);

    void eliminarRutina(Long id);
}
