package com.upc.pruebas.infraestructure.persistence.mapper;

import com.upc.pruebas.domain.exception.EMailInvalidoException;
import com.upc.pruebas.domain.model.Cliente;
import com.upc.pruebas.infraestructure.persistence.entity.ClienteEntity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteMapperTest {

    @Test
    void toEntity_deberiaMapearClienteAEntidadCorrectamente() {
        Cliente cliente = new Cliente(1, "Ana", "ana@mail.com");

        ClienteEntity entity = ClienteMapper.toEntity(cliente);

        assertEquals(1, entity.getId());
        assertEquals("Ana", entity.getNombre());
        assertEquals("ana@mail.com", entity.getEmail());
    }

    @Test
    void toDomain_deberiaMapearEntidadADominioCorrectamente() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(1);
        entity.setNombre("Luis");
        entity.setEmail("luis@mail.com");

        Cliente cliente = ClienteMapper.toDomain(entity);

        assertEquals("Luis", cliente.getNombre());
        assertEquals("luis@mail.com", cliente.getEmail());
    }

    @Test
    void toDomain_conNombreNulo_deberiaLanzarExcepcion() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(1);
        entity.setNombre(null);
        entity.setEmail("test@mail.com");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            ClienteMapper.toDomain(entity);
        });

        assertEquals("El nombre es obligatorio", ex.getMessage());
    }

    @Test
    void toDomain_conEmailInvalido_deberiaLanzarExcepcion() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(1);
        entity.setNombre("Carlos");
        entity.setEmail("correoSinArroba");

        assertThrows(EMailInvalidoException.class, () -> {
            ClienteMapper.toDomain(entity);
        });
    }

    @Test
    void toEntity_conClienteConCamposNulos_deberiaMapearConNulls() {
        Cliente cliente = new Cliente(1, null, null); // Usamos el constructor sin validaciones

        ClienteEntity entity = ClienteMapper.toEntity(cliente);

        assertEquals(1, entity.getId());
        assertNull(entity.getNombre());
        assertNull(entity.getEmail());
    }

}
