package com.proyecto.stemgym.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Tabla que representa un ejercicio para la hipertrofia muscular
 * <p>
 * Contiene información sobre su nombre, músculo principal, músculos
 * secundarios, una descripción, material e imagen y vídeo del mismo.
 * </p>
 *
 * @see Musculo
 * @see Rutina
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Entity
@Table(name = "ejercicios")
public class Ejercicio {

    // ======== ATRIBUTOS ========= //

    /** Identificador del ejercicio. Se autogenera. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre que recibe el ejercicio */
    @Column(nullable = false)
    private String nombre;

    /** Descripción del ejercicio */
    @Column(nullable = false, length = 1000)
    private String descripcion;

    /** Material necesario del ejercicio */
    @Column(nullable = false)
    private String material;

    /** URL de la imagen del ejercicio */
    @Column(nullable = false)
    private String urlImagen;

    /** URL del vídeo demostrativo del ejercicio */
    @Column(nullable = false)
    private String urlVideo;

    /**
     * Músculo principal que trabaja el ejercicio
     * <p>
     * Un ejercicio tiene un único músculo principal, pero un músculo puede ser el
     * principal de varios ejercicios.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "musculo_principal_id", nullable = false)
    private Musculo musculoPrincipal;

    /**
     * Músculos secundarios del ejercicio
     * <p>
     * Un ejercicio puede tener 0 o muchos músculos secundarios, y un músculo puede
     * ser secundario en 0 o muchos ejercicios.
     * </p>
     */
    @ManyToMany
    @JoinTable(name = "ejercicio_musculo_secundario", joinColumns = @JoinColumn(name = "ejercicio_id"), inverseJoinColumns = @JoinColumn(name = "musculo_id"))
    private List<Musculo> musculosSecundarios;

    // ========== CONSTRUCTORES =========== //

    /**
     * Constructor para JPA
     *
     * @since 1.0
     */
    public Ejercicio() {
    }

    /**
     * Constructor funcional para ejercicio, con parámetros.
     *
     * @param nombre           nombre del ejercicio
     * @param descripcion      descripción y consejos de ejecución
     * @param material         material necesario para realizarlo
     * @param urlImagen        URL de la imagen ilustrativa
     * @param urlVideo         URL del vídeo demostrativo
     * @param musculoPrincipal músculo principal que trabaja el ejercicio
     * @since 1.0
     */
    public Ejercicio(String nombre, String descripcion, String material,
            String urlImagen, String urlVideo, Musculo musculoPrincipal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.material = material;
        this.urlImagen = urlImagen;
        this.urlVideo = urlVideo;
        this.musculoPrincipal = musculoPrincipal;
        this.musculosSecundarios = new ArrayList<>();
    }

    // ======== GETTERS & SETTERS ======== //

    /**
     * Método get para id
     *
     * @return id del ejercicio
     * @since 1.0
     */
    public Long getId() {
        return id;
    }

    /**
     * Método set para id
     *
     * @param id id del ejercicio
     * @since 1.0
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método get para nombre
     *
     * @return nombre del ejercicio
     * @since 1.0
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método set para nombre
     *
     * @param nombre nombre del ejercicio
     * @since 1.0
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método get para descripción
     *
     * @return descripción del ejercicio
     * @since 1.0
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método set para descripción
     *
     * @param descripcion descripción del ejercicio
     * @since 1.0
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método get para material
     *
     * @return material necesario para el ejercicio
     * @since 1.0
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Método set para material
     *
     * @param material material necesario para el ejercicio
     * @since 1.0
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Método get para url de imagen
     *
     * @return URL de la imagen del ejercicio
     * @since 1.0
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Método set para url de imagen
     *
     * @param urlImagen URL de la imagen del ejercicio
     * @since 1.0
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Método get para url de vídeo
     *
     * @return URL del vídeo demostrativo del ejercicio
     * @since 1.0
     */
    public String getUrlVideo() {
        return urlVideo;
    }

    /**
     * Método set para url de vídeo
     *
     * @param urlVideo URL del vídeo demostrativo del ejercicio
     * @since 1.0
     */
    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    /**
     * Método get para músculo principal
     * <p>
     * Devuelve el músculo principal que trabaja el ejercicio.
     * </p>
     *
     * @return músculo principal
     * @since 1.0
     */
    public Musculo getMusculoPrincipal() {
        return musculoPrincipal;
    }

    /**
     * Método set para músculo principal
     * <p>
     * Modifica el músculo principal que trabaja el ejercicio.
     * </p>
     *
     * @param musculoPrincipal músculo principal
     * @since 1.0
     */
    public void setMusculoPrincipal(Musculo musculoPrincipal) {
        this.musculoPrincipal = musculoPrincipal;
    }

    /**
     * Método get para músculos secundarios
     * <p>
     * Devuelve la lista de músculos secundarios que trabaja el ejercicio. Ver
     * entidad {@link Musculo}
     * </p>
     *
     * @return lista de músculos secundarios
     * @since 1.0
     */
    public List<Musculo> getMusculosSecundarios() {
        return musculosSecundarios;
    }

    /**
     * Método set para músculos secundarios
     * <p>
     * Modifica la lista de músculos secundarios que trabaja el ejercicio
     * </p>
     *
     * @param musculosSecundarios lista de músculos secundarios
     * @since 1.0
     */
    public void setMusculosSecundarios(List<Musculo> musculosSecundarios) {
        this.musculosSecundarios = musculosSecundarios;
    }

    // =========== TO STRING ============ //

    /**
     * Devuelve en String la información principal del ejercicio
     *
     * @return cadena con el id, nombre, material y músculo principal
     * @since 1.0
     */
    @Override
    public String toString() {
        return id + " --> " + nombre
                + " | Material: " + material
                + " | Músculo principal: " + musculoPrincipal.getNombre();
    }
}
