package com.proyecto.stemgym.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Clase para el control de variables con valores fijos y comunes
 * 
 * @author Jorge Martín Llaneli
 * @version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "stem.inicializacion")
public class InitializationConfig {

    private int numeroInicialDeClientes = 50;
    private int edadMinimaClientesIniciales = 18;
    private int edadMaximaClientesIniciales = 80;
    private int pesoMinimoClientesIniciales = 45;
    private int pesoMaximoClientesIniciales = 115;
    private int pesoMinimoClientesObjetivo = 50;
    private int pesoMaximoObjetivoIniciales = 90;
    private int numeroInicialDeRutinas = 60;
    private int numeroMinimoEjerciciosEnRutinaInicial = 3;
    private int numeroMaximoEjerciciosEnRutinaInicial = 10;

    // ========= GETTERS & SETTERS ========== //
    public int getNumeroInicialDeClientes() {
        return numeroInicialDeClientes;
    }

    public void setNumeroInicialDeClientes(int numeroInicialDeClientes) {
        this.numeroInicialDeClientes = numeroInicialDeClientes;
    }

    public int getEdadMinimaClientesIniciales() {
        return edadMinimaClientesIniciales;
    }

    public void setEdadMinimaClientesIniciales(int edadMinimaClientesIniciales) {
        this.edadMinimaClientesIniciales = edadMinimaClientesIniciales;
    }

    public int getEdadMaximaClientesIniciales() {
        return edadMaximaClientesIniciales;
    }

    public void setEdadMaximaClientesIniciales(int edadMaximaClientesIniciales) {
        this.edadMaximaClientesIniciales = edadMaximaClientesIniciales;
    }

    public int getPesoMinimoClientesIniciales() {
        return pesoMinimoClientesIniciales;
    }

    public void setPesoMinimoClientesIniciales(int pesoMinimoClientesIniciales) {
        this.pesoMinimoClientesIniciales = pesoMinimoClientesIniciales;
    }

    public int getPesoMaximoClientesIniciales() {
        return pesoMaximoClientesIniciales;
    }

    public void setPesoMaximoClientesIniciales(int pesoMaximoClientesIniciales) {
        this.pesoMaximoClientesIniciales = pesoMaximoClientesIniciales;
    }

    public int getPesoMinimoClientesObjetivo() {
        return pesoMinimoClientesObjetivo;
    }

    public void setPesoMinimoClientesObjetivo(int pesoMinimoClientesObjetivo) {
        this.pesoMinimoClientesObjetivo = pesoMinimoClientesObjetivo;
    }

    public int getPesoMaximoObjetivoIniciales() {
        return pesoMaximoObjetivoIniciales;
    }

    public void setPesoMaximoObjetivoIniciales(int pesoMaximoObjetivoIniciales) {
        this.pesoMaximoObjetivoIniciales = pesoMaximoObjetivoIniciales;
    }

    public int getNumeroInicialDeRutinas() {
        return numeroInicialDeRutinas;
    }

    public void setNumeroInicialDeRutinas(int numeroInicialDeRutinas) {
        this.numeroInicialDeRutinas = numeroInicialDeRutinas;
    }

    public int getNumeroMinimoEjerciciosEnRutinaInicial() {
        return numeroMinimoEjerciciosEnRutinaInicial;
    }

    public void setNumeroMinimoEjerciciosEnRutinaInicial(int numeroMinimoEjerciciosEnRutinaInicial) {
        this.numeroMinimoEjerciciosEnRutinaInicial = numeroMinimoEjerciciosEnRutinaInicial;
    }

    public int getNumeroMaximoEjerciciosEnRutinaInicial() {
        return numeroMaximoEjerciciosEnRutinaInicial;
    }

    public void setNumeroMaximoEjerciciosEnRutinaInicial(int numeroMaximoEjerciciosEnRutinaInicial) {
        this.numeroMaximoEjerciciosEnRutinaInicial = numeroMaximoEjerciciosEnRutinaInicial;
    }

}
