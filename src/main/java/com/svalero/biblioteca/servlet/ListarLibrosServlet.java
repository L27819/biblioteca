package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.LibroDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Libro;
import com.svalero.biblioteca.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listar-libros")
@MultipartConfig
public class ListarLibrosServlet extends HttpServlet {

    private static final int LIBROS_POR_PAGINA = 6;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String textoBusqueda = request.getParameter("busqueda");
        int paginaActual = 1;
        try {
            String param = request.getParameter("pagina");
            if (param != null) {
                paginaActual = Integer.parseInt(param);
            }
        } catch (NumberFormatException ignored) {}

        int offset = (paginaActual - 1) * LIBROS_POR_PAGINA;

        try (Connection connection = new Database().getConnection()) {
            LibroDao libroDao = new LibroDao(connection);
            List<Libro> librosPagina;
            int totalLibros;

            if (textoBusqueda != null && !textoBusqueda.trim().isEmpty()) {
                List<Libro> librosFiltrados = libroDao.buscarPorTexto(textoBusqueda.trim());
                totalLibros = librosFiltrados.size();

                int totalPaginas = (int) Math.ceil((double) totalLibros / LIBROS_POR_PAGINA);
                int toIndex = Math.min(offset + LIBROS_POR_PAGINA, totalLibros);

                librosPagina = librosFiltrados.subList(offset, toIndex);

                request.setAttribute("busqueda", textoBusqueda);
                request.setAttribute("libros", librosPagina);
                request.setAttribute("paginaActual", paginaActual);
                request.setAttribute("totalPaginas", totalPaginas);
                request.setAttribute("ruta", "listar-libros?busqueda=" + textoBusqueda);

            } else {
                librosPagina = libroDao.getPaginated(offset, LIBROS_POR_PAGINA);
                totalLibros = libroDao.getTotalCount();
                int totalPaginas = (int) Math.ceil((double) totalLibros / LIBROS_POR_PAGINA);

                request.setAttribute("libros", librosPagina);
                request.setAttribute("paginaActual", paginaActual);
                request.setAttribute("totalPaginas", totalPaginas);
                request.setAttribute("ruta", "listar-libros");
            }

            request.getRequestDispatcher("lista-libros.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al obtener la lista de libros.");
        }
    }
}