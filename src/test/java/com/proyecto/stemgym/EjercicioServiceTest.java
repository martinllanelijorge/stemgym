package com.proyecto.stemgym;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;
import com.proyecto.stemgym.repository.MusculoRepository;
import com.proyecto.stemgym.service.EjercicioService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@SpringBootTest
public class EjercicioServiceTest {
    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private MusculoRepository musculoRepository;
    private Ejercicio ejercicio;
    private Musculo musculo;

    // ================ INICIALIZA LA CLASE ================ //
    @BeforeEach
    void inicializar() {
        musculo = new Musculo("nombre musculo", 2, 12, 20,
                "https://m.media-amazon.com/images/I/61WNnhYl2CL._AC_UF894,1000_QL80_.jpg");
        musculoRepository.save(musculo);
        ejercicio = new Ejercicio("Ejercillas", "esto es una descripción", "material", "https://ejemplo/imagen.jpg",
                "https://ejemplo/embeb/JDQQPSDQ01J1EW", musculo, new ArrayList<Musculo>());
    }

    // ================ CREAR UN EJERCICIO EN LA BBDD ================ //
    @Test
    @DisplayName("Debería devolver el mismo ejercicio")
    void crearEjercicio_devuelveTrue_siEsElMismoEjercicio() {
        assertTrue(ejercicio.getNombre().equals(ejercicioService.crearEjercicio(ejercicio).getNombre()));
    }

    // ================ OBTENER TODOS LOS EJERCICIOS ================ //
    @Test
    @DisplayName("Deberían ser iguales si lista anterior le sumo 1 ejercicio")
    void obtenerTodos_devuelveTrue_siDevuelveNumeroTodosEjericicosMasUnoCreado() {
        int numeroEjercicios = ejercicioService.obtenerTodos().size();
        ejercicioService.crearEjercicio(ejercicio);
        assertEquals(numeroEjercicios + 1, ejercicioService.obtenerTodos().size());
    }

    // ================ OBTENER EJERCICIO POR ID ================ //
    // Son el mismo ejercicio
    @Test
    @DisplayName("Debería obtener el mismo ejercicio")
    void obtenerEjercicioPorId_devuelveTrue_siAmbosTienenMismoId() {
        Long id = ejercicioService.crearEjercicio(ejercicio).getId();
        assertTrue(id == ejercicioService.obtenerEjercicioPorId(id).getId());
    }

    // Salta un error si el id no existe
    @Test
    @DisplayName("Debería lanzar una excepción si el id no existe")
    void obtenerEjercicioPorId_lanzaExcepcion_siNoExisteElId() {
        assertThrows(RuntimeException.class, () -> ejercicioService.obtenerEjercicioPorId(-1L));
    }

    // ================ ACTUALIZAR EJERCICIO ================ //
    @Test
    @DisplayName("Debería actualizar el ejercicio")
    void actualizarEjercicio_actualizaNombre_siCambioNombre() {
        Ejercicio ejercicioGuardado = ejercicioService.crearEjercicio(ejercicio);
        Ejercicio ejercicioActualizado = new Ejercicio("Actualizado", "esto es una descripción", "material",
                "https://ejemplo/imagen.jpg",
                "https://ejemplo/embeb/JDQQPSDQ01J1EW", musculo, new ArrayList<Musculo>());
        Ejercicio resultado = ejercicioService.actualizarEjercicio(ejercicioActualizado, ejercicioGuardado.getId());
        assertEquals("Actualizado", resultado.getNombre());
    }

    // ================ ELIMINAR EJERCICIO ================ //
    @Test
    @DisplayName("Debería eliminar el ejercicio")
    void eliminarEjercicio_lanzaExcepcionAlObtenerEjercicio_siEliminaEjercicio() {
        Ejercicio ejercicioGuardado = ejercicioService.crearEjercicio(ejercicio);
        Long id = ejercicioGuardado.getId();
        assertEquals(id, ejercicioService.obtenerEjercicioPorId(id).getId());

        ejercicioService.eliminarEjercicio(id);
        assertThrows(RuntimeException.class, () -> ejercicioService.obtenerEjercicioPorId(id));
    }

    // ================ ELIMINAR EJERCICIO QUE IMPLICA UN MÚSCULO CONCRETO ================ //
    @Test
    @DisplayName("Debería eliminar el ejercicio que implica el músculo")
    void eliminarEjerciciosDeMusculo_lanzaExcepcionAlObtenerEjercicio_siEjercicioEliminadoPorqueTieneElMusculo(){
        Ejercicio ejercicioGuardado = ejercicioService.crearEjercicio(ejercicio);
        Long id = ejercicioGuardado.getId();
        assertEquals(id, ejercicioService.obtenerEjercicioPorId(id).getId());

        // Elimina el ejercicio si tiene el músculo
        ejercicioService.eliminarEjerciciosDeMusculo(musculo);
        assertThrows(RuntimeException.class, () -> ejercicioService.obtenerEjercicioPorId(id));
    }

}