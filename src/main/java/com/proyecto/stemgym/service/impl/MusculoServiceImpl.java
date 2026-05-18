package com.proyecto.stemgym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.stemgym.entity.Musculo;
import com.proyecto.stemgym.repository.MusculoRepository;
import com.proyecto.stemgym.service.MusculoService;

/**
 * Clase que implementa {@link MusculoService} para desarrollar los métodos GET,
 * POST, PUT y DELETE.
 * 
 * <p>
 * Esta clase se encarga de desarrollar la lógica interna de lo que devuelven
 * cada uno de los métodos para el GET, POST, PUT y DELETE de la API en lo
 * referente a la entidad {@link Musculo}
 * </p>
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Service
public class MusculoServiceImpl implements MusculoService {

    @Autowired
    private MusculoRepository musculoRepository;

    @Autowired
    private EjercicioServiceImpl ejercicioServiceImpl;

    /**
     * Método para actualizar un músculo
     * <p>
     * Este método permite actualizar un músculo por su id, modificando los
     * atributos más relevantes
     * </p>
     * 
     * @param musculoActualizado músculo con los atributos modificados
     * @param id                 id del músculo que se pretende modificar
     * @return Objeto {@link Musculo} ya actualizado
     * 
     * @see Musculo
     * @since 1.0
     */
    @Override
    public Musculo actualizarMusculo(Musculo musculoActualizado, Long id) {
        // Se obtiene el músculo existente
        Musculo musculoExistente = obtenerMusculoPorId(id);

        // Modifica los atributos del musculo por los del musculo actualizado
        musculoExistente.setFrecuenciaRecomendada(musculoActualizado.getFrecuenciaRecomendada());
        musculoExistente.setMinVolumenSemanal(musculoActualizado.getMinVolumenSemanal());
        musculoExistente.setMaxVolumenSemanal(musculoActualizado.getMaxVolumenSemanal());
        musculoExistente.setNombre(musculoActualizado.getNombre());
        musculoExistente.setUrlImagen(musculoActualizado.getUrlImagen());

        // Guarda las modificaciones
        return musculoRepository.save(musculoExistente);

    }

    /**
     * Método para crear un nuevo músculo
     * 
     * @param musculo músculo nuevo
     * @return Objeto {@link Musculo} creado
     * @since 1.0
     */
    @Override
    public Musculo crearMusculo(Musculo musculo) {
        return musculoRepository.save(musculo);

    }

    /**
     * Método para eliminar un músculo de la BBDD
     * <p>
     * Antes de eliminarlo, elimina todos los ejercicios que lo tengan como
     * músculo principal o secundario, limpiando también sus apariciones en rutinas.
     * </p>
     *
     * @param id id del músculo que se desea eliminar
     * @since 1.0
     */
    @Override
    public void eliminarMusculo(Long id) {
        Musculo musculo = obtenerMusculoPorId(id);
        ejercicioServiceImpl.eliminarEjerciciosDeMusculo(musculo);
        musculoRepository.deleteById(id);
    }

    /**
     * Método para obtener un músculo por su id
     * <p>
     * Este método devuelve un músculo mediante su búsqueda por id, lanza un error
     * cuando no lo encuentra
     * </p>
     * 
     * @param id id del músculo que se desea encontrar
     * @return Objeto {@link Musculo} encontrado
     * @throws RuntimeException si no encuentra el músculo
     * @since 1.0
     */
    @Override
    public Musculo obtenerMusculoPorId(Long id) {
        return musculoRepository.findById(id).orElseThrow(() -> new RuntimeException("¡ERROR! Músculo no encontrado"));
    }

    /**
     * Método para obtener todos los músculos existentes en la BBDD
     * 
     * @return Objetos {@link Musculo} encontrados
     * 
     * @since 1.0
     */
    @Override
    public List<Musculo> obtenerTodos() {
        return musculoRepository.findAll();
    }

}
