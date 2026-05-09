package com.proyecto.stemgym.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.proyecto.stemgym.config.InitializationConfig;
import com.proyecto.stemgym.controller.ClienteController;
import com.proyecto.stemgym.controller.EjercicioController;
import com.proyecto.stemgym.controller.RutinaController;
import com.proyecto.stemgym.entity.Cliente;
import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Rutina;

import jakarta.transaction.Transactional;

/**
 * Clase donde tiene lugar la inicialización de las rutinas asociadas a lo
 * clientes de base que aparecerán en la API
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Component
public class RutinaInitializer {

    @Autowired
    private RutinaController rutinaController;

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private EjercicioController ejercicioController;

    @Autowired
    private InitializationConfig config;

    private final Faker faker = new Faker(new Locale("es"));

    /**
     * Método para inicializar las rutinas de la API
     * <p>
     * Este método hace que la API comience con una serie de rutinas random
     * predefinidas y personalizadas para los clientes creados
     * </p>
     * 
     * @see Cliente
     * 
     * @since 1.0
     */
    @Transactional
    public void inicializarRutinas() {
        for (int i = 0; i < config.getNumeroInicialDeRutinas(); i++) {
            // Se crea una lista aleatoria de ejercicios y se obtiene un cliente aleatorio
            Rutina nuevaRutina = crearRutinaAleatoria(obtenerUnClienteAleatorio(), obtenerListaAleatoriaEjercicios());

            // Se almacena la rutina
            rutinaController.crearNuevaRutina(nuevaRutina);
        }
    }

    /**
     * Método que obtiene al azar un cliente creado previamente de una lista de
     * clientes
     * 
     * @return devuelve un cliente al azar
     * 
     * @since 1.0
     */
    private Cliente obtenerUnClienteAleatorio() {
        // Se obtienen todos los clientes
        List<Cliente> todosLosClientes = clienteController.obtenerTodosLosClientes();

        // Se escoge un cliente al azar
        Cliente clienteAleatorio = todosLosClientes.get(faker.number().numberBetween(0, todosLosClientes.size()));

        return clienteAleatorio;
    }

    /**
     * Método para generar una lista aleatoria de ejercicios cuyo número máximo y
     * mínimo viene determinado en {@link InitializationConfig}
     * 
     * @return devuelve una lista de {@link Ejercicio} aleatoria
     * 
     * @since 1.0
     */
    private List<Ejercicio> obtenerListaAleatoriaEjercicios() {

        List<Ejercicio> listaEjerciciosAleatoria = new ArrayList<>();
        int numeroEjerciciosAleatorio = faker.number().numberBetween(config.getNumeroMinimoEjerciciosEnRutinaInicial(),
                config.getNumeroMaximoEjerciciosEnRutinaInicial() + 1);

        // Se obtienen todos los ejercicios creados
        List<Ejercicio> listaTodosEjercicios = ejercicioController.obtenerTodosLosEjercicios();

        // Añade a la lista de ejercicios aleatoria tantos ejercicios aleatorios entre
        // el máximo y mínimo de ejercicicios aleatorios
        for (int i = 0; i < numeroEjerciciosAleatorio; i++) {
            Ejercicio ejercicioAleatorio = listaTodosEjercicios
                    .get(faker.number().numberBetween(0, listaTodosEjercicios.size())); // Un ejercicio aleatorio de los
                                                                                        // existentes
            listaEjerciciosAleatoria.add(ejercicioAleatorio);
        }

        return listaEjerciciosAleatoria;
    }

    /**
     * Generador aleatorio de rutinas para clientes
     * 
     * @param cliente cliente al que se le otorgará una rutina aleatoria
     * @return devuelve la rutina
     * 
     * @since 1.0
     */
    private Rutina crearRutinaAleatoria(Cliente cliente, List<Ejercicio> ejercicios) {
        // Nombre de la rutina
        String nombreRutina = faker.options().option(
                "Rutina de fuerza",
                "Rutina de cardio",
                "Rutina de flexibilidad",
                "Rutina de resistencia",
                "Rutina de hipertrofia",
                "Rutina funcional",
                "Rutina de powerLifting",
                "Rutina de rehabilitación");

        // Frecuencia de veces (semanal) que se repite la rutina
        int frecuenciaSemanal = faker.number().numberBetween(config.getFrecuenciaRutinaMinimaInicial(),
                config.getFrecuenciaRutinaMaximaInicial() + 1);

        // Creación de la rutina
        Rutina rutinaAleatoria = new Rutina(nombreRutina, frecuenciaSemanal, cliente, ejercicios);
        return rutinaAleatoria;
    }
}
