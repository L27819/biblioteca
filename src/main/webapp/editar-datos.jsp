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
    <h2 class=" text-center mb-4">Editar mis datos ✍️</h2>
    <form action="EditarDatosServlet" method="post">
        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input type="text" name="nombre" class="form-control" value="<%= usuario.getNombre() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Apellidos</label>
            <input type="text" name="apellidos" class="form-control" value="<%= usuario.getApellidos() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Nombre de usuario</label>
            <input type="text" name="nombre_usuario" class="form-control" value="<%= usuario.getNombre_usuario() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email" class="form-control" value="<%= usuario.getEmail() %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Contraseña</label>
            <input type="password" name="contrasena" class="form-control" value="<%= usuario.getContrasena() %>"
                   required>
        </div>
        <button type="submit" class="btn btn-success">Guardar cambios</button>
        <a href="mis-datos.jsp" class="btn btn-secondary">Cancelar</a>
    </form>
</div>

<%@ include file="includes/footer.jsp" %>