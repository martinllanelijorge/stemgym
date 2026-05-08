package com.proyecto.stemgym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;
import com.proyecto.stemgym.service.EjercicioService;

/**
 * Clase para mapear las peticiones y envíos de la api (GET, POT, PUT y DELETE)
 * para los datos referentes a la entidad {@link Ejercicio}
 * 
 * @see Ejercicio
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@RestController
@RequestMapping("/stemgymapi/ejercicios")
public class EjercicioController {
    @Autowired
    private EjercicioService ejercicioService;

    /**
     * Método GET de la API para obtener todos los objetos {@link Ejercicio}
     * 
     * @return lista de {@link Ejercicio}
     * 
     * @since 1.0
     */
    @GetMapping
    public List<Ejercicio> obtenerTodosLosEjercicios() {
        return ejercicioService.obtenerTodos();
    }

    /**
     * Método GET de la API para obtener el {@link Ejercicio} por id
     * 
     * @param id del ejercicio
     * @return devuelve el {@link Ejercicio} buscado
     * 
     * @since 1.0
     */
    @GetMapping("/{id}")
    public Ejercicio obtenerEjercicio(@PathVariable Long id) {
        return ejercicioService.obtenerEjercicioPorId(id);
    }

    /**
     * Método POST de la API para crear un nuevo {@link Ejercicio}
     * 
     * @param ejercicio ejercicio que se desea crear
     * @return devuelve el nuevo ejercicio
     * 
     * @since 1.0
     */
    @PostMapping
    public Ejercicio crearNuevoEJercicio(@RequestBody Ejercicio ejercicio) {
        return ejercicioService.crearEjercicio(ejercicio);
    }

    /**
     * Método PUT de la API para actualizar un {@link Ejercicio} existente
     * 
     * @param ejercicioActualizado ejercicio con datos ya actualizados
     * @param id                   id del ejercicio que se pretende actualizar
     * @return devuelve el ejercicio actualizado
     */
    @PutMapping("/{id}")
    public Ejercicio actualizarEjercicio(@RequestBody Ejercicio ejercicioActualizado, @PathVariable Long id) {
        return ejercicioService.actualizarEjercicio(ejercicioActualizado, id);
    }

    /**
     * Método DELETE de la API para eliminar un {@link Ejercicio}
     * 
     * @param id id del ejercicio que se desea eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminarEjercicio(@PathVariable Long id) {
        ejercicioService.eliminarEjercicio(id);
    }
}
