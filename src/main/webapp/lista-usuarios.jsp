<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.biblioteca.model.Usuario" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%

    if (usuario == null || !"admin".equalsIgnoreCase(usuario.getTipo_usuario())) {
        response.sendRedirect("index.jsp");
        return;
    }
    List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("usuarios");
%>

<div class="container my-5">
    <h2 class="mb-4 text-center">Listado de Usuarios 👥</h2>

    <div class="row">
        <% if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
            for (Usuario u : listaUsuarios) {
        %>
        <div class="col-md-4 mb-4">
            <div class="card h-100 shadow rounded-4">
                <div class="card-body d-flex flex-column justify-content-between">
                    <div>
                        <h5 class="card-title fw-bold text-primary">
                            <a href="detalle-usuario?id=<%= u.getId_usuario() %>" class="text-decoration-none text-primary">
                                👤 <%= u.getNombre_usuario() %>
                            </a>
                        </h5>
                        <p class="card-text">
                            <strong>Nombre:</strong> <%= u.getNombre() %> <%= u.getApellidos() %><br>
                            <strong>Email:</strong> <%= u.getEmail() %><br>
                            <strong>Rol:</strong> <%= u.getTipo_usuario() %><br>
                            <strong>Activo:</strong> <%= u.isActivo() ? "Sí" : "No" %>
                        </p>
                    </div>

                    <div class="d-flex gap-2">
                        <a href="EditarUsuarioServlet?id=<%= u.getId_usuario() %>" class="btn btn-warning btn-sm">Editar</a>
                        <a href="EliminarUsuarioServlet?id=<%= u.getId_usuario() %>" class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Seguro que deseas eliminar este usuario?');">Eliminar</a>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p class="text-center text-danger">No hay usuarios registrados.</p>
        <% } %>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>