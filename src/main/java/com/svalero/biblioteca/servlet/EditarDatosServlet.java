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
        String nuevoEmail = request.getParameter("email");
        String nuevaContrasena = request.getParameter("contrasena");

        try (Connection connection = new Database().getConnection()) {
            String sql;
            PreparedStatement ps;

            if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
                sql = "UPDATE usuarios SET nombre_usuario = ?, email = ?, contrasena = ? WHERE id_usuario = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, nuevoNombre);
                ps.setString(2, nuevoEmail);
                ps.setString(3, sha1(nuevaContrasena));
                ps.setInt(4, usuario.getId_usuario());

                usuario.setContrasena(sha1(nuevaContrasena));
            } else {
                sql = "UPDATE usuarios SET nombre_usuario = ?, email = ? WHERE id_usuario = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, nuevoNombre);
                ps.setString(2, nuevoEmail);
                ps.setInt(3, usuario.getId_usuario());
            }

            ps.executeUpdate();

            usuario.setNombre_usuario(nuevoNombre);
            usuario.setEmail(nuevoEmail);
            session.setAttribute("usuario", usuario);

            response.sendRedirect("mis-datos.jsp");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al actualizar los datos.");
        }
    }
}