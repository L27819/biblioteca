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
    <h2 class="text-center mb-4">Mis Datos Personales</h2>

    <form>
        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input type="text" class="form-control" value="<%= usuario.getNombre() %>" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">Apellidos</label>
            <input type="text" class="form-control" value="<%= usuario.getApellidos() %>" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">Nombre de usuario</label>
            <input type="text" class="form-control" value="<%= usuario.getNombre_usuario() %>" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" class="form-control" value="<%= usuario.getEmail() %>" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">Contraseña</label>
            <input type="password" class="form-control" value="<%= usuario.getContrasena() %>" readonly>
        </div>

    </form>
    <div class="d-grid gap-2">
        <a href="editar-datos.jsp" class="btn btn-warning">Editar mis datos</a>
        <a href="panel-usuario.jsp" class="btn btn-secondary">Volver</a>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>