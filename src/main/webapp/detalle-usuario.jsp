<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.model.Usuario" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
    if (usuario == null) {
%>
<div class="alert alert-danger text-center">Usuario no encontrado.</div>
<%
        return;
    }
%>

<div class="container my-5">
    <div class="card shadow p-4">
        <div class="row">
            <div class="col-md-4 text-center">
                <img src="<%= usuario.getImagen() %>" class="img-fluid rounded-circle" alt="Imagen del usuario">
            </div>
            <div class="col-md-8">
                <h2 class="mb-3"><%= usuario.getNombre() %> <%= usuario.getApellidos() %></h2>
                <p><strong>Usuario:</strong> <%= usuario.getNombre_usuario() %></p>
                <p><strong>Email:</strong> <%= usuario.getEmail() %></p>
                <p><strong>Teléfono:</strong> <%= usuario.getTelefono() %></p>
                <p><strong>Edad:</strong> <%= usuario.getEdad() %> años</p>
                <p><strong>Rol:</strong> <%= usuario.getTipo_usuario() %></p>
                <p><strong>Activo:</strong> <%= usuario.isActivo() ? "Sí" : "No" %></p>
                <p><strong>Fecha de Registro:</strong> <%= usuario.getFecha_registro() %></p>
            </div>
        </div>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>