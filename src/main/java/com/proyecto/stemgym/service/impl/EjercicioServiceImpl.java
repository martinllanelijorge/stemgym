package com.proyecto.stemgym.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;
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
     * y limpia su tabla de músculos secundarios para evitar conflictos de FK.
     * </p>
     *
     * @param id id del ejercicio que se desea eliminar
     * @since 1.0
     */
    @Override
    public void eliminarEjercicio(Long id) {
        Ejercicio ejercicio = obtenerEjercicioPorId(id);
        // Busca las rutinas con esos ejercicios y elimina los ejercicios de esas rutinas
        List<Rutina> rutinas = rutinaRepository.findByEjerciciosId(id);
        for (Rutina rutina : rutinas) {
            rutina.getEjercicios().remove(ejercicio);
        }
        rutinaRepository.saveAll(rutinas);
        // Elimina los ejercicios que tiene los musculos secundarios
        ejercicioRepository.eliminarMusculosSecundarios(id);
        ejercicioRepository.deleteById(id);
    }

    /**
     * Método para eliminar todos los ejercicios asociados a un músculo
     * <p>
     * Elimina los ejercicios que tengan al músculo como principal o como
     * secundario, limpiando previamente sus apariciones en rutinas y en la tabla
     * intermedia de músculos secundarios. Se llama desde {@link MusculoServiceImpl}
     * antes de eliminar el músculo.
     * </p>
     *
     * @param musculo músculo cuyos ejercicios se desean eliminar
     * @since 1.0
     */
    public void eliminarEjerciciosDeMusculo(Musculo musculo) {
        List<Ejercicio> principal = ejercicioRepository.findByMusculoPrincipal(musculo);
        List<Ejercicio> secundario = ejercicioRepository.findByMusculosSecundariosContaining(musculo);

        // Unir sin duplicados
        List<Long> idsEjercicios = new ArrayList<>();
        for (Ejercicio ejercicio : principal) {
            idsEjercicios.add(ejercicio.getId());
        }
        for (Ejercicio ejercicio : secundario) {
            if (!idsEjercicios.contains(ejercicio.getId())) {
                idsEjercicios.add(ejercicio.getId());
            }
        }

        if (!idsEjercicios.isEmpty()) {
            ejercicioRepository.eliminarDeRutinasPorLista(idsEjercicios);
            for (Long ejercicioId : idsEjercicios) {
                ejercicioRepository.eliminarMusculosSecundarios(ejercicioId);
            }
            ejercicioRepository.deleteAllById(idsEjercicios);
        }

        // Limpiar ejercicios que sobreviven pero tenían al músculo como secundario
        ejercicioRepository.eliminarPorMusculoSecundario(musculo.getId());
    }

}
