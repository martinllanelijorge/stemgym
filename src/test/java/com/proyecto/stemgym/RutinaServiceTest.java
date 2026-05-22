package com.proyecto.stemgym;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto.stemgym.entity.Cliente;
import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;
import com.proyecto.stemgym.entity.Rutina;
import com.proyecto.stemgym.repository.ClienteRepository;
import com.proyecto.stemgym.repository.EjercicioRepository;
import com.proyecto.stemgym.repository.MusculoRepository;
import com.proyecto.stemgym.service.RutinaService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RutinaServiceTest {
    @Autowired
    private RutinaService rutinaService;
    private Rutina rutina;
    private Cliente cliente;
    @Autowired
    private ClienteRepository clienteRepository;
    private Ejercicio ejercicio;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    private Musculo musculo;
    @Autowired
    private MusculoRepository musculoRepository;

    // ================ INICIALIZA LA CLASE ================ //
    @BeforeEach
    void inicializar() {
        musculo = new Musculo("nombre musculo", 2, 12, 20,
                "https://m.media-amazon.com/images/I/61WNnhYl2CL._AC_UF894,1000_QL80_.jpg");
        musculoRepository.save(musculo);
        ejercicio = new Ejercicio("Ejercillas", "esto es una descripción", "material", "https://ejemplo/imagen.jpg",
                "https://ejemplo/embeb/JDQQPSDQ01J1EW", musculo, new ArrayList<Musculo>());
        ejercicioRepository.save(ejercicio);
        cliente = new Cliente("Jorge Martín Llaneli", "Hombre", 27, 77.7, 80, "https://ejemplo/imagen.jpg");
        clienteRepository.save(cliente);
        rutina = new Rutina("nombre rutina", 2, cliente, new ArrayList<Ejercicio>());

    }

    // ============ CREAR MUSCULO =============== //
    @Test
    @DisplayName("Debería devolver la rutina una vez la crea")
    void crearRutina_sonIguales_siNombreEsElMismo() {
        Rutina rutinaGuardada = rutinaService.crearRutina(rutina);
        assertEquals(rutina.getNombre(), rutinaGuardada.getNombre());
    }

    // ============ OBTENER TODOS LAS RUTINAS =============== //
    @Test
    @DisplayName("Deberían ser iguales si lista anterior le sumo 1 rutina")
    void obtenerTodos_devuelveTrue_siDevuelveNumeroTodasRutinasMasUnaCreado() {
        int numeroRutinas = rutinaService.obtenerTodos().size();
        rutinaService.crearRutina(rutina);
        assertEquals(numeroRutinas + 1, rutinaService.obtenerTodos().size());
    }

    // ================ OBTENER RUTINA POR ID ================ //
    // Son el mismo musculo
    @Test
    @DisplayName("Debería obtener la misma rutina")
    void obtenerRutinaPorId_devuelveTrue_siAmbosTienenMismoId() {
        Long id = rutinaService.crearRutina(rutina).getId();
        assertTrue(id == rutinaService.obtenerRutinaPorId(id).getId());
    }

    // Salta un error si el id no existe
    @Test
    @DisplayName("Debería lanzar una excepción si el id no existe")
    void obtenerRutinaPorId_lanzaExcepcion_siNoExisteElId() {
        assertThrows(RuntimeException.class, () -> rutinaService.obtenerRutinaPorId(-1L));
    }

    // ================ ACTUALIZAR RUTINA ================ //
    @Test
    @DisplayName("Debería actualizar la rutina")
    void actualizarRutina_actualizaNombreYEjerciciosYCliente_siCambioNombreEjerciciosCliente() {
        Rutina rutinaGuardada = rutinaService.crearRutina(rutina);
        assertTrue(rutinaGuardada.getEjercicios().size() == 0);

        List<Ejercicio> listaEjercicios = new ArrayList<>();
        listaEjercicios.add(ejercicio);

        // Crea cliente nuevo
        Cliente cliente2 = new Cliente("nuevo cliente", "Hombre", 27, 77.7, 80, "https://ejemplo/imagen.jpg");
        clienteRepository.save(cliente2);

        // Modifica nombre, cliente, ejercicios
        Rutina rutinaActualizada = new Rutina("Actualizada", 2, cliente2, listaEjercicios);
        Rutina resultado = rutinaService.actualizarRutina(rutinaActualizada, rutinaGuardada.getId());

        // Comprueba que tenga el nombre cambiado y un ejercicio con nombre Ejercillas
        assertEquals("Actualizada", resultado.getNombre());
        assertTrue(rutinaActualizada.getEjercicios().size() == 1);
        assertEquals("Ejercillas", resultado.getEjercicios().get(0).getNombre());
        assertEquals("nuevo cliente", resultado.getCliente().getNombre());
    }

    // ================ ELIMINAR EJERCICIO ================ //
    @Test
    @DisplayName("Debería eliminar la rutina")
    void eliminarRutina_lanzaExcepcionAlObtenerRutina_siEliminaRutina() {
        Rutina rutinaGuardada = rutinaService.crearRutina(rutina);
        Long id = rutinaGuardada.getId();
        assertEquals(id, rutinaService.obtenerRutinaPorId(id).getId());

        rutinaService.eliminarRutina(id);
        assertThrows(RuntimeException.class, () -> rutinaService.obtenerRutinaPorId(id));
    }
}
