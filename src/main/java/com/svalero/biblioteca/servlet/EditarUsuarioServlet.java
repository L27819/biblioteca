package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.UsuarioDao;
import com.svalero.biblioteca.database.Database;
import com.svalero.biblioteca.model.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/EditarUsuarioServlet"})
public class EditarUsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = (new Database()).getConnection()) {
            UsuarioDao usuarioDao = new UsuarioDao(connection);
            Usuario usuario = usuarioDao.getById(id);
            request.setAttribute("usuarioEditar", usuario);
            request.getRequestDispatcher("editar-usuario.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            ((Exception)e).printStackTrace();
            response.sendError(500, "Error al cargar el usuario para editar.");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id_usuario"));
            String nombre_usuario = request.getParameter("nombre_usuario");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String tipo_usuario = request.getParameter("tipo_usuario");
            String imagen = request.getParameter("imagen");
            boolean activo = Boolean.parseBoolean(request.getParameter("activo"));

            try (Connection connection = (new Database()).getConnection()) {
                UsuarioDao usuarioDao = new UsuarioDao(connection);
                Usuario usuario = usuarioDao.getById(id);
                usuario.setNombre_usuario(nombre_usuario);
                usuario.setNombre(nombre);
                usuario.setApellidos(apellidos);
                usuario.setEmail(email);
                usuario.setTelefono(telefono);
                usuario.setEdad(edad);
                usuario.setTipo_usuario(tipo_usuario);
                usuario.setImagen(imagen);
                usuario.setActivo(activo);
                usuarioDao.update(usuario);
            }

            response.sendRedirect("listar-usuarios");
        } catch (ClassNotFoundException | SQLException e) {
            ((Exception)e).printStackTrace();
            response.sendError(500, "Error al actualizar el usuario.");
        }

    }
}