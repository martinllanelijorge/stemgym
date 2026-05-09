package com.proyecto.stemgym.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * Tabla que representa un músculo del cuerpo humano
 * <p>
 * Contiene información sobre la frecuencia de entrenamiento recomendada
 * y el volumen semanal mínimo y máximo en series.
 * </p>
 *
 * @see Ejercicio
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Entity
@Table(name = "musculos")
public class Musculo {

    // ========== ATRIBUTOS ============ //
    /** Identificador del músculo. Se autogenera. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre que recibe el músculo. */
    @Column(nullable = false)
    private String nombre;

    /** Frecuencia de trabajo semanal recomendada (lo normal es {@code 2}) */
    @Column(nullable = false, columnDefinition = "INT CHECK (frecuencia_recomendada BETWEEN 1 AND 7)")
    private int frecuenciaRecomendada;

    /**
     * Mínimo de volumen semanal del músculo. Cantidad de series mínimas que se
     * deberían hacer.
     */
    @Column(nullable = false)
    private int minVolumenSemanal;

    /**
     * Máximo de volumen semanal del músculo. Cantidad máxima de series de trabajo
     * que puede soportar el músculo a la semana.
     */
    @Column(nullable = false)
    private int maxVolumenSemanal;

    /** URL de la imagen representativa del músculo. Debe ser una URL. */
    @Column(nullable = false)
    private String urlImagen;

    /** Lista de ejercicios donde el músculo es el principal */
    @JsonIgnore
    @OneToMany(mappedBy = "musculoPrincipal", cascade = CascadeType.ALL)
    private List<Ejercicio> ejercicios;

    // ========== CONSTRUCTORES =========== //
    /**
     * Constructor para JPA
     *
     * @since 1.0
     */
    public Musculo() {

    }

    /**
     * Constructor funcional para músculo, con parámetros
     *
     * @param nombre                nombre del músculo
     * @param frecuenciaRecomendada número de veces por semana recomendado para
     *                              entrenar el músculo
     * @param minVolumenSemanal     volumen mínimo semanal
     * @param maxVolumenSemanal     volumen máximo semanal
     * @param urlImagen             dirección web del alojamiento de la imagen (url)
     * @since 1.0
     */
    public Musculo(String nombre, int frecuenciaRecomendada, int minVolumenSemanal, int maxVolumenSemanal,
            String urlImagen) {
        this.nombre = nombre;
        this.frecuenciaRecomendada = frecuenciaRecomendada;
        this.minVolumenSemanal = minVolumenSemanal;
        this.maxVolumenSemanal = maxVolumenSemanal;
        this.urlImagen = urlImagen;
        ejercicios = new ArrayList<>();
    }

    // ========== GETTERS & SETTERS ======== //
    /**
     * Método get para id
     * <p>
     * Este método permite obtener el identificador del músculo.
     * </p>
     * 
     * @return id del músculo
     * 
     * @since 1.0
     */
    public Long getId() {
        return id;
    }

    /**
     * Método set para id
     * <p>
     * Este método permite modificar el id del músculo.
     * </p>
     * 
     * @param id id del músculo
     * 
     * @since 1.0
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método get para nombre
     * <p>
     * Este método permite obtener el nombre del músculo.
     * </p>
     * 
     * @return nombre del músculo
     * 
     * @since 1.0
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método set para nombre
     * <p>
     * Este método permite modificar el nombre del músculo.
     * </p>
     *
     * @param nombre nombre del músculo
     * @since 1.0
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método get para frecuencia recomendada
     * <p>
     * Este método permite obtener la frecuencia de entrenamiento semanal
     * recomendada.
     * </p>
     *
     * @return frecuencia recomendada en días por semana
     * @since 1.0
     */
    public int getFrecuenciaRecomendada() {
        return frecuenciaRecomendada;
    }

    /**
     * Método set para frecuencia recomendada
     * <p>
     * Este método permite modificar la frecuencia de entrenamiento semanal
     * recomendada.
     * </p>
     *
     * @param frecuenciaRecomendada frecuencia en días por semana
     * @since 1.0
     */
    public void setFrecuenciaRecomendada(int frecuenciaRecomendada) {
        this.frecuenciaRecomendada = frecuenciaRecomendada;
    }

    /**
     * Método get para volumen mínimo semanal
     * <p>
     * Este método permite obtener el volumen de trabajo mínimo semanal del músculo.
     * </p>
     *
     * @return volumen mínimo en series
     * @since 1.0
     */
    public int getMinVolumenSemanal() {
        return minVolumenSemanal;
    }

    /**
     * Método set para volumen mínimo semanal
     * <p>
     * Este método permite modificar el volumen m de trabajo mínimo semanal del
     * músculo.
     * </p>
     *
     * @param minVolumenSemanal volumen mínimo en series
     * @since 1.0
     */
    public void setMinVolumenSemanal(int minVolumenSemanal) {
        this.minVolumenSemanal = minVolumenSemanal;
    }

    /**
     * Método get para volumen máximo semanal
     * <p>
     * Este método permite obtener el volumen máximo de trabajo semanal del músculo.
     * </p>
     *
     * @return volumen máximo en series
     * @since 1.0
     */
    public int getMaxVolumenSemanal() {
        return maxVolumenSemanal;
    }

    /**
     * Método set para volumen máximo semanal
     * <p>
     * Este método permite modificar el volumen máximo de trabajo semanal del
     * músculo.
     * </p>
     *
     * @param maxVolumenSemanal volumen máximo en series
     * @since 1.0
     */
    public void setMaxVolumenSemanal(int maxVolumenSemanal) {
        this.maxVolumenSemanal = maxVolumenSemanal;
    }

    /**
     * Método get para url de imagen
     * <p>
     * Este método permite obtener la URL de la imagen del músculo.
     * </p>
     *
     * @return URL de la imagen
     * @since 1.0
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Método set para url de imagen
     * <p>
     * Este método permite modificar la URL de la imagen del músculo.
     * </p>
     *
     * @param urlImagen URL de la imagen
     * @since 1.0
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Método get para ejercicios
     * <p>
     * Devuelve una lista de ejercicios donde el músculo es el principal
     * </p>
     * 
     * @return lista de ejercicios
     * @since 1.p
     */
    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    /**
     * Método set para ejercicios
     * <p>
     * Modifica la lista de ejercicios donde el músculo es el principal
     * </p>
     * 
     * @param ejercicios lista de ejercicios
     * @since 1.0
     */
    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    // =========== TO STRING ============ //
    /**
     * Devuelve en String la información principal del músculo
     *
     * @return cadena con el id, nombre, frecuencia recomendada y volumen semanal
     * @since 1.0
     */
    @Override
    public String toString() {
        return id + " --> " + nombre + " | Frecuencia recomendada: " + frecuenciaRecomendada
                + " | Volumen semanal (min - max): " + minVolumenSemanal + " - " + maxVolumenSemanal + " series.";
    }
}
