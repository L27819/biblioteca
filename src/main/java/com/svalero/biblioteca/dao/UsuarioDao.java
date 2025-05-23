package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.exception.UserNotFoundException;
import com.svalero.biblioteca.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
