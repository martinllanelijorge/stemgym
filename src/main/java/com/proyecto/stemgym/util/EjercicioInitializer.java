package com.proyecto.stemgym.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proyecto.stemgym.controller.EjercicioController;
import com.proyecto.stemgym.controller.MusculoController;
import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;

/**
 * Clase donde tiene lugar la inicialización de los ejercicios base que
 * aparecerán en la API
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Component
public class EjercicioInitializer {

    @Autowired
    private EjercicioController ejercicioController;
    @Autowired
    private MusculoController musculoController;

    /**
     * Método para inicializar los ejercicios de la API
     * <p>
     * Este método hace que la API comience con una serie de ejercicios predefinidos
     * </p>
     * 
     * @see Musculo
     * @sice 1.0
     */
    public void inicializarEjercicios() {

        // Creación de los ejercicios
        Ejercicio pressBanca = new Ejercicio("Press banca",
                "Se debe realizar tumbado en la banca y empujando la barra hacia el lado contrario, formando un leve arco lumbar",
                "Barra olímpica y discos", "https://www.fisioterapiaconmueve.com/wp-content/uploads/2018/04/1.jpg",
                "https://www.youtube.com/watch?v=TAH8RxOS0VI",
                musculoController.obtenerMusculo(1L),
                new ArrayList<>(Arrays.asList(musculoController.obtenerMusculo(5L))));

        Ejercicio flexiones = new Ejercicio("Flexiones",
                "Se debe realizar tumbado en el suelo boca abajo y empujando el suelo, sin perder la tensión en el abdomen",
                "Suelo", "https://static.strengthlevel.com/images/exercises/push-ups/push-ups-800.jpg",
                "https://www.youtube.com/watch?v=mTs83kUu7rc&t=0s",
                musculoController.obtenerMusculo(1L),
                new ArrayList<>(Arrays.asList(musculoController.obtenerMusculo(5L))));

        Ejercicio fondos = new Ejercicio("Fondos",
                "Se debe realizar en unas paralelas, subiendo y bajando el cuerpo con el pecho y manteniendo el abdomen duro",
                "paralelas",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyzC2_69NTGOF8ro50gqYNH7Sfi5dMqS9tkQ&s",
                "https://www.youtube.com/watch?v=CkX5QdEz4IE",
                musculoController.obtenerMusculo(1L),
                new ArrayList<>(Arrays.asList(musculoController.obtenerMusculo(5L))));

        Ejercicio dominadas = new Ejercicio("Dominadas",
                "Se debe realizar en una barra alta colgado, subiendo y bajando el cuerpo con la espalda y manteniendo el abdomen duro",
                "barra alta",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSlG56QMs8YsVJpb32GfQUBNwSBVUWLKHB4w&s",
                "https://www.youtube.com/watch?v=J1r9UtnaY5c",
                musculoController.obtenerMusculo(2L),
                new ArrayList<>(
                        Arrays.asList(musculoController.obtenerMusculo(3L), musculoController.obtenerMusculo(6L))));

        Ejercicio jalonPecho = new Ejercicio("Jalón al pecho",
                "Se debe realizar la máquina de jalones para la espalda, bajando y dejando subir la polea",
                "máquina de jalón",
                "https://s3assets.skimble.com/assets/1876096/image_full.jpg",
                "https://www.youtube.com/watch?v=x2Y6Mb41zjY",
                musculoController.obtenerMusculo(2L),
                new ArrayList<>(
                        Arrays.asList(musculoController.obtenerMusculo(3L), musculoController.obtenerMusculo(6L))));
    }
}
