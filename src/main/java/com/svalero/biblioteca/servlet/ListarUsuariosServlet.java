package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.UsuarioDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listar-usuarios")
@MultipartConfig
public class ListarUsuariosServlet extends HttpServlet {

    private static final int USUARIOS_POR_PAGINA = 6;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int paginaActual = 1;
        try {
            String param = request.getParameter("pagina");
            if (param != null) {
                paginaActual = Integer.parseInt(param);
            }
        } catch (NumberFormatException ignored) {}

        String filtro = request.getParameter("filtro");

        try (Connection connection = new Database().getConnection()) {
            UsuarioDao usuarioDao = new UsuarioDao(connection);
            List<Usuario> usuariosPagina;
            int totalUsuarios;

            if (filtro != null && !filtro.trim().isEmpty()) {
                List<Usuario> resultados = usuarioDao.buscarPorTexto(filtro);
                totalUsuarios = resultados.size();

                int offset = (paginaActual - 1) * USUARIOS_POR_PAGINA;
                int fin = Math.min(offset + USUARIOS_POR_PAGINA, totalUsuarios);
                if (offset >= totalUsuarios) {
                    usuariosPagina = List.of(); // Evitar errores si el offset supera
                } else {
                    usuariosPagina = resultados.subList(offset, fin);
                }

                request.setAttribute("filtro", filtro); // para que se mantenga en el input
            } else {
                int offset = (paginaActual - 1) * USUARIOS_POR_PAGINA;
                usuariosPagina = usuarioDao.getPaginated(offset, USUARIOS_POR_PAGINA);
                totalUsuarios = usuarioDao.getTotalCount();
            }

            int totalPaginas = (int) Math.ceil((double) totalUsuarios / USUARIOS_POR_PAGINA);

            request.setAttribute("usuarios", usuariosPagina);
            request.setAttribute("paginaActual", paginaActual);
            request.setAttribute("totalPaginas", totalPaginas);
            request.setAttribute("ruta", "listar-usuarios");

            request.getRequestDispatcher("lista-usuarios.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(500, "Error al obtener la lista de usuarios.");
        }
    }
}