package com.proyecto.stemgym;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;
import com.proyecto.stemgym.service.EjercicioService;
import com.proyecto.stemgym.service.MusculoService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@SpringBootTest
public class MusculoServiceTest {
    @Autowired
    private MusculoService musculoService;
    private Musculo musculo;
    @Autowired
    private EjercicioService ejercicioService;


    // ================ INICIALIZA LA CLASE ================ //
    @BeforeEach
    void inicializar() {
        musculo = new Musculo("nombre musculo", 2, 12, 20,
                "https://m.media-amazon.com/images/I/61WNnhYl2CL._AC_UF894,1000_QL80_.jpg");
    }

    // ============ CREAR MUSCULO =============== //
    @Test
    @DisplayName("Debería devolver el músculo una vez lo crea")
    void crearMusculo_sonIguales_siElNombreEsElMismo() {
        Musculo musculoGuardado = musculoService.crearMusculo(musculo);
        assertEquals(musculo.getNombre(), musculoGuardado.getNombre());
    }

    // ============ OBTENER TODOS LOS MUSCULOS =============== //
    @Test
    @DisplayName("Deberían ser iguales si lista anterior le sumo 1 musculo")
    void obtenerTodos_devuelveTrue_siDevuelveNumeroTodosMusculosMasUnoCreado() {
        int numeroMusculos = musculoService.obtenerTodos().size();
        musculoService.crearMusculo(musculo);
        assertEquals(numeroMusculos + 1, musculoService.obtenerTodos().size());
    }

    // ================ OBTENER MUSCULO POR ID ================ //
    // Son el mismo musculo
    @Test
    @DisplayName("Debería obtener el mismo musculo")
    void obtenerMusculoPorId_devuelveTrue_siAmbosTienenMismoId() {
        Long id = musculoService.crearMusculo(musculo).getId();
        assertTrue(id == musculoService.obtenerMusculoPorId(id).getId());
    }

    // Salta un error si el id no existe
    @Test
    @DisplayName("Debería lanzar una excepción si el id no existe")
    void obtenerMusculoPorId_lanzaExcepcion_siNoExisteElId() {
        assertThrows(RuntimeException.class, () -> musculoService.obtenerMusculoPorId(-1L));
    }

    // ================ ACTUALIZAR MUSCULO ================ //
    @Test
    @DisplayName("Debería actualizar el musculo")
    void actualizarMusculo_actualizaNombre_siCambioNombre() {
        Musculo musculoGuardado = musculoService.crearMusculo(musculo);
        Musculo musculoActualizado = new Musculo("Actualizado", 2, 12, 20,
                "https://m.media-amazon.com/images/I/61WNnhYl2CL._AC_UF894,1000_QL80_.jpg");
        Musculo resultado = musculoService.actualizarMusculo(musculoActualizado, musculoGuardado.getId());
        assertEquals("Actualizado", resultado.getNombre());
    }

    // ================ ELIMINAR MUSCULO ================ //
    @Test
    @DisplayName("Debería eliminar el músculo y el ejercicio que tiene el músculo")
    void eliminarMusculo_eliminaMusculoYEjercicio_siEjercicioTieneMusculoYseEliminaMusculo(){
        Musculo musculoGuardado = musculoService.crearMusculo(musculo);

        Ejercicio ejercicio = new Ejercicio("Ejercillas", "esto es una descripción", "material", "https://ejemplo/imagen.jpg",
                "https://ejemplo/embeb/JDQQPSDQ01J1EW", musculoGuardado, new ArrayList<Musculo>());
        Ejercicio ejercicioGuardado = ejercicioService.crearEjercicio(ejercicio);

        // Elimina el músculo
        musculoService.eliminarMusculo(musculoGuardado.getId());

        // Excepciones
        assertThrows(RuntimeException.class, () -> musculoService.obtenerMusculoPorId(musculoGuardado.getId()));
        assertThrows(RuntimeException.class, () -> ejercicioService.obtenerEjercicioPorId(ejercicioGuardado.getId()));
    }

}
