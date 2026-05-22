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
import java.util.List;

@SpringBootTest
public class EjercicioServiceTest {
    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private MusculoRepository musculoRepository;
    private Ejercicio ejercicio;
    private Musculo musculo;
    private Musculo musculoSecundario;

    // ================ INICIALIZA LA CLASE ================ //
    @BeforeEach
    void inicializar() {
        musculo = new Musculo("nombre musculo", 2, 12, 20,
                "https://m.media-amazon.com/images/I/61WNnhYl2CL._AC_UF894,1000_QL80_.jpg");
        musculoSecundario = new Musculo("nombre musculo secundario", 2, 12, 20,
                "https://m.media-amazon.com/images/I/61WNnhYl2CL._AC_UF894,1000_QL80_.jpg");
        musculoRepository.save(musculo);
        musculoRepository.save(musculoSecundario);
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
    void actualizarEjercicio_actualizaNombreYMusculoSecundario_siCambioNombreYMusculoSecundario() {
        // Debería tener 0 músculos secundarios
        Ejercicio ejercicioGuardado = ejercicioService.crearEjercicio(ejercicio);
        assertTrue(ejercicioGuardado.getMusculosSecundarios().size() == 0);


        // Añade musculoSecundario a la lista de secundarios y cambia el nombre a actualizado
        List<Musculo> listaSecundarios = new ArrayList<>();
        listaSecundarios.add(musculoSecundario);
        Ejercicio ejercicioActualizado = new Ejercicio("Actualizado", "esto es una descripción", "material",
                "https://ejemplo/imagen.jpg",
                "https://ejemplo/embeb/JDQQPSDQ01J1EW", musculo, listaSecundarios);

        Ejercicio resultado = ejercicioService.actualizarEjercicio(ejercicioActualizado, ejercicioGuardado.getId());

        // Debería cambiar el nombre, añadir el músculo secundario
        assertEquals("Actualizado", resultado.getNombre());
        assertTrue(resultado.getMusculosSecundarios().size() == 1);
        assertEquals("nombre musculo secundario", resultado.getMusculosSecundarios().get(0).getNombre());
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

    // ================ ELIMINAR EJERCICIO QUE IMPLICA UN MÚSCULO CONCRETO
    // ================ //
    @Test
    @DisplayName("Debería eliminar el ejercicio que implica el músculo")
    void eliminarEjerciciosDeMusculo_lanzaExcepcionAlObtenerEjercicio_siEjercicioEliminadoPorqueTieneElMusculo() {
        Ejercicio ejercicioGuardado = ejercicioService.crearEjercicio(ejercicio);
        Long id = ejercicioGuardado.getId();
        assertEquals(id, ejercicioService.obtenerEjercicioPorId(id).getId());

        // Elimina el ejercicio si tiene el músculo
        ejercicioService.eliminarEjerciciosDeMusculo(musculo);
        assertThrows(RuntimeException.class, () -> ejercicioService.obtenerEjercicioPorId(id));
    }

}