package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/EditarDatosServlet")
public class EditarDatosServlet extends HttpServlet {

    private String sha1(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(texto.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al cifrar contraseña", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String nuevoNombre = request.getParameter("nombre");
        String nuevosApellidos = request.getParameter("apellidos");
        String nuevoNombreUsuario = request.getParameter("nombre_usuario");
        String nuevoEmail = request.getParameter("email");
        String nuevaContrasena = request.getParameter("contrasena");

        try (Connection connection = new Database().getConnection()) {
            String sql = "UPDATE usuarios SET nombre = ?, apellidos = ?, nombre_usuario = ?, email = ?, contrasena = ? WHERE id_usuario = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevosApellidos);
            ps.setString(3, nuevoNombreUsuario);
            ps.setString(4, nuevoEmail);
            ps.setString(5, sha1(nuevaContrasena));  // Siempre se cifra
            ps.setInt(6, usuario.getId_usuario());

            ps.executeUpdate();

            // Actualizamos también el objeto de sesión
            usuario.setNombre(nuevoNombre);
            usuario.setApellidos(nuevosApellidos);
            usuario.setNombre_usuario(nuevoNombreUsuario);
            usuario.setEmail(nuevoEmail);
            usuario.setContrasena(sha1(nuevaContrasena));

            session.setAttribute("usuario", usuario);
            response.sendRedirect("mis-datos.jsp");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al actualizar los datos.");
        }
    }
}