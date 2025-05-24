package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.UsuarioDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.ServletException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String nombre_usuario = request.getParameter("nombre_usuario");
        String contrasena = request.getParameter("contrasena");

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setNombre_usuario(nombre_usuario);
        usuario.setContrasena(contrasena);
        usuario.setTipo_usuario("user"); // siempre user por defecto
        usuario.setActivo(true);
        usuario.setFecha_registro(LocalDate.now());

        try (Connection connection = new Database().getConnection()) {
            UsuarioDao usuarioDao = new UsuarioDao(connection);
            usuarioDao.insert(usuario);

            response.sendRedirect("index.jsp");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "No se pudo registrar el usuario.");
            request.getRequestDispatcher("registro-usuario.jsp").forward(request, response);
        }
    }
}