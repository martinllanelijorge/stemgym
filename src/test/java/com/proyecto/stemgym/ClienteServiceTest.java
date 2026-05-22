package com.proyecto.stemgym;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto.stemgym.config.InitializationConfig;
import com.proyecto.stemgym.entity.Cliente;
import com.proyecto.stemgym.service.ClienteService;
import com.proyecto.stemgym.service.impl.ClienteServiceImpl;

import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    private Cliente cliente;
    private InitializationConfig initializationConfig;

    // ================ INICIALIZA LA CLASE ================ //
    @BeforeEach
    void inicializar() {
        cliente = new Cliente("Jorge Martín Llaneli", "Hombre", 27, 77.7, 80, "https://ejemplo/imagen.jpg");
        initializationConfig = new InitializationConfig();
    }

    // ================ CREAR UN CLIENTE EN LA BBDD ================ //
    @Test
    @DisplayName("Debería devolver el mismo cliente")
    void crearCliente_devuelveClienteAgregado_siSeHizoSave() {
        assertTrue(cliente.getNombre() == clienteService.crearCliente(cliente).getNombre());
    }

    // ================ OBTENER TODOS LOS CLIENTES ================ //
    // Clientes totales, lista no nula
    @Test
    @DisplayName("Debería devolver una lista no nula")
    void obtenerTodos_devuelveListaNoNula_siObtengoListaClientes() {
        assertNotNull(clienteService.obtenerTodos());
    }

    // Lista con clientes
    @Test
    @DisplayName("Debería devolver una lista de tamaño indicado en initializationConfig.getNumeroInicialDeClientes()")
    void obtenerTodos_devuelveListaConClientes_siObtengoListaClientes() {
        assertEquals(initializationConfig.getNumeroInicialDeClientes(), clienteService.obtenerTodos().size());
    }

    // Todos los clientes +1 tras crear uno nuevo
    @Test
    @DisplayName("Debería devolver al menos un cliente más que initializationConfig.getNumeroInicialDeClientes() tras crear uno")
    void obtenerTodos_devuelveMasClientes_siCreaUnoNuevo() {
        clienteService.crearCliente(cliente);
        List<Cliente> clientes = clienteService.obtenerTodos();
        assertEquals(initializationConfig.getNumeroInicialDeClientes() + 1, clientes.size());
    }

    // ================ OBTENER CLIENTE POR ID ================ //

}
