package com.svalero.biblioteca.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Libro {

    private int id_libro;
    private String isbn;
    private String titulo;
    private String editorial;
    private LocalDate fecha_publicacion;
    private String genero;
    private String descripcion;
    private int paginas;
    private float precio;
    private boolean disponible;
    private String imagen;
    private int id_autor;
}