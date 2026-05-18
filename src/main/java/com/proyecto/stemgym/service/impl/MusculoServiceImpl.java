package com.proyecto.stemgym.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;
import com.proyecto.stemgym.entity.Rutina;
import com.proyecto.stemgym.repository.EjercicioRepository;
import com.proyecto.stemgym.repository.MusculoRepository;
import com.proyecto.stemgym.repository.RutinaRepository;
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
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

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
     * Este método permite eliminar un músculo de la BBDD mediante su id.
     * Antes de eliminarlo, elimina los ejercicios afectados (donde el músculo
     * aparece como principal o secundario) de las rutinas que los contengan,
     * y después borra dichos ejercicios. Los ejercicios donde el músculo es
     * principal se eliminan por la cascada definida en {@link Musculo}.
     * </p>
     *
     * @param id id del músculo que se desea eliminar
     * @since 1.0
     */
    @Override
    public void eliminarMusculo(Long id) {
        Musculo musculo = obtenerMusculoPorId(id);

        List<Ejercicio> ejerciciosConSecundario = ejercicioRepository.findByMusculosSecundariosContaining(musculo);
        List<Ejercicio> ejerciciosPrincipal = ejercicioRepository.findByMusculoPrincipal(musculo);

        List<Ejercicio> todosAfectados = new ArrayList<>();
        todosAfectados.addAll(ejerciciosConSecundario);
        todosAfectados.addAll(ejerciciosPrincipal);

        // Quita los ejercicios afectados de todas las rutinas y guarda
        List<Rutina> todasLasRutinas = rutinaRepository.findAll();
        for (Rutina rutina : todasLasRutinas) {
            rutina.getEjercicios().removeAll(todosAfectados);
        }
        rutinaRepository.saveAll(todasLasRutinas);

        // Borra los ejercicios secundarios (los principales los borra la
        // cascada que ya se puso en musculo)
        ejercicioRepository.deleteAll(ejerciciosConSecundario);

        // Borrar el músculo (cascada borra los ejercicios principales)
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
