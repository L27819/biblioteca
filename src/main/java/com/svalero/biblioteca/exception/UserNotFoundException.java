package com.svalero.biblioteca.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Usuario no encontrado");
    }
}