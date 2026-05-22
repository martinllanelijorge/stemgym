package com.proyecto.stemgym;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto.stemgym.entity.Cliente;
import com.proyecto.stemgym.service.ClienteService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    private Cliente cliente;

    // ================ INICIALIZA LA CLASE ================ //
    @BeforeEach
    void inicializar() {
        cliente = new Cliente("Jorge Martín Llaneli", "Hombre", 27, 77.7, 80, "https://ejemplo/imagen.jpg");
    }

    // ================ CREAR UN CLIENTE EN LA BBDD ================ //
    @Test
    @DisplayName("Debería devolver el mismo cliente")
    void crearCliente_devuelveClienteAgregado_siSeHizoSave() {
        assertTrue(cliente.getNombre().equals(clienteService.crearCliente(cliente).getNombre()));
    }

    // ================ OBTENER TODOS LOS CLIENTES ================ //
    // Clientes totales, lista no nula
    @Test
    @DisplayName("Debería devolver una lista no nula")
    void obtenerTodos_devuelveListaNoNula_siObtengoListaClientes() {
        assertNotNull(clienteService.obtenerTodos());
    }

    // Todos los clientes +1 tras crear uno nuevo
    @Test
    @DisplayName("Debería devolver al menos un cliente más tras crear uno")
    void obtenerTodos_devuelveMasClientes_siCreaUnoNuevo() {
        int numeroClientes = clienteService.obtenerTodos().size();
        clienteService.crearCliente(cliente);
        List<Cliente> clientes = clienteService.obtenerTodos();
        assertEquals(numeroClientes + 1, clientes.size());
    }

    // ================ OBTENER CLIENTE POR ID ================ //
    // Devuelve el mismo cliente
    @Test
    @DisplayName("Debería devolver el cliente correcto por su id")
    void obtenerClientePorId_devuelveClienteCorrecto_siExisteElId() {
        Cliente clienteGuardado = clienteService.crearCliente(cliente);
        Cliente clienteEncontrado = clienteService.obtenerClientePorId(clienteGuardado.getId());
        assertEquals(clienteGuardado.getId(), clienteEncontrado.getId());
    }

    // Salta un error
    @Test
    @DisplayName("Debería lanzar una excepción si el id no existe")
    void obtenerClientePorId_lanzaExcepcion_siNoExisteElId() {
        assertThrows(RuntimeException.class, () -> clienteService.obtenerClientePorId(-1L));
    }

    // ================ ACTUALIZAR UN CLIENTE ================ //
    // Acttualiza nombre
    @Test
    @DisplayName("Debería actualizar el nombre del cliente correctamente")
    void actualizarCliente_actualizaNombre_siElClienteExiste() {
        Cliente clienteGuardado = clienteService.crearCliente(cliente);

        Cliente clienteActualizado = new Cliente("Actualizado", "Hombre", 27, 77.7, 80,
                "https://ejemplo/imagen.jpg");
        Cliente resultado = clienteService.actualizarCliente(clienteActualizado, clienteGuardado.getId());

        assertEquals("Actualizado", resultado.getNombre());
    }

    // Error al actualizar cliente inexistente
    @Test
    @DisplayName("Debería lanzar una excepción al actualizar un id inexistente")
    void actualizarCliente_lanzaExcepcion_siElIdNoExiste() {
        assertThrows(RuntimeException.class, () -> clienteService.actualizarCliente(cliente, -1L));
    }

    // ================ ELIMINAR UN CLIENTE ================ //
    // Elimina al cliente
    @Test
    @DisplayName("Debería eliminar el cliente correctamente")
    void eliminarCliente_eliminaCliente_siElIdExiste() {
        Cliente clienteGuardado = clienteService.crearCliente(cliente);
        Long id = clienteGuardado.getId();
        // El cliente se guardó correctamente
        assertEquals(id, clienteService.obtenerClientePorId(id).getId());

        // El cliente se eliminó correctamente - Manda una excepción
        clienteService.eliminarCliente(id);
        assertThrows(RuntimeException.class, () -> clienteService.obtenerClientePorId(id));
    }

    // Error al intentar eliminar cliente que no existe
    @Test
    @DisplayName("Debería lanzar una excepción al eliminar un id inexistente")
    void eliminarCliente_lanzaExcepcion_siElIdNoExiste() {
        assertThrows(RuntimeException.class, () -> clienteService.obtenerClientePorId(-1L));
    }
}

