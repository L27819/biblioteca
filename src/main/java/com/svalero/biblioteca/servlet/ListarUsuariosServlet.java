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
import java.util.List;

@WebServlet("/listar-usuarios")
public class ListarUsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection connection = new Database().getConnection()) {
            UsuarioDao usuarioDao = new UsuarioDao(connection);
            List<Usuario> usuarios = usuarioDao.getAll();

            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("lista-usuarios.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al obtener la lista de usuarios.");
        }
    }
}