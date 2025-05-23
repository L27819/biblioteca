package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDao {

    private final Connection connection;

    public LibroDao(Connection connection) {
        this.connection = connection;
    }

    public List<Libro> getAll() throws SQLException {
        String sql = "SELECT * FROM Libros";
        PreparedStatement statement = statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        ArrayList<Libro> listaLibros = new ArrayList<>();

            while (result.next()) {

                Libro libro = new Libro();
                libro.setId_libro(result.getInt("id_libro"));
                libro.setTitulo(result.getString("titulo"));
                libro.setGenero(result.getString("genero"));
                libro.setEditorial(result.getString("editorial"));
                libro.setFecha_publicacion(result.getDate("fecha_publicacion").toLocalDate());
                libro.setPaginas(result.getInt("paginas"));
                libro.setPrecio(result.getFloat("precio"));
                libro.setDisponible(result.getBoolean("disponible"));
                libro.setImagen(result.getString("imagen"));

                listaLibros.add(libro);
            }
            result.close();
            statement.close();
            return listaLibros;
    }
}