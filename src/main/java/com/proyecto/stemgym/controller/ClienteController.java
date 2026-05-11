package com.proyecto.stemgym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.stemgym.entity.Cliente;
import com.proyecto.stemgym.entity.Rutina;
import com.proyecto.stemgym.service.ClienteService;

/**
 * Clase para mapear las peticiones y envíos de la api (GET, POT, PUT y DELETE)
 * para los datos referentes a la entidad {@link Cliente}
 * 
 * @see Cliente
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@RestController
@RequestMapping("/stemgymapi/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Método GET de la API para obtener todos los objetos {@link Cliente}
     * 
     * @return lista de {@link Cliente}
     * 
     * @since 1.0
     */
    @GetMapping
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteService.obtenerTodos();
    }

    /**
     * Método GET de la API para obtener el {@link Cliente} por id
     * 
     * @param id del cliente
     * @return devuelve el {@link Cliente} buscado
     * 
     * @since 1.0
     */
    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id);
    }

    /**
     * Método GET de la API para obtener las {@link Rutina} del cliente por id
     * 
     * @param id del cliente
     * @return devuelve un LIst<{@link Rutina}> del cliente buscado
     * 
     * @since 1.0
     */
    @GetMapping("/{id}/rutinas")
    public List<Rutina> obtenerRutinasCliente(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id).getRutinas();
    }

    /**
     * Método POST de la API para crear un nuevo {@link Cliente}
     * 
     * @param cliente cliente que se desea crear
     * @return devuelve el nuevo cliente
     * 
     * @since 1.0
     */
    @PostMapping
    public Cliente crearNuevoCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    /**
     * Método PUT de la API para actualizar un {@link Cliente} existente
     * 
     * @param clienteActualizado cliente con datos ya actualizados
     * @param id                 id del cliente que se pretende actualizar
     * @return devuelve el cliente actualizado
     */
    @PutMapping("/{id}")
    public Cliente actualizarCliente(@RequestBody Cliente clienteActualizado, @PathVariable Long id) {
        return clienteService.actualizarCliente(clienteActualizado, id);
    }

    /**
     * Método DELETE de la API para eliminar un {@link Cliente}
     * 
     * @param id id del cliente que se desea eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminarEjercicio(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }
}
