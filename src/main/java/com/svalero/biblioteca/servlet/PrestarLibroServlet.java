package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/PrestarLibroServlet")
public class PrestarLibroServlet extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
        } else {
            int idLibro = Integer.parseInt(request.getParameter("id_libro"));
            LocalDate hoy = LocalDate.now();
            try (Connection connection = new Database().getConnection()) {
                String sql = "INSERT INTO Prestamos (id_usuario, id_libro, fecha_inicio, estado) VALUES (?, ?, ?, 'pendiente')";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setInt(1, usuario.getId_usuario());
                    stmt.setInt(2, idLibro);
                    stmt.setDate(3, Date.valueOf(hoy));
                    stmt.executeUpdate();
                }

                response.sendRedirect("zona-usuario"); // o donde prefieras

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendError(500, "Error al registrar el préstamo.");
            }
        }

    }
}