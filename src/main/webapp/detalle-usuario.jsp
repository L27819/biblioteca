<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.model.Usuario" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
    Usuario usuarioDetalle = (Usuario) request.getAttribute("usuarioDetalle");
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
            <div class="col-md-8">
                <h2 class="mb-3"><%= usuarioDetalle.getNombre() %> <%= usuario.getApellidos() %></h2>
                <p><strong>Usuario:</strong> <%= usuarioDetalle.getNombre_usuario() %></p>
                <p><strong>Email:</strong> <%= usuarioDetalle.getEmail() %></p>
                <p><strong>Teléfono:</strong> <%= usuarioDetalle.getTelefono() %></p>
                <p><strong>Edad:</strong> <%= usuarioDetalle.getEdad() %> años</p>
                <p><strong>Rol:</strong> <%= usuarioDetalle.getTipo_usuario() %></p>
                <p><strong>Activo:</strong> <%= usuarioDetalle.isActivo() ? "Sí" : "No" %></p>
                <p><strong>Fecha de Registro:</strong> <%= usuarioDetalle.getFecha_registro() %></p>
            </div>
        </div>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>