<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">📚 Biblioteca Lulu</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarContent" aria-controls="navbarContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Inicio</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="listar-libros">Libros</a>
                </li>

                <% if (usuario == null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="registroUsuario.jsp">Registro</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Iniciar sesión</a>
                </li>
                <% } else { %>

                <% if ("user".equalsIgnoreCase(usuario.getTipo_usuario())) { %>
                <li class="nav-item">
                    <a class="nav-link" href="zona-usuario.jsp">Mi zona</a>
                </li>
                <% } %>

                <% if ("admin".equalsIgnoreCase(usuario.getTipo_usuario())) { %>
                <li class="nav-item">
                    <a class="nav-link" href="panel-admin.jsp">Panel Admin</a>
                </li>
                <% } %>

                <li class="nav-item">
                    <span class="nav-link disabled">Hola, <%= usuario.getNombre_usuario() %></span>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Cerrar sesión</a>
                </li>
                <% } %>

            </ul>
        </div>
    </div>
</nav>