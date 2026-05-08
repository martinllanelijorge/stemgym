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

import com.proyecto.stemgym.entity.Rutina;
import com.proyecto.stemgym.service.RutinaService;

/**
 * Clase para mapear las peticiones y envíos de la api (GET, POT, PUT y DELETE)
 * para los datos referentes a la entidad {@link Rutina}
 * 
 * @see Rutina
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@RestController
@RequestMapping("/stemgymapi/rutinas")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;

    /**
     * Método GET de la API para obtener todos los objetos {@link Rutina}
     * 
     * @return lista de {@link Rutina}
     * 
     * @since 1.0
     */
    @GetMapping
    public List<Rutina> obtenerTodasLasRutinas() {
        return rutinaService.obtenerTodos();
    }

    /**
     * Método GET de la API para obtener la {@link Rutina} por id
     * 
     * @param id de la rutina
     * @return devuelve la {@link Rutina} buscada
     * 
     * @since 1.0
     */
    @GetMapping("/{id}")
    public Rutina obtenerRutina(@PathVariable Long id) {
        return rutinaService.obtenerRutinaPorId(id);
    }

    /**
     * Método POST de la API para crear una nueva {@link Rutina}
     * 
     * @param rutina rutina que se desea crear
     * @return devuelve la nueva rutina
     * 
     * @since 1.0
     */
    @PostMapping
    public Rutina crearNuevaRutina(@RequestBody Rutina rutina) {
        return rutinaService.crearRutina(rutina);
    }

    /**
     * Método PUT de la API para actualizar una {@link Rutina} existente
     * 
     * @param rutinaActualizada rutina con datos ya actualizados
     * @param id                id de la rutina que se pretende actualizar
     * @return devuelve la rutina actualizada
     */
    @PutMapping("/{id}")
    public Rutina actualizarRutina(@RequestBody Rutina rutinaActualizada, @PathVariable Long id) {
        return rutinaService.actualizarRutina(rutinaActualizada, id);
    }

    /**
     * Método DELETE de la API para eliminar una {@link Rutina}
     * 
     * @param id id de la rutina que se desea eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminarRutina(@PathVariable Long id) {
        rutinaService.eliminarRutina(id);
    }
}
