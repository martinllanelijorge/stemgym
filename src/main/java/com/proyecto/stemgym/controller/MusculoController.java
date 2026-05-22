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
import com.proyecto.stemgym.service.MusculoService;

/**
 * Clase para mapear las peticiones y envíos de la api (GET, POT, PUT y DELETE)
 * para los datos referentes a la entidad {@link Musculo}
 * 
 * @see Musculo
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@RestController
@RequestMapping("/stemgymapi/musculos")
public class MusculoController {

    @Autowired
    private MusculoService musculoService;

    /**
     * Método GET de la API para obtener todos los objetos {@link Musculo}
     * 
     * @return lista de {@link Musculo}
     * 
     * @since 1.0
     */
    @GetMapping
    public List<Musculo> obtenerTodosLosMusculos() {
        return musculoService.obtenerTodos();
    }

    /**
     * Método GET de la API para obtener el {@link Musculo} por id
     * 
     * @param id del músculo
     * @return devuelve el {@link Musculo} buscado
     * 
     * @since 1.0
     */
    @GetMapping("/{id}")
    public Musculo obtenerMusculo(@PathVariable Long id) {
        return musculoService.obtenerMusculoPorId(id);
    }

    /**
     * Método GET de la API para obtener los ejercicios principales del músculo por
     * id
     * 
     * @param id del músculo
     * @return devuelve un {@link java.util.List} de {@link Ejercicio} del músculo
     *         buscado
     * 
     * @since 1.0
     */
    @GetMapping("/{id}/ejercicios")
    public List<Ejercicio> obtenerEjerciciosDeMusculo(@PathVariable Long id) {
        return musculoService.obtenerMusculoPorId(id).getEjercicios();
    }

    /**
     * Método POST de la API para crear un nuevo {@link Musculo}
     * 
     * @param musculo músculo que se desea crear
     * @return devuelve el nuevo músculo
     * 
     * @since 1.0
     */
    @PostMapping
    public Musculo crearNuevoMusculo(@RequestBody Musculo musculo) {
        return musculoService.crearMusculo(musculo);
    }

    /**
     * Método PUT de la API para actualizar un {@link Musculo} existente
     * 
     * @param musculoActualizado músculo con datos ya actualizados
     * @param id                 id del músculo que se pretende actualizar
     * @return devuelve el músculo actualizado
     */
    @PutMapping("/{id}")
    public Musculo actualizarMusculo(@RequestBody Musculo musculoActualizado, @PathVariable Long id) {
        return musculoService.actualizarMusculo(musculoActualizado, id);
    }

    /**
     * Método DELETE de la API para eliminar un {@link Musculo}
     * 
     * @param id id del músculo que se desea eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminarMusculo(@PathVariable Long id) {
        musculoService.eliminarMusculo(id);
    }
}
