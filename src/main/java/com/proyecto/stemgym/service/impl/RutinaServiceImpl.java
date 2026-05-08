package com.proyecto.stemgym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.stemgym.entity.Rutina;
import com.proyecto.stemgym.repository.RutinaRepository;
import com.proyecto.stemgym.service.RutinaService;

/**
 * Clase que implementa {@link RutinaService} para desarrollar los métodos GET,
 * POST, PUT y DELETE.
 * 
 * <p>
 * Esta clase se encarga de desarrollar la lógica interna de lo que devuelven
 * cada uno de los métodos para el GET, POST, PUT y DELETE de la API en lo
 * referente a la entidad {@link Rutina}
 * </p>
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Service
public class RutinaServiceImpl implements RutinaService {

    @Autowired
    private RutinaRepository rutinaRepository;

    /**
     * Método para obtener todos las rutinas existentes en la BBDD
     * 
     * @return Objetos {@link Rutina} encontrados
     * 
     * @since 1.0
     */
    @Override
    public List<Rutina> obtenerTodos() {
        return rutinaRepository.findAll();
    }

    /**
     * Método para obtener una rutina por su id
     * <p>
     * Este método devuelve una rutina mediante su búsqueda por id, lanza un error
     * cuando no la encuentra
     * </p>
     * 
     * @param id id de la rutina que se desea encontrar
     * @return Objeto {@link Rutina} encontrada
     * @throws RuntimeException si no encuentra la rutina
     * @since 1.0
     */
    @Override
    public Rutina obtenerRutinaPorId(Long id) {
        return rutinaRepository.findById(id).orElseThrow(() -> new RuntimeException("¡ERROR! Rutina no encontrada"));
    }

    /**
     * Método para crear una nueva rutina
     * 
     * @param rutina rutina nueva
     * @return Objeto {@link Rutina} creado
     * @since 1.0
     */
    @Override
    public Rutina crearRutina(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    /**
     * Método para actualizar una rutina
     * <p>
     * Este método permite actualizar una rutina existente en la BBDD, modificando
     * sus atributos
     * </p>
     * 
     * @param rutinaActualizada rutina con los atributos ya modificados
     * @param id                id de la rutina que se desea modificar
     * @return Objeto {@link Rutina} ya actualizado
     * 
     * @since 1.0
     */
    @Override
    public Rutina actualizarRutina(Rutina rutinaActualizada, Long id) {
        // Busca la rutina que se pretende actualizar
        Rutina rutinaExistente = obtenerRutinaPorId(id);

        // Modifica sus atributos
        rutinaExistente.setNombre(rutinaActualizada.getNombre());
        rutinaExistente.setCliente(rutinaActualizada.getCliente());
        rutinaExistente.setEjercicios(rutinaActualizada.getEjercicios());
        rutinaExistente.setFrecuenciaSemanal(rutinaActualizada.getFrecuenciaSemanal());

        // Se guardan las modificaciones en la BBDD
        return rutinaRepository.save(rutinaExistente);
    }

    /**
     * Método para eliminar una rutina de la BBDD
     * <p>
     * Este método permite eliminar una rutina de la BBDD mediante su id
     * </p>
     * 
     * @param id id de la rutina que se desea eliminar
     * 
     * @since 1.0
     */
    @Override
    public void eliminarRutina(Long id) {
        rutinaRepository.deleteById(id);
    }

}
