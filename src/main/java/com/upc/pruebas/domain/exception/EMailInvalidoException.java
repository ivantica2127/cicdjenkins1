package com.upc.pruebas.domain.exception;


public class EMailInvalidoException extends RuntimeException {

    public EMailInvalidoException(String email) {
        super("El correo electrónico proporcionado no es válido: " + email);
    }
}