package com.upc.pruebas.infraestructure.persistence.repository;

import com.upc.pruebas.infraestructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAClienteRepository extends JpaRepository<ClienteEntity, Integer> {
}
