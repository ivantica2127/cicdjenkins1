package com.upc.pruebas.domain.repository;

import java.util.List;

public interface ICRUD <T>{

    T insertar(T obj);
    T actualizar(T obj);
    List<T> listar();
    T listarPorId(Integer id);
}
