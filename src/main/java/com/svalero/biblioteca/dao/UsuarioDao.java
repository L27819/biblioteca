package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.exception.UserNotFoundException;
import com.svalero.biblioteca.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private final Connection connection;

    public UsuarioDao(Connection connection) {
        this.connection = connection;
    }

    public Usuario login(String nombre_usuario, String contrasena)
            throws SQLException, UserNotFoundException {

        String sql = "SELECT * FROM Usuarios WHERE nombre_usuario = ? AND contrasena = SHA1(?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre_usuario);
            statement.setString(2, contrasena);

            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) throw new UserNotFoundException();
                    return mapUsuario(rs);
            }
        }
    }

    public List<Usuario> getAll() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(resultSet.getInt("id_usuario"));
                usuario.setNombre_usuario(resultSet.getString("nombre_usuario"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellidos(resultSet.getString("apellidos"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setTipo_usuario(resultSet.getString("tipo_usuario"));
                usuario.setActivo(resultSet.getBoolean("activo"));
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    public Usuario getById(int id) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE id_usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuario.setFecha_registro(rs.getDate("fecha_registro").toLocalDate());
                usuario.setImagen(rs.getString("imagen"));
                return usuario;
            }
            return null;
        }
    }

    private Usuario mapUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(rs.getInt("id_usuario"));
        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellidos(rs.getString("apellidos"));
        usuario.setEmail(rs.getString("email"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setTipo_usuario(rs.getString("tipo_usuario"));
        usuario.setActivo(rs.getBoolean("activo"));
        usuario.setImagen(rs.getString("imagen"));

        return usuario;
    }

    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuarios (nombre, apellidos, email, nombre_usuario, contrasena, tipo_usuario, activo, fecha_registro) " +
                "VALUES (?, ?, ?, ?, SHA1(?), ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellidos());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getNombre_usuario());
            statement.setString(5, usuario.getContrasena());
            statement.setString(6, usuario.getTipo_usuario());
            statement.setBoolean(7, usuario.isActivo());
            statement.setDate(8, java.sql.Date.valueOf(usuario.getFecha_registro()));

            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuarios SET nombre_usuario = ?, nombre = ?, apellidos = ?, email = ?, telefono = ?, edad = ?, tipo_usuario = ?, activo = ?, imagen = ? WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre_usuario());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellidos());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefono());
            stmt.setInt(6, usuario.getEdad());
            stmt.setString(7, usuario.getTipo_usuario());
            stmt.setBoolean(8, usuario.isActivo());
            stmt.setString(9, usuario.getImagen());
            stmt.setInt(10, usuario.getId_usuario());

            stmt.executeUpdate();
        }
    }
}
