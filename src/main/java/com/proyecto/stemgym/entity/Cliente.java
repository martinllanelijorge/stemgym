package com.proyecto.stemgym.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Tabla que representa los clientes del gimnasio
 * <p>
 * Contiene información sobre su nombre, edad, peso actual, peso objetivo,
 * imagen de perfil y sus rutinas asociadas.
 * </p>
 *
 * @see Rutina
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    // =========== ATRIBUTOS ======== //
    /** Identificador del cliente. Se autogenera. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre que recibe el cliente */
    @Column(nullable = false)
    private String nombre;

    /** Género del cliente */
    @Column(nullable = false)
    private String genero;

    /** Edad del cliente */
    @Column(nullable = false)
    private int edad;

    /** Peso en Kg del cliente actualmente */
    @Column(nullable = false)
    private double pesoActual;

    /** Peso Objetivo en Kg del cliente */
    @Column(nullable = false)
    private double pesoObjetivo;

    /** Foto del cliente (url de su alojamiento web) */
    @Column(nullable = false)
    private String urlImagen;

    /** Se alcanzó el peso o no con true o false */
    @Column(nullable = false)
    private boolean objetivoAlcanzado;

    /**
     * Rutinas asociadas al cliente
     * <p>
     * Un cliente puede tener 1 o muchas rutinas, pero una rutina solo puede
     * pertenecer a un cliente.
     * </p>
     */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Rutina> rutinas;

    // ============ CONSTRUCTOR ============= //
    /**
     * Constructor vacío para JPA
     * 
     * @since 1.0
     */
    public Cliente() {

    }

    /**
     * Constructor con parámetros para cliente.
     * 
     * @param nombre       nombre del cliente
     * @param genero       género del cliente
     * @param edad         edad del cliente
     * @param pesoActual   peso actual del cliente
     * @param pesoObjetivo peso que se busca alcanzar
     * @param urlImagen    enlace a la imagen de perfil del cliente
     * @since 1.0
     */
    public Cliente(String nombre, String genero, int edad, double pesoActual, double pesoObjetivo, String urlImagen) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.pesoActual = pesoActual;
        this.pesoObjetivo = pesoObjetivo;
        this.urlImagen = urlImagen;
        this.objetivoAlcanzado = false;
        this.rutinas = new ArrayList<>();
    }

    // =========== GETTERS & SETTERS ============= //
    /**
     * Método get para id
     * <p>
     * Devuelve el identificador del cliente.
     * </p>
     * 
     * @return id del cliente
     * @since 1.0
     */
    public Long getId() {
        return id;
    }

    /**
     * Método set para id
     * 
     * @param id id del cliente
     * @since 1.0
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método get para nombre
     * 
     * @return nombre del cliente
     * @since 1.0
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método set para nombre
     * 
     * @param nombre nombre del cliente
     * @since 1.0
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método get para género
     * 
     * @return género del cliente
     * @since 1.0
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Método set para género
     * 
     * @param genero género del cliente
     * @since 1.0
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Método get para edad
     * 
     * @return edad del cliente
     * @since 1.0
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Método set para edad
     * 
     * @param edad edad del cliente
     * @since 1.0
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Método get para peso actual
     * 
     * @return peso actual en Kg
     * @since 1.0
     */
    public double getPesoActual() {
        return pesoActual;
    }

    /**
     * Método set para peso actual
     * 
     * @param pesoActual peso actual en Kg
     * @since 1.0
     */
    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    /**
     * Método get para peso objetivo
     * 
     * @return peso objetivo en Kg
     * @since 1.0
     */
    public double getPesoObjetivo() {
        return pesoObjetivo;
    }

    /**
     * Método set para peso objetivo
     * 
     * @param pesoObjetivo peso objetivo en Kg
     * @since 1.0
     */
    public void setPesoObjetivo(double pesoObjetivo) {
        this.pesoObjetivo = pesoObjetivo;
    }

    /**
     * Método get para url de imagen
     * 
     * @return URL de la imagen de perfil
     * @since 1.0
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Método set para url de imagen
     * 
     * @param urlImagen URL de la imagen de perfil
     * @since 1.0
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Método para comprobar si se alcanzó el objetivo
     * <p>
     * Compara el peso actual con el objetivo y actualiza el estado.
     * </p>
     * 
     * @return {@code true} si el peso actual es igual al objetivo, {@code false} en
     *         caso contrario
     * @since 1.0
     */
    public boolean isObjetivoAlcanzado() {
        if (pesoActual != pesoObjetivo) {
            setObjetivoAlcanzado(false);
        } else {
            setObjetivoAlcanzado(true);
        }
        return objetivoAlcanzado;
    }

    /**
     * Método set para estado del objetivo
     * 
     * @param objetivoAlcanzado true si se ha cumplido el objetivo
     * @since 1.0
     */
    public void setObjetivoAlcanzado(boolean objetivoAlcanzado) {
        this.objetivoAlcanzado = objetivoAlcanzado;
    }

    /**
     * Método get para rutinas
     * <p>
     * Devuelve la lista de rutinas asociadas al cliente.
     * </p>
     * 
     * @return lista de rutinas
     * @since 1.0
     */
    public List<Rutina> getRutinas() {
        return rutinas;
    }

    /**
     * Método set para rutinas
     * 
     * @param rutinas lista de rutinas del cliente
     * @since 1.0
     */
    public void setRutinas(List<Rutina> rutinas) {
        this.rutinas = rutinas;
    }

    // ============ TO STRING =============== //
    /**
     * Devuelve en String la información principal del cliente
     * 
     * @return cadena con el id, nombre, pesos y estado del objetivo
     * @since 1.0
     */
    @Override
    public String toString() {
        String mensajeObjetivoCumplido = "No";
        if (isObjetivoAlcanzado()) {
            mensajeObjetivoCumplido = "Sí";
        }
        return id + " --> " + nombre + " | Peso Actual: " + pesoActual + " Kg | Peso Objetivo: " + pesoObjetivo
                + " Kg | Objetivo cumplido: " + mensajeObjetivoCumplido;
    }

}
