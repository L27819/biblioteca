package com.svalero.biblioteca.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Autor {

    private int id_autor;
    private String nombre;
    private String apellidos;
    private String nacionalidad;
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_defuncion;
    private boolean activo;
    private int numero_obras;
    private String imagen;
}