package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.LibroDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Libro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/EditarLibroServlet")
public class EditarLibroServlet extends HttpServlet {

    // Muestra el formulario con los datos del libro
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idLibro = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = new Database().getConnection()) {
            LibroDao libroDao = new LibroDao(connection);
            Libro libro = libroDao.getById(idLibro);

            request.setAttribute("libro", libro);
            request.getRequestDispatcher("editar-libro.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al cargar el libro para editar.");
        }
    }

    // Guarda los cambios del libro
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idLibro = Integer.parseInt(request.getParameter("id_libro"));
        String titulo = request.getParameter("titulo");
        String editorial = request.getParameter("editorial");
        String genero = request.getParameter("genero");
        int paginas = Integer.parseInt(request.getParameter("paginas"));
        float precio = Float.parseFloat(request.getParameter("precio"));
        String descripcion = request.getParameter("descripcion");

        try (Connection connection = new Database().getConnection()) {
            LibroDao libroDao = new LibroDao(connection);

            Libro libro = libroDao.getById(idLibro);
            libro.setTitulo(titulo);
            libro.setEditorial(editorial);
            libro.setGenero(genero);
            libro.setPaginas(paginas);
            libro.setPrecio(precio);
            libro.setDescripcion(descripcion);

            libroDao.update(libro);

            response.sendRedirect("listar-libros");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al actualizar el libro.");
        }
    }
}