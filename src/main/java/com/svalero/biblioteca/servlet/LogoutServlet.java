package com.svalero.biblioteca.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    // Cierra la sesión y redirige al inicio
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invalidar la sesión actual (borra todos los atributos, incluido el usuario)
        HttpSession session = request.getSession(false); // false → no crea sesión si no existe
        if (session != null) {
            session.invalidate();
        }

        // Redirigir al inicio
        response.sendRedirect("index.jsp");
    }
}