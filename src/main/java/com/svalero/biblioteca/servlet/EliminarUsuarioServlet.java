package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.UsuarioDao;
import com.svalero.biblioteca.database.Database;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/EliminarUsuarioServlet"})
public class EliminarUsuarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = (new Database()).getConnection()) {
            UsuarioDao usuarioDao = new UsuarioDao(connection);
            usuarioDao.delete(id);
            response.sendRedirect("listar-usuarios");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al eliminar usuario.");
        }

    }
}