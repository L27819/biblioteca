package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.UsuarioDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/detalle-usuario")
@MultipartConfig
public class DetalleUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = new Database().getConnection()) {
            UsuarioDao usuarioDao = new UsuarioDao(connection);
            Usuario usuario = usuarioDao.getById(id);

            request.setAttribute("usuarioDetalle", usuario);
            request.getRequestDispatcher("detalle-usuario.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al cargar el detalle del usuario.");
        }
    }
}