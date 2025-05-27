package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.LibroDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Libro;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/detalle-libro")
@MultipartConfig
public class DetalleLibroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idLibro = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = new Database().getConnection()) {
            LibroDao libroDao = new LibroDao(connection);
            Libro libro = libroDao.getById(idLibro);

            request.setAttribute("libro", libro);
            request.getRequestDispatcher("detalle-libro.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al cargar los detalles del libro.");
        }
    }
}