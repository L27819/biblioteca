package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.AutorDao;
import com.svalero.biblioteca.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/EliminarAutorServlet")
public class EliminarAutorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection connection = new Database().getConnection()) {
            AutorDao autorDao = new AutorDao(connection);
            autorDao.deleteById(id);
            response.sendRedirect("listar-autores");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al eliminar autor.");
        }
    }
}