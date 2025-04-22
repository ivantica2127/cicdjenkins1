package com.upc.pruebas.domain.model;

import com.upc.pruebas.domain.exception.EMailInvalidoException;

public class Cliente {

    private Integer id;
    private String nombre;
    private String email;

    public Cliente(String nombre, String email) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (email == null || !email.contains("@")) {
            throw new EMailInvalidoException(email); // Lanzamos la excepción personalizada
        }
        this.nombre = nombre;
        this.email = email;
    }

    public Cliente(Integer id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void actualizarDatos(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
}
