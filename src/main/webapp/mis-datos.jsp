<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<div class="container my-5">
    <h2 class="text-center mb-4">Mis datos personales</h2>

    <ul class="list-group mb-4">
        <li class="list-group-item"><strong>Nombre:</strong> <%= usuario.getNombre_usuario() %></li>
        <li class="list-group-item"><strong>Apellidos:</strong> <%= usuario.getApellidos() %></li>
        <li class="list-group-item"><strong>Email:</strong> <%= usuario.getEmail() %></li>
    </ul>

    <div class="d-grid gap-2">
        <a href="editar-datos.jsp" class="btn btn-warning">Editar mis datos</a>
        <a href="zona-usuario" class="btn btn-secondary">Volver</a>
    </div>

</div>

<%@ include file="includes/footer.jsp" %>