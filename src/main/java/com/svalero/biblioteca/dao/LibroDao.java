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

    public Libro getById(int idLibro) throws SQLException {
        String sql = "SELECT * FROM Libros WHERE id_libro = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLibro);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Libro libro = new Libro();
                libro.setId_libro(result.getInt("id_libro"));
                libro.setTitulo(result.getString("titulo"));
                libro.setGenero(result.getString("genero"));
                libro.setEditorial(result.getString("editorial"));
                libro.setFecha_publicacion(result.getDate("fecha_publicacion").toLocalDate());
                libro.setPaginas(result.getInt("paginas"));
                libro.setPrecio(result.getFloat("precio"));
                libro.setDisponible(result.getBoolean("disponible"));
                libro.setDescripcion(result.getString("descripcion"));
                libro.setImagen(result.getString("imagen"));
                return libro;
            } else {
                return null;
            }
        }
    }

    public void deleteById(int idLibro) throws SQLException {
        String sql = "DELETE FROM Libros WHERE id_libro = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLibro);
            statement.executeUpdate();
        }
    }

    public void update(Libro libro) throws SQLException {
        String sql = "UPDATE Libros SET titulo=?, editorial=?, genero=?, paginas=?, precio=?, descripcion=? WHERE id_libro=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getEditorial());
            statement.setString(3, libro.getGenero());
            statement.setInt(4, libro.getPaginas());
            statement.setFloat(5, libro.getPrecio());
            statement.setString(6, libro.getDescripcion());
            statement.setInt(7, libro.getId_libro());

            statement.executeUpdate();
        }
    }

    public void insert(Libro libro) throws SQLException {
        String sql = "INSERT INTO Libros (isbn, titulo, editorial, fecha_publicacion, genero, paginas, precio, disponible, imagen, id_autor) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getIsbn());
            statement.setString(2, libro.getTitulo());
            statement.setString(3, libro.getEditorial());
            statement.setDate(4, Date.valueOf(libro.getFecha_publicacion()));
            statement.setString(5, libro.getGenero());
            statement.setInt(6, libro.getPaginas());
            statement.setFloat(7, libro.getPrecio());
            statement.setBoolean(8, libro.isDisponible());
            statement.setString(9, libro.getImagen());
            statement.setInt(10, libro.getId_autor());

            statement.executeUpdate();
        }
    }
}