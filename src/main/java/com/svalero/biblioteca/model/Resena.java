package com.svalero.biblioteca.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Resena {

    private int id_resena;
    private int id_usuario;
    private int id_libro;
    private String comentario;
    private int puntuacion;
    private boolean me_gusta;
    private LocalDate fecha_resena;
}