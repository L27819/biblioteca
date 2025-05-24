package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.AutorDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Autor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

@WebServlet("/RegistroAutorServlet")
public class RegistroAutorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String nacionalidad = request.getParameter("nacionalidad");
            String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
            String fechaDefuncionStr = request.getParameter("fecha_defuncion");
            int numeroObras = Integer.parseInt(request.getParameter("numero_obras"));
            String imagen = request.getParameter("imagen");

            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
            LocalDate fechaDefuncion = fechaDefuncionStr != null && !fechaDefuncionStr.isEmpty()
                    ? LocalDate.parse(fechaDefuncionStr)
                    : null;

            Autor autor = new Autor();
            autor.setNombre(nombre);
            autor.setApellidos(apellidos);
            autor.setNacionalidad(nacionalidad);
            autor.setFecha_nacimiento(fechaNacimiento);
            autor.setFecha_defuncion(fechaDefuncion);
            autor.setNumero_obras(numeroObras);
            autor.setActivo(fechaDefuncion == null); // activo = true si está vivo
            autor.setImagen(imagen);

            try (Connection connection = new Database().getConnection()) {
                AutorDao autorDao = new AutorDao(connection);
                autorDao.insert(autor);
            }

            response.sendRedirect("listar-autores");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al registrar el autor.");
        }
    }
}
