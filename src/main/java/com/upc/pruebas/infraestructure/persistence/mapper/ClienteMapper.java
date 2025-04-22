package com.upc.pruebas.infraestructure.persistence.mapper;

import com.upc.pruebas.domain.model.Cliente;
import com.upc.pruebas.infraestructure.persistence.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public static ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setNombre(cliente.getNombre());
        entity.setEmail(cliente.getEmail());
        return entity;
    }

    public static Cliente toDomain(ClienteEntity entity) {
        Cliente cliente = new Cliente(entity.getNombre(), entity.getEmail());
        cliente.setId(entity.getId());
        return cliente;
    }
}
