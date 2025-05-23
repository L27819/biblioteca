package com.svalero.biblioteca.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Usuario {

    private int id_usuario;
    private String nombre_usuario;
    private String nombre;
    private String apellidos;
    private int edad;
    private String email;
    private String telefono;
    private String contrasena;
    private String tipo_usuario;
    private boolean activo;
    private LocalDate fecha_registro;
    private String imagen;
}