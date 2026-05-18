package com.proyecto.stemgym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Rutina;
import com.proyecto.stemgym.repository.EjercicioRepository;
import com.proyecto.stemgym.repository.RutinaRepository;
import com.proyecto.stemgym.service.EjercicioService;

/**
 * Clase que implementa {@link EjercicioService} para desarrollar los métodos
 * GET,
 * POST, PUT y DELETE.
 * 
 * <p>
 * Esta clase se encarga de desarrollar la lógica interna de lo que devuelven
 * cada uno de los métodos para el GET, POST, PUT y DELETE de la API en lo
 * referente a la entidad {@link Ejercicio}
 * </p>
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Service
public class EjercicioServiceImpl implements EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

    /**
     * Método para obtener todos los ejercicios existentes en la BBDD
     * 
     * @return Objetos {@link Ejercicio} encontrados
     * 
     * @since 1.0
     */
    @Override
    public List<Ejercicio> obtenerTodos() {
        return ejercicioRepository.findAll();
    }

    /**
     * Método para obtener un ejercicio por su id
     * <p>
     * Este método devuelve un ejercicio mediante su búsqueda por id, lanza un error
     * cuando no lo encuentra
     * </p>
     * 
     * @param id id del ejercicio que se desea encontrar
     * @return Objeto {@link Ejercicio} encontrado
     * @throws RuntimeException si no encuentra el ejercicio
     * @since 1.0
     */
    @Override
    public Ejercicio obtenerEjercicioPorId(Long id) {
        return ejercicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡ERROR! Ejercicio no encontrado"));
    }

    /**
     * Método para crear un nuevo ejercicio
     * 
     * @param ejercicio ejercicio nuevo
     * @return Objeto {@link Ejercicio} creado
     * @since 1.0
     */
    @Override
    public Ejercicio crearEjercicio(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }

    /**
     * Método para actualizar un ejercicio
     * <p>
     * Este método permite actualizar un ejercicio existente en la BBDD, modificando
     * sus atributos
     * </p>
     * 
     * @param ejercicioActualizado ejercicio con los atributos ya modificados
     * @param id                   id del ejercicio que se desea modificar
     * @return Objeto {@link Ejercicio} ya actualizado
     * 
     * @since 1.0
     */
    @Override
    public Ejercicio actualizarEjercicio(Ejercicio ejercicioActualizado, Long id) {
        // Busca el ejercicio que se desea actualizar
        Ejercicio ejercicioExistente = obtenerEjercicioPorId(id);

        // Se modifican sus atributos
        ejercicioExistente.setNombre(ejercicioActualizado.getNombre());
        ejercicioExistente.setDescripcion(ejercicioActualizado.getDescripcion());
        ejercicioExistente.setMaterial(ejercicioActualizado.getMaterial());
        ejercicioExistente.setMusculoPrincipal(ejercicioActualizado.getMusculoPrincipal());
        ejercicioExistente.setMusculosSecundarios(ejercicioActualizado.getMusculosSecundarios());
        ejercicioExistente.setUrlImagen(ejercicioActualizado.getUrlImagen());
        ejercicioExistente.setUrlVideo(ejercicioActualizado.getUrlVideo());

        // Se actualiza el ejercicio en la BBDD
        return ejercicioRepository.save(ejercicioExistente);
    }

    /**
     * Método para eliminar un ejercicio de la BBDD
     * <p>
     * Este método permite eliminar un ejercicio de la BBDD mediante su id.
     * Antes de eliminarlo, lo elimina de todas las rutinas que lo contengan
     * para evitar conflictos con la tabla intermedia rutina_ejercicio.
     * </p>
     *
     * @param id id del ejercicio que se desea eliminar
     * @since 1.0
     */
    @Override
    public void eliminarEjercicio(Long id) {
        Ejercicio ejercicio = obtenerEjercicioPorId(id);
        List<Rutina> rutinas = rutinaRepository.findByEjerciciosId(id);
        // De cada rutina que contenga el ejercicio, elimina el ejercicio
        for (Rutina rutina : rutinas) {
            rutina.getEjercicios().remove(ejercicio);
        }
        rutinaRepository.saveAll(rutinas);
        ejercicioRepository.deleteById(id);
    }

}
