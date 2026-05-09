package com.proyecto.stemgym.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proyecto.stemgym.controller.ClienteController;

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
    private ClienteController clienteController;

    
}
