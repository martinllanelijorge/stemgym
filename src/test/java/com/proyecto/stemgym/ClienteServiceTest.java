package com.proyecto.stemgym;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto.stemgym.entity.Cliente;
import com.proyecto.stemgym.service.ClienteService;
import com.proyecto.stemgym.service.impl.ClienteServiceImpl;

import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    private Cliente cliente;

    // ================ INICIALIZA LA CLASE ================ //
    @BeforeEach
    void inicializar() {
        cliente = new Cliente("Jorge Martín Llaneli", "Hombre", 27, 77.7, 80, "https://ejemplo/imagen.jpg");
    }

    // ================ CREAR UN CLIENTE EN LA BBDD ================ //
    @Test
    @DisplayName("Debería devolver el mismo cliente")
    void crearCliente_devuelveClienteAgregado_siSeHizoSave(){
        assertTrue(cliente.getNombre() == clienteService.crearCliente(cliente).getNombre());
    }
}
