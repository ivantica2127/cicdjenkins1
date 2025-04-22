package com.upc.pruebas.infraestructure.persistence.repository;

import com.upc.pruebas.domain.model.Cliente;
import com.upc.pruebas.domain.repository.ClienteRepository;
import com.upc.pruebas.infraestructure.persistence.entity.ClienteEntity;
import com.upc.pruebas.infraestructure.persistence.mapper.ClienteMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JPAClienteRepository jpaRepository;

    public ClienteRepositoryImpl(JPAClienteRepository jpaRepository) {
        this.jpaRepository = jpaRepository;

    }


    @Override
    public Cliente insertar(Cliente obj) {
        ClienteEntity entity = ClienteMapper.toEntity(obj);
        ClienteEntity savedEntity = jpaRepository.save(entity);
        return ClienteMapper.toDomain(savedEntity);
    }

    @Override
    public Cliente actualizar(Cliente obj) {
        return null;
    }

    @Override
    public List<Cliente> listar() {
        return jpaRepository.findAll()
                .stream().map(ClienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Cliente listarPorId(Integer id) {
        return jpaRepository.findById(id)
                .map(ClienteMapper::toDomain)
                .orElseThrow(() -> new NoSuchElementException("Cliente con ID " + id + " no encontrado"));
    }
}
