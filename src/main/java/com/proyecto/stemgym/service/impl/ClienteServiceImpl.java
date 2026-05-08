package com.proyecto.stemgym.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.stemgym.entity.Cliente;
import com.proyecto.stemgym.repository.ClienteRepository;
import com.proyecto.stemgym.service.ClienteService;

/**
 * Clase que implementa {@link ClienteService} para desarrollar los métodos
 * GET,
 * POST, PUT y DELETE.
 * 
 * <p>
 * Esta clase se encarga de desarrollar la lógica interna de lo que devuelven
 * cada uno de los métodos para el GET, POST, PUT y DELETE de la API en lo
 * referente a la entidad {@link Cliente}
 * </p>
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Método para obtener todos los clientes existentes en la BBDD
     * 
     * @return Objetos {@link Cliente} encontrados
     * 
     * @since 1.0
     */
    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    /**
     * Método para obtener un cliente por su id
     * <p>
     * Este método devuelve un cliente mediante su búsqueda por id, lanza un error
     * cuando no lo encuentra
     * </p>
     * 
     * @param id id del cliente que se desea encontrar
     * @return Objeto {@link Cliente} encontrado
     * @throws RuntimeException si no encuentra el cliente
     * @since 1.0
     */
    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("¡ERROR! Cliente no encontrado"));
    }

    /**
     * Método para crear un cliente nuevo
     * 
     * @param cliente cliente nuevo
     * @return Objeto {@link Cliente} creado
     * @since 1.0
     */
    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Método para actualizar un cliente
     * <p>
     * Este método permite actualizar un cliente existente en la BBDD, modificando
     * sus atributos
     * </p>
     * 
     * @param clienteActualizado cliente con los atributos ya modificados
     * @param id                 id del cliente que se desea modificar
     * @return Objeto {@link Cliente} ya actualizado
     * 
     * @since 1.0
     */
    @Override
    public Cliente actualizarCliente(Cliente clienteActualizado, Long id) {
        // Busca el cliente
        Cliente clienteExistente = obtenerClientePorId(id);

        // Modifica sus atributos
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setGenero(clienteActualizado.getGenero());
        clienteExistente.setEdad(clienteActualizado.getEdad());
        clienteExistente.setPesoActual(clienteActualizado.getPesoActual());
        clienteExistente.setPesoObjetivo(clienteActualizado.getPesoObjetivo());
        clienteExistente.setObjetivoAlcanzado(clienteActualizado.isObjetivoAlcanzado());
        clienteExistente.setUrlImagen(clienteActualizado.getUrlImagen());
        clienteExistente.setRutinas(clienteActualizado.getRutinas());

        // Guarda las modificaciones
        return clienteRepository.save(clienteExistente);
    }

    /**
     * Método para eliminar un cliente de la BBDD
     * <p>
     * Este método permite eliminar un cliente de la BBDD mediante su id
     * </p>
     * 
     * @param id id del cliente que se desea eliminar
     * 
     * @since 1.0
     */
    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
