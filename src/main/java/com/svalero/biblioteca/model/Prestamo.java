package com.svalero.biblioteca.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Prestamo {

    private int id_prestamo;
    private int id_usuario;
    private int id_libro;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String estado;
}
