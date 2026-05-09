package com.proyecto.stemgym.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.proyecto.stemgym.config.InitializationConfig;
import com.proyecto.stemgym.controller.ClienteController;
import com.proyecto.stemgym.entity.Cliente;

import jakarta.transaction.Transactional;

/**
 * Clase donde tiene lugar la inicialización de los clientes base que
 * aparecerán en la API
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Component
public class CienteInitializer {

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private InitializationConfig config;

    private final Faker faker = new Faker(new Locale("es"));

    /**
     * Método para inicializar los clientes de la API
     * <p>
     * Este método hace que la API comience con una serie de clientes random
     * predefinidos
     * </p>
     * 
     * @see Cliente
     * 
     * @sice 1.0
     */
    @Transactional
    public void inizializarClientes() {
        // Creación de los clientes
        for (int i = 0; i < config.getNumeroInicialDeClientes(); i++) {
            Cliente nuevoCliente = crearClienteAleatorio();
            clienteController.crearNuevoCliente(nuevoCliente);
        }
    }

    /**
     * Crea un cliente con datos generados aleatoriamente usando JavaFaker.
     *
     * @return un {@link Cliente} con atributos generados aleatoriamente
     * @see Cliente
     * 
     * @since 1.0
     */
    private Cliente crearClienteAleatorio() {
        // Creación de los parámetros para el constructor
        String nombreCompleto = faker.name().fullName();
        String genero = faker.options().option("Hombre", "Mujer", "Otro");
        int edad = faker.number().numberBetween(config.getEdadMinimaClientesIniciales(), config.getEdadMaximaClientesIniciales()); // Edades minimas y maximas
        double pesoActual = faker.number().randomDouble(1, config.getPesoMinimoClientesIniciales(), config.getPesoMaximoClientesIniciales()); // Entre 45 y 115 Kg
        double pesoObjetivo = faker.number().randomDouble(1, config.getPesoMinimoClientesObjetivo(), config.getPesoMaximoObjetivoIniciales()); // Entre 50 y 90 Kg
        String avatar = faker.internet().avatar();

        // Creación del cliente con características aleatorias
        Cliente clienteAleatorio = new Cliente(nombreCompleto, genero, edad, pesoActual, pesoObjetivo, avatar);
        return clienteAleatorio;
    }
}
