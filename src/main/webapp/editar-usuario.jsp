<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.model.Usuario" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
    Usuario usuarioEditar = (Usuario) request.getAttribute("usuarioEditar");

    if (usuarioEditar == null) {
%>
<div class="alert alert-danger text-center my-5">Usuario no encontrado.</div>
<%
        return;
    }
%>

<div class="container my-5">
    <h2 class="text-center mb-4">Editar Usuario 👤</h2>

    <form action="EditarUsuarioServlet" method="post" class="row g-3">
        <input type="hidden" name="id_usuario" value="<%= usuarioEditar.getId_usuario() %>">

        <div class="col-md-6">
            <label class="form-label">Nombre de usuario</label>
            <input type="text" name="nombre_usuario" value="<%= usuarioEditar.getNombre_usuario() %>" class="form-control" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Email</label>
            <input type="email" name="email" value="<%= usuarioEditar.getEmail() %>" class="form-control" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Nombre</label>
            <input type="text" name="nombre" value="<%= usuarioEditar.getNombre() %>" class="form-control">
        </div>

        <div class="col-md-6">
            <label class="form-label">Apellidos</label>
            <input type="text" name="apellidos" value="<%= usuarioEditar.getApellidos() %>" class="form-control">
        </div>

        <div class="col-md-4">
            <label class="form-label">Teléfono</label>
            <input type="text" name="telefono" value="<%= usuarioEditar.getTelefono() %>" class="form-control">
        </div>

        <div class="col-md-4">
            <label class="form-label">Edad</label>
            <input type="number" name="edad" value="<%= usuarioEditar.getEdad() %>" class="form-control">
        </div>

        <div class="col-md-4">
            <label class="form-label">Rol</label>
            <select name="tipo_usuario" class="form-select">
                <option value="user" <%= "user".equals(usuarioEditar.getTipo_usuario()) ? "selected" : "" %>>Usuario</option>
                <option value="admin" <%= "admin".equals(usuarioEditar.getTipo_usuario()) ? "selected" : "" %>>Administrador</option>
            </select>
        </div>

        <div class="col-md-6">
            <label class="form-label">Imagen (URL)</label>
            <input type="text" name="imagen" value="<%= usuarioEditar.getImagen() %>" class="form-control">
        </div>

        <div class="col-md-6">
            <label class="form-label">Activo</label>
            <select name="activo" class="form-select">
                <option value="true" <%= usuarioEditar.isActivo() ? "selected" : "" %>>Sí</option>
                <option value="false" <%= !usuarioEditar.isActivo() ? "selected" : "" %>>No</option>
            </select>
        </div>

        <div class="col-12 text-center">
            <button type="submit" class="btn btn-primary px-5">Guardar cambios</button>
        </div>
    </form>
</div>

<%@ include file="includes/footer.jsp" %>