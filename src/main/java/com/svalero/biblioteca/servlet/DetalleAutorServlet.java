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

@WebServlet("/detalle-autor")
@MultipartConfig
public class DetalleAutorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = new Database().getConnection()) {
            AutorDao autorDao = new AutorDao(connection);
            Autor autor = autorDao.getById(id);

            request.setAttribute("autor", autor);
            request.getRequestDispatcher("detalle-autor.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error al cargar el autor.");
        }
    }
}