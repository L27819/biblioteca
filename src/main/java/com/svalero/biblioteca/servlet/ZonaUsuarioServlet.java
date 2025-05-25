package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.LibroDao;
import com.svalero.biblioteca.dao.PrestamoDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Libro;
import com.svalero.biblioteca.model.Prestamo;
import com.svalero.biblioteca.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/zona-usuario")
public class ZonaUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !"user".equalsIgnoreCase(usuario.getTipo_usuario())) {
            response.sendRedirect("index.jsp");
            return;
        }

        try (Connection connection = new Database().getConnection()) {
            PrestamoDao prestamoDao = new PrestamoDao(connection);
            LibroDao libroDao = new LibroDao(connection);
            List<Prestamo> prestamos = prestamoDao.getByUsuarioId(usuario.getId_usuario());

// Crear un map con el ID del libro y su título

            Map<Integer, String> titulosLibros = new HashMap<>();
            for (Prestamo p : prestamos) {
                Libro libro = libroDao.getById(p.getId_libro());
                if (libro != null) {
                    titulosLibros.put(p.getId_libro(), libro.getTitulo());
                }
            }

            request.setAttribute("prestamos", prestamos);
            request.setAttribute("titulosLibros", titulosLibros);

            request.getRequestDispatcher("zona-usuario.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al cargar la zona de usuario.");
        }
    }
}