package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.AutorDao;
import com.svalero.biblioteca.dao.LibroDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Autor;
import com.svalero.biblioteca.model.Libro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/EditarAutorServlet")
public class EditarAutorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection connection = new Database().getConnection()) {
            AutorDao autorDao = new AutorDao(connection);
            Autor autor = autorDao.getById(id);
            request.setAttribute("autorEditar", autor);
            request.getRequestDispatcher("editar-autor.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al cargar autor.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_autor"));
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String nacionalidad = request.getParameter("nacionalidad");
            LocalDate nacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));
            String fechaDefuncionStr = request.getParameter("fecha_defuncion");
            LocalDate defuncion = fechaDefuncionStr != null && !fechaDefuncionStr.isEmpty() ? LocalDate.parse(fechaDefuncionStr) : null;
            boolean activo = Boolean.parseBoolean(request.getParameter("activo"));
            int numeroObras = Integer.parseInt(request.getParameter("numero_obras"));
            String imagen = request.getParameter("imagen");

            Autor autor = new Autor();
            autor.setId_autor(id);
            autor.setNombre(nombre);
            autor.setApellidos(apellidos);
            autor.setNacionalidad(nacionalidad);
            autor.setFecha_nacimiento(nacimiento);
            autor.setFecha_defuncion(defuncion);
            autor.setActivo(activo);
            autor.setNumero_obras(numeroObras);
            autor.setImagen(imagen);

            try (Connection connection = new Database().getConnection()) {
                AutorDao autorDao = new AutorDao(connection);
                autorDao.update(autor);
            }

            response.sendRedirect("listar-autores");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al editar autor.");
        }
    }
}