package com.proyecto.stemgym.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Tabla que representa una rutina de ejercicios para la hipertrofia
 * <p>
 * Contiene el nombre, las veces que se realiza a la semana y la lista de
 * ejercicios.
 * </p>
 *
 * @see Ejercicio
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Entity
@Table(name = "rutinas")
public class Rutina {

    // ========= ATRIBUTOS =========== //
    /** Identificador de la rutina. Se autogenera. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre que recibe la rutina */
    @Column(nullable = false)
    private String nombre;

    /** Veces que se repite la rutina (Máximo 7) */
    @Column(nullable = false, columnDefinition = "INT CHECK (frecuencia_semanal BETWEEN 1 AND 7)")
    private int frecuenciaSemanal;

    /**
     * Lista de ejercicios que contiene la rutina, es una relación muchos a muchos
     * con la entidad Ejercicio
     */
    @ManyToMany
    @JoinTable(name = "rutina_ejercicio", joinColumns = @JoinColumn(name = "rutina_id"), inverseJoinColumns = @JoinColumn(name = "ejercicio_id"))
    private List<Ejercicio> ejercicios;

    /**
     * Cliente al que pertenece la rutina
     * <p>
     * Una rutina pertenece a un único cliente, pero un cliente puede tener muchas
     * rutinas.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // ========= CONSTRUCTOR ======== //
    /**
     * Constructor vacío para JPA
     * 
     * @since 1.0
     */
    public Rutina() {

    }

    /**
     * Constructor con parámetros de rutina
     * 
     * @param nombre            nombre de la rutina
     * @param frecuenciaSemanal frecuencia de dias de la rutina de forma semanal
     * @param cliente           cliente propietario de la rutina
     * 
     * @see Cliente
     * @since 1.0
     */
    public Rutina(String nombre, int frecuenciaSemanal, Cliente cliente, List<Ejercicio> ejercicios) {
        this.nombre = nombre;
        this.frecuenciaSemanal = frecuenciaSemanal;
        this.cliente = cliente;
        this.ejercicios = ejercicios;
    }

    // ========= GETTERS & SETTERS ========== //
    /**
     * Método get para id
     * <p>
     * Devuelve el identificador de la rutina
     * <p>
     * 
     * @return id de la rutina
     * 
     * @since 1.0
     */
    public Long getId() {
        return id;
    }

    /**
     * Método set para id
     * <p>
     * Permite modificar el id de la rutina
     * </p>
     * 
     * @param id identificador de la rutina
     * 
     * @since 1.0
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método get para nombre
     * <p>
     * Permite obtener nombre de la rutina
     * </p>
     * 
     * @return nombre de la rutina
     * 
     * @since 1.0
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método set para nombre
     * <p>
     * Permite modificar el nombre de la rutina
     * </p>
     * 
     * @param nombre nombre de la rutina
     * 
     * @since 1.0
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método get para la fecuencia semanal
     * <p>
     * Sirve para obtener la frecuencia con la que se repite la rutina de forma
     * semanal
     * </p>
     * 
     * @return frecuencia semanal de la rutina
     * 
     * @since 1.0
     */
    public int getFrecuenciaSemanal() {
        return frecuenciaSemanal;
    }

    /**
     * Método set para la frecuencia semanal
     * <p>
     * Permite modificar la frecuencia con la que el cliente repite la rutina
     * semanalmente
     * </p>
     * 
     * @param frecuenciaSemanal frecuencia de repetición de la rutina semanalmente
     * 
     * @since 1.0
     */
    public void setFrecuenciaSemanal(int frecuenciaSemanal) {
        this.frecuenciaSemanal = frecuenciaSemanal;
    }

    /**
     * Método get para ejercicios
     * <p>
     * Este método permite obtener la lista de ejercicios de la rutina
     * </p>
     * 
     * @return lista de ejercicios de la rutina
     * 
     * @since 1.0
     */
    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    /**
     * Método set para ejercicios
     * <p>
     * Este método permite modificar la lista de ejercicios de la rutina
     * </p>
     * 
     * @param ejercicios lista de ejercicios de la rutina
     * 
     * @see Ejercicio
     * @since 1.0
     */
    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    /**
     * Método get del cliente propietario
     * <p>
     * Permite obtener el cliente dueño de la rutina
     * </p>
     * 
     * @return cliente propietario de la rutina
     * 
     * @see Cliente
     * @since 1.0
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Método set del cliente de la rutina
     * <p>
     * Este método modifica el cliente asociado a la rutina
     * </p>
     * 
     * @param cliente cliente dueño de la rutina
     * 
     * @see Cliente
     * @since 1.0
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // ============ TO STRING ============= //
    /**
     * Método to string para la RUTINA
     * <p>
     * Devuelve una cadena con información básica sobre la rutina
     * </p>
     * 
     * @since 1.0
     */
    @Override
    public String toString() {
        return id + " --> " + nombre + " | Pertenece a: " + cliente.getNombre();
    }
}
