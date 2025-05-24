package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.LibroDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Libro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

@WebServlet("/RegistroLibroServlet")
public class RegistroLibroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String titulo = request.getParameter("titulo");
            String isbn = request.getParameter("isbn");
            String editorial = request.getParameter("editorial");
            String genero = request.getParameter("genero");
            int paginas = Integer.parseInt(request.getParameter("paginas"));
            float precio = Float.parseFloat(request.getParameter("precio"));
            String imagen = request.getParameter("imagen");
            int idAutor = Integer.parseInt(request.getParameter("id_autor"));
            LocalDate fechaPublicacion = LocalDate.parse(request.getParameter("fecha_publicacion"));

            Libro libro = new Libro();
            libro.setTitulo(titulo);
            libro.setIsbn(isbn);
            libro.setEditorial(editorial);
            libro.setGenero(genero);
            libro.setPaginas(paginas);
            libro.setPrecio(precio);
            libro.setImagen(imagen);
            libro.setFecha_publicacion(fechaPublicacion);
            libro.setDisponible(true);
            libro.setId_autor(idAutor);

            try (Connection connection = new Database().getConnection()) {
                LibroDao libroDao = new LibroDao(connection);
                libroDao.insert(libro);
            }

            response.sendRedirect("listar-libros");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al registrar el libro.");
        }
    }
}

