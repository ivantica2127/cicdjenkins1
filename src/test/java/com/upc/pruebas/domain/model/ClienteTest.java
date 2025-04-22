package com.upc.pruebas.domain.model;

import com.upc.pruebas.domain.exception.EMailInvalidoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    @Test
    void crearClienteValido() {
        Cliente cliente = new Cliente("Juan", "juan@email.com");
        assertEquals("Juan", cliente.getNombre());
        assertEquals("juan@email.com", cliente.getEmail());
    }
    @Test
    void crearClienteConNombreNuloDebeLanzarExcepcion() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Cliente(null, "test@email.com");
        });
        assertEquals("El nombre es obligatorio", ex.getMessage());
    }
    @Test
    void crearClienteConNombreVacioDebeLanzarExcepcion() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("   ", "test@email.com");
        });
        assertEquals("El nombre es obligatorio", ex.getMessage());
    }
    @Test
    void crearClienteConEmailInvalidoDebeLanzarExcepcion() {
        assertThrows(EMailInvalidoException.class, () -> {
            new Cliente("Ana", "correo-sin-arroba.com");
        });
    }
    @Test
    void actualizarDatosDebeCambiarNombreYEmail() {
        Cliente cliente = new Cliente("Luis", "luis@email.com");
        cliente.actualizarDatos("Carlos", "carlos@email.com");
        assertEquals("Carlos", cliente.getNombre());
        assertEquals("carlos@email.com", cliente.getEmail());
    }
}
