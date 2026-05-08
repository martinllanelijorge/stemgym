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

    /**
     * Método para actualizar un músculo
     * <p>
     * Este método permite actualizar un músculo por su id, modificando los
     * atributos más relevantes
     * </p>
     * 
     * @param musculoActualizado músculo con los atributos modificados
     * @param id                 id del músculo que se pretende modificar
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

    @Override
    public void crearMusculo(Musculo musculo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eliminarMusculo(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Musculo obtenerMusculoPorId(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Musculo> obtenerTodos() {
        // TODO Auto-generated method stub
        return null;
    }

}
