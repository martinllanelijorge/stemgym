package com.proyecto.stemgym.service;

import java.util.List;

import com.proyecto.stemgym.entity.Ejercicio;

/**
 * Interfaz para {@link EjercicioServiceImpl}
 * <p>
 * Esta interfaz permite visualizar más rápidamente los métodos que contiene
 * {@link EjercicioServiceImpl}
 * </p>
 */
public interface EjercicioService {
    List<Ejercicio> obtenerTodos();

    Ejercicio obtenerEjercicioPorId(Long id);

    Ejercicio crearEjercicio(Ejercicio ejercicio);

    Ejercicio actualizarEjercicio(Ejercicio ejercicioActualizado, Long id);

    void eliminarEjercicio(Long id);
}
