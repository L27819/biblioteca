package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.LibroDao;
import com.svalero.biblioteca.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/EliminarLibroServlet")
public class EliminarLibrosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idLibro = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = new Database().getConnection()) {
            LibroDao libroDao = new LibroDao(connection);
            libroDao.deleteById(idLibro);

            response.sendRedirect("listar-libros");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al eliminar el libro.");
        }
    }
}
