package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.model.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDao {

    private final Connection connection;

    public AutorDao(Connection connection) {
        this.connection = connection;
    }

    // Insertar un nuevo autor
    public void insert(Autor autor) throws SQLException {
        String sql = "INSERT INTO Autores (nombre, apellidos, nacionalidad, fecha_nacimiento, fecha_defuncion, activo, numero_obras, imagen) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getApellidos());
            stmt.setString(3, autor.getNacionalidad());
            stmt.setDate(4, Date.valueOf(autor.getFecha_nacimiento()));

            if (autor.getFecha_defuncion() != null) {
                stmt.setDate(5, Date.valueOf(autor.getFecha_defuncion()));
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.setBoolean(6, autor.isActivo());
            stmt.setInt(7, autor.getNumero_obras());
            stmt.setString(8, autor.getImagen());

            stmt.executeUpdate();
        }
    }

    // Obtener todos los autores
    public List<Autor> getAll() throws SQLException {
        String sql = "SELECT * FROM Autores";
        List<Autor> autores = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId_autor(rs.getInt("id_autor"));
                autor.setNombre(rs.getString("nombre"));
                autor.setApellidos(rs.getString("apellidos"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                autor.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());

                Date fechaDef = rs.getDate("fecha_defuncion");
                if (fechaDef != null) {
                    autor.setFecha_defuncion(fechaDef.toLocalDate());
                }

                autor.setActivo(rs.getBoolean("activo"));
                autor.setNumero_obras(rs.getInt("numero_obras"));
                autor.setImagen(rs.getString("imagen"));

                autores.add(autor);
            }
        }

        return autores;
    }

    // Obtener un autor por ID
    public Autor getById(int id) throws SQLException {
        String sql = "SELECT * FROM Autores WHERE id_autor = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Autor autor = new Autor();
                autor.setId_autor(rs.getInt("id_autor"));
                autor.setNombre(rs.getString("nombre"));
                autor.setApellidos(rs.getString("apellidos"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                autor.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());

                Date fechaDef = rs.getDate("fecha_defuncion");
                if (fechaDef != null) {
                    autor.setFecha_defuncion(fechaDef.toLocalDate());
                }

                autor.setActivo(rs.getBoolean("activo"));
                autor.setNumero_obras(rs.getInt("numero_obras"));
                autor.setImagen(rs.getString("imagen"));

                return autor;
            } else {
                return null;
            }
        }
    }

    public void update(Autor autor) throws SQLException {
        String sql = "UPDATE Autores SET nombre=?, apellidos=?, nacionalidad=?, fecha_nacimiento=?, fecha_defuncion=?, activo=?, numero_obras=?, imagen=? WHERE id_autor=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getApellidos());
            stmt.setString(3, autor.getNacionalidad());
            stmt.setDate(4, java.sql.Date.valueOf(autor.getFecha_nacimiento()));
            if (autor.getFecha_defuncion() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(autor.getFecha_defuncion()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }
            stmt.setBoolean(6, autor.isActivo());
            stmt.setInt(7, autor.getNumero_obras());
            stmt.setString(8, autor.getImagen());
            stmt.setInt(9, autor.getId_autor());
            stmt.executeUpdate();
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Autores WHERE id_autor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public List<Autor> getPaginated(int offset, int limit) throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autores LIMIT ? OFFSET ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId_autor(rs.getInt("id_autor"));
                autor.setNombre(rs.getString("nombre"));
                autor.setApellidos(rs.getString("apellidos"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                autor.setFecha_nacimiento(rs.getDate("fecha_nacimiento") != null ? rs.getDate("fecha_nacimiento").toLocalDate() : null);
                autor.setFecha_defuncion(rs.getDate("fecha_defuncion") != null ? rs.getDate("fecha_defuncion").toLocalDate() : null);
                autor.setActivo(rs.getBoolean("activo"));
                autor.setNumero_obras(rs.getInt("numero_obras"));
                autor.setImagen(rs.getString("imagen"));
                autores.add(autor);
            }
        }

        return autores;
    }

    public int getTotalCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Autores";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    public List<Autor> buscarPorTexto(String texto, int offset, int limit) throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autores WHERE nombre LIKE ? OR apellidos LIKE ? OR nacionalidad LIKE ? LIMIT ? OFFSET ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String filtro = "%" + texto + "%";
            stmt.setString(1, filtro);
            stmt.setString(2, filtro);
            stmt.setString(3, filtro);
            stmt.setInt(4, limit);
            stmt.setInt(5, offset);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId_autor(rs.getInt("id_autor"));
                autor.setNombre(rs.getString("nombre"));
                autor.setApellidos(rs.getString("apellidos"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                autor.setNumero_obras(rs.getInt("numero_obras"));
                autor.setImagen(rs.getString("imagen"));
                autor.setActivo(rs.getBoolean("activo"));
                autores.add(autor);
            }
        }
        return autores;
    }

    public int contarPorTexto(String texto) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Autores WHERE nombre LIKE ? OR apellidos LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String filtro = "%" + texto + "%";
            stmt.setString(1, filtro);
            stmt.setString(2, filtro);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

}
