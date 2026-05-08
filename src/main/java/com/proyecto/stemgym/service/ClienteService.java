package com.proyecto.stemgym.service;

import java.util.List;

import com.proyecto.stemgym.entity.Cliente;

/**
 * Interfaz para {@link ClienteServiceImpl}
 * <p>
 * Esta interfaz permite visualizar más rápidamente los métodos que contiene
 * {@link ClienteServiceImpl}
 * </p>
 */
public interface ClienteService {
    List<Cliente> obtenerTodos();

    Cliente obtenerClientePorId(Long id);

    Cliente crearCliente(Cliente cliente);

    Cliente actualizarCliente(Cliente clienteActualizado, Long id);

    void eliminarCliente(Long id);
}
