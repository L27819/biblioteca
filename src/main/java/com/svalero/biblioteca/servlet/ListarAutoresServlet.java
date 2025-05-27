package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.AutorDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Autor;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listar-autores")
@MultipartConfig
public class ListarAutoresServlet extends HttpServlet {

    private static final int AUTORES_POR_PAGINA = 6;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filtro = request.getParameter("busqueda");
        int paginaActual = 1;
        try {
            String param = request.getParameter("pagina");
            if (param != null) {
                paginaActual = Integer.parseInt(param);
            }
        } catch (NumberFormatException ignored) {}

        int offset = (paginaActual - 1) * AUTORES_POR_PAGINA;

        try (Connection connection = new Database().getConnection()) {
            AutorDao autorDao = new AutorDao(connection);

            List<Autor> autoresPagina;
            int totalAutores;

            if (filtro != null && !filtro.trim().isEmpty()) {
                filtro = filtro.trim();
                autoresPagina = autorDao.buscarPorTexto(filtro, offset, AUTORES_POR_PAGINA);
                totalAutores = autorDao.contarPorTexto(filtro);
                request.setAttribute("busqueda", filtro);
            } else {
                autoresPagina = autorDao.getPaginated(offset, AUTORES_POR_PAGINA);
                totalAutores = autorDao.getTotalCount();
            }

            int totalPaginas = (int) Math.ceil((double) totalAutores / AUTORES_POR_PAGINA);

            request.setAttribute("autores", autoresPagina);
            request.setAttribute("paginaActual", paginaActual);
            request.setAttribute("totalPaginas", totalPaginas);
            request.setAttribute("ruta", "listar-autores");

            request.getRequestDispatcher("listar-autores.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al obtener la lista de autores.");
        }
    }
}