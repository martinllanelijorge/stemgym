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
    private double pesoMinimoClientesIniciales = 45;
    private double pesoMaximoClientesIniciales = 115;
    private double pesoMinimoClientesObjetivo = 50;
    private double pesoMaximoObjetivoIniciales = 90;

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
    public double getPesoMinimoClientesIniciales() {
        return pesoMinimoClientesIniciales;
    }
    public void setPesoMinimoClientesIniciales(double pesoMinimoClientesIniciales) {
        this.pesoMinimoClientesIniciales = pesoMinimoClientesIniciales;
    }
    public double getPesoMaximoClientesIniciales() {
        return pesoMaximoClientesIniciales;
    }
    public void setPesoMaximoClientesIniciales(double pesoMaximoClientesIniciales) {
        this.pesoMaximoClientesIniciales = pesoMaximoClientesIniciales;
    }
    public double getPesoMinimoClientesObjetivo() {
        return pesoMinimoClientesObjetivo;
    }
    public void setPesoMinimoClientesObjetivo(double pesoMinimoClientesObjetivo) {
        this.pesoMinimoClientesObjetivo = pesoMinimoClientesObjetivo;
    }
    public double getPesoMaximoObjetivoIniciales() {
        return pesoMaximoObjetivoIniciales;
    }
    public void setPesoMaximoObjetivoIniciales(double pesoMaximoObjetivoIniciales) {
        this.pesoMaximoObjetivoIniciales = pesoMaximoObjetivoIniciales;
    }
    
}
