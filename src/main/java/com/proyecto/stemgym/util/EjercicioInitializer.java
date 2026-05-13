package com.proyecto.stemgym.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proyecto.stemgym.controller.EjercicioController;
import com.proyecto.stemgym.controller.MusculoController;
import com.proyecto.stemgym.entity.Ejercicio;
import com.proyecto.stemgym.entity.Musculo;

import jakarta.transaction.Transactional;

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
    @Transactional
    public void inicializarEjercicios() {

        // Creación de los ejercicios
        // ================ PECTORALES ================= //
        Ejercicio pressBanca = new Ejercicio("Press banca",
                "Se debe realizar tumbado en la banca y empujando la barra hacia el lado contrario, formando un leve arco lumbar",
                "Barra olímpica y discos", "https://lh7-rt.googleusercontent.com/docsz/AD_4nXcCY0RiDhmlqHge9uWtCheMNYDkIaPFebc6349jgiiqem6KMAJ0QIP5EkFk64XQE9ahjsJ39sZvxBfOH0-MPcMA11txPrM9-mmd4zntU9aSAofxExvioS7mqMZGMc4yrn1Cs8nGcA?key=OeCwlVuEXtEmgxskpbBOHpFE",
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

        // ================ DORSALES ================= //
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

        Ejercicio remoDorsal = new Ejercicio("Remo en polea",
                "Se debe realizar en una polea tirando con la espalda dorsal, bajando y dejando subir la polea",
                "máquina de polea",
                "https://i.blogs.es/209555/remo1/450_1000.webp",
                "https://www.youtube.com/watch?v=LTwA6-3IIIs",
                musculoController.obtenerMusculo(2L),
                new ArrayList<>(
                        Arrays.asList(musculoController.obtenerMusculo(3L), musculoController.obtenerMusculo(6L))));

        // ================ TRAPECIO ================= //
        Ejercicio remoDorian = new Ejercicio("Remo Dorian",
                "Se debe realizar en la máquina dorian tirando de ella con el pecho apoyado",
                "máquina Dorian",
                "https://akonfitness.com/cdn/shop/files/dorian-2.jpg?v=1713779728&width=1214",
                "https://www.youtube.com/watch?v=s-W_EK_xats",
                musculoController.obtenerMusculo(3L),
                new ArrayList<>(
                        Arrays.asList(musculoController.obtenerMusculo(2L), musculoController.obtenerMusculo(6L))));

        Ejercicio encogimientos = new Ejercicio("Encogimientos de Trapecio",
                "Se debe realizar cogiendo un peso y subiendo unicamente el trapecio, con una leve inclinación hacia adelante",
                "mancuernas, barra hexagonal, polea",
                "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg94r1YXywbtf1kqBkemZVOhD0Xtcv8f9n22uY6yllPkVIdBN-qXQhEKVF9fX_HT2bukVKH4In3r9ozZKet8Pkqbu-H2d4gieXxIgv6yTbZ7Vaotdf5yGdPgS69LXRkFKeXZ4EWSiivtgs2/s1600/encogimiento_de_hombros_con_barra.jpg",
                "https://www.youtube.com/watch?v=q7yCmRbQ5m0",
                musculoController.obtenerMusculo(3L),
                new ArrayList<>(
                        Arrays.asList(musculoController.obtenerMusculo(2L))));

        // =============== DELTOIDES ============= //
        Ejercicio elevacionesLaterales = new Ejercicio("Elevaciones laterales",
                "Se debe realizar cogiendo un peso y subiendo elevando el brazo de forma extendida lateralmente",
                "mancuernas, polea",
                "https://static.strengthlevel.com/images/exercises/dumbbell-lateral-raise/dumbbell-lateral-raise-800.jpg",
                "https://www.youtube.com/watch?v=hgLpdwMtEEs",
                musculoController.obtenerMusculo(4L),
                new ArrayList<>(
                        Arrays.asList(musculoController.obtenerMusculo(3L))));

        Ejercicio pressMilitar = new Ejercicio("Press militar",
                "Se debe realizar cogiendo un peso y subiendo elevando el brazo similar a un press banca pero completamente vertical",
                "mancuernas, polea, barra, smith",
                "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiwzoCLkDn6wKzb1JBZMkcf_3ACNYQLKg4AJQpvJdudEVVDv0VrMlCl8PDiQOwRSH5wRwKiRFChh7vpos1oO1sbAMCSJIOuuYZMd9qUMDtTbCjYEfR9mj_t31iAU1hATKjsYFwJAzrPFNc/s1600/press-militar.jpg",
                "https://www.youtube.com/watch?v=4I6gCfiIHlw&t=0s",
                musculoController.obtenerMusculo(4L),
                new ArrayList<>(
                        Arrays.asList(musculoController.obtenerMusculo(1L), musculoController.obtenerMusculo(5L))));

        // ============= CUADRICEPS ============= //
        Ejercicio sentadillas = new Ejercicio("Sentadillas",
                "Se debe realizar cogiendo un peso y subiendo elevando el cuerpo, doblando las piernas en el proceso",
                "mancuernas, barra, smith",
                "https://i.blogs.es/51cee2/sentadilla-goblet/450_1000.webp",
                "https://www.youtube.com/watch?v=BjixzWEw4EY",
                musculoController.obtenerMusculo(7L),
                new ArrayList<>(
                        Arrays.asList(musculoController.obtenerMusculo(8L), musculoController.obtenerMusculo(9L))));

        Ejercicio extensionesCuadriceps = new Ejercicio("Extensiones de cuádriceps",
                "Se debe realizar en la máquina de extensiones, extendiendo las piernas sentado",
                "máquina de extensiones",
                "https://s3assets.skimble.com/assets/1914841/image_iphone.jpg",
                "https://www.youtube.com/watch?v=DI34ngDC8FU",
                musculoController.obtenerMusculo(7L),
                new ArrayList<>());

        // ============= ISQUIOS ============= //
        Ejercicio curlIsquios = new Ejercicio("Curl de isquios",
                "Se debe realizar cogiendo un peso y moviendolo con las piernas",
                "máquina de isquios",
                "https://static.strengthlevel.com/images/exercises/lying-leg-curl/lying-leg-curl-800.jpg",
                "https://www.youtube.com/watch?v=I0Z-mrfePJo",
                musculoController.obtenerMusculo(8L),
                new ArrayList<>());
        
        // ============= GLÚTEOS ============= //
        Ejercicio hipTrust = new Ejercicio("Hip trust",
                "Se debe realizar cogiendo una barra olímpica, haciendo un puente entre el banco y las piernas y elevando estas con el peso apoyado en las caderas",
                "barra y discos",
                "https://hips.hearstapps.com/hmg-prod/images/hip-thrust-67acc7664e81d.jpg",
                "https://www.youtube.com/watch?v=pF17m_CXfL0",
                musculoController.obtenerMusculo(9L),
                new ArrayList<>());

        // ============= TRÍCEPS ============ //
        Ejercicio extensionesTrasNuca = new Ejercicio("Extensiones tras nuca",
                "Con una polea o mancuena elevando el brazo, haciendo fuerza con el tríceps",
                "mancuerna, polea",
                "https://eliteatleta.com/wp-content/uploads/2023/10/Extension-trasnuca-polea-alta-2-brazos-cuerda.webp",
                "https://www.youtube.com/watch?v=P40-DvgM9qI",
                musculoController.obtenerMusculo(5L),
                new ArrayList<>(Arrays.asList(musculoController.obtenerMusculo(1L))));

        Ejercicio extensionesTriceps = new Ejercicio("Extensiones de tríceps",
                "Con una polea o mancuena bajando el brazo, haciendo fuerza con el tríceps",
                "mancuerna, polea",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9Hl1ufjBdtroWhiCrFHBoT7r4s_HJVL9DoQ&s",
                "https://www.youtube.com/watch?v=uZg6VtRhwQY",
                musculoController.obtenerMusculo(5L),
                new ArrayList<>(Arrays.asList(musculoController.obtenerMusculo(1L))));

        // ============= BÍCEPS ============ //
        Ejercicio curlBiceps = new Ejercicio("Curl de Bíceps",
                "Con una polea, mancuena o barra subiendo el brazo, haciendo fuerza con el bíceps en posición neutra",
                "mancuerna, polea, barra",
                "https://s3assets.skimble.com/assets/2287282/image_iphone.jpg",
                "https://www.youtube.com/watch?v=i1YgFZB6alI&t=0s",
                musculoController.obtenerMusculo(6L),
                new ArrayList<>());
        
        Ejercicio curlPredicador = new Ejercicio("Curl predicador",
                "Con una polea, mancuena o barra subiendo el brazo, haciendo fuerza con el bíceps en posición adelantada",
                "mancuerna, barra, banco scott, máquina",
                "https://static.strengthlevel.com/images/exercises/preacher-curl/preacher-curl-800.jpg",
                "https://www.youtube.com/watch?v=rFbuiBgWCYE",
                musculoController.obtenerMusculo(6L),
                new ArrayList<>());
        
          Ejercicio curlBayesian = new Ejercicio("Curl bayesian",
                "Con una polea, mancuena o barra subiendo el brazo, haciendo fuerza con el bíceps en atrasada",
                "mancuerna, barra máquina",
                "https://s3assets.skimble.com/assets/2627844/image_iphone.jpg",
                "https://www.youtube.com/watch?v=eyxodt_n8M4",
                musculoController.obtenerMusculo(6L),
                new ArrayList<>());
        
        // ============= ABDOMEN ============ //
        Ejercicio crunchAbdominal = new Ejercicio("Crunch Abdominal",
                "Con una polea, mancuena o disco, elevando levemente el torso contrayendo el abdomen.",
                "mancuerna, polea, disco",
                "https://i.ytimg.com/vi/OsUz898onTE/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDVI7thOYz4Eq3F0R5BzgpybIEUuA",
                "https://www.youtube.com/watch?v=OsUz898onTE",
                musculoController.obtenerMusculo(10L),
                new ArrayList<>());
        
        // ============= GUARDADO DE LOS EJERCICIOS EN LA DB ================= //
        ejercicioController.crearNuevoEJercicio(pressBanca);
        ejercicioController.crearNuevoEJercicio(flexiones);
        ejercicioController.crearNuevoEJercicio(fondos);
        ejercicioController.crearNuevoEJercicio(dominadas);
        ejercicioController.crearNuevoEJercicio(jalonPecho);
        ejercicioController.crearNuevoEJercicio(remoDorsal);
        ejercicioController.crearNuevoEJercicio(remoDorian);
        ejercicioController.crearNuevoEJercicio(encogimientos);
        ejercicioController.crearNuevoEJercicio(elevacionesLaterales);
        ejercicioController.crearNuevoEJercicio(pressMilitar);
        ejercicioController.crearNuevoEJercicio(sentadillas);
        ejercicioController.crearNuevoEJercicio(extensionesCuadriceps);
        ejercicioController.crearNuevoEJercicio(curlIsquios);
        ejercicioController.crearNuevoEJercicio(hipTrust);
        ejercicioController.crearNuevoEJercicio(extensionesTrasNuca);
        ejercicioController.crearNuevoEJercicio(extensionesTriceps);
        ejercicioController.crearNuevoEJercicio(curlBiceps);
        ejercicioController.crearNuevoEJercicio(curlPredicador);
        ejercicioController.crearNuevoEJercicio(curlBayesian);
        ejercicioController.crearNuevoEJercicio(crunchAbdominal);
    }
}
