package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.UsuarioDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.exception.UserNotFoundException;
import com.svalero.biblioteca.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre_usuario = request.getParameter("nombre_usuario");
        String contrasena = request.getParameter("contrasena");

        try (Connection connection = new Database().getConnection()){
            UsuarioDao usuarioDao = new UsuarioDao(connection);

            Usuario usuario = usuarioDao.login(nombre_usuario, contrasena);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            if ("admin".equalsIgnoreCase(usuario.getTipo_usuario())) {
                response.sendRedirect("panel-admin.jsp");
            } else {
                response.sendRedirect("lista-libros.jsp");
            }

        } catch (UserNotFoundException e) {
            request.setAttribute("errorMessage", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("login-error.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al conectar con la base de datos.");
            request.getRequestDispatcher("login-error.jsp").forward(request, response);
        }

    }
}