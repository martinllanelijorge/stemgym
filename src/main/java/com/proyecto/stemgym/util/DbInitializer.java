package com.proyecto.stemgym.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Clase para initializar todos los datos de la BBDD
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private MusculoInitializer musculoInitializer;
    
    @Autowired
    private EjercicioInitializer ejercicioInitializer;
    
    @Autowired
    private ClienteInitializer clienteInitializer;

    @Autowired
    private RutinaInitializer rutinaInitializer;
    
    @Override
    public void run(String... args) {
        inicializarBaseDeDatos();
    }

    /**
     * Método que llama al resto de inicializadores para cargar los datos en la BBDD
     * 
     * @since 1.0
     */
    private void inicializarBaseDeDatos() {
        System.out.println("Iniciando la carga de datos iniciales...");
        musculoInitializer.inicializarMusculos();
        ejercicioInitializer.inicializarEjercicios();
        clienteInitializer.inicializarClientes();
        rutinaInitializer.inicializarRutinas();
        System.out.println("Carga de datos completada.");
    }
}