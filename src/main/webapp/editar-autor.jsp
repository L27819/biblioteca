<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.model.Autor" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
  Autor autorEditar = (Autor) request.getAttribute("autorEditar");

  if (autorEditar == null) {
%>
<div class="alert alert-danger text-center my-5">Autor no encontrado.</div>
<%
    return;
  }
%>

<div class="container my-5">
  <h2 class="text-center mb-4">Editar Autor ✍️</h2>

  <form action="EditarAutorServlet" method="post" class="row g-3">
    <input type="hidden" name="id_autor" value="<%= autorEditar.getId_autor() %>">

    <div class="col-md-6">
      <label class="form-label">Nombre</label>
      <input type="text" name="nombre" value="<%= autorEditar.getNombre() %>" class="form-control" required>
    </div>

    <div class="col-md-6">
      <label class="form-label">Apellidos</label>
      <input type="text" name="apellidos" value="<%= autorEditar.getApellidos() %>" class="form-control">
    </div>

    <div class="col-md-6">
      <label class="form-label">Nacionalidad</label>
      <input type="text" name="nacionalidad" value="<%= autorEditar.getNacionalidad() %>" class="form-control">
    </div>

    <div class="col-md-6">
      <label class="form-label">Fecha de nacimiento</label>
      <input type="date" name="fecha_nacimiento" value="<%= autorEditar.getFecha_nacimiento() %>" class="form-control">
    </div>

    <div class="col-md-6">
      <label class="form-label">Fecha de defunción</label>
      <input type="date" name="fecha_defuncion" value="<%= autorEditar.getFecha_defuncion() != null ? autorEditar.getFecha_defuncion() : "" %>" class="form-control">
    </div>

    <div class="col-md-3">
      <label class="form-label">Activo</label>
      <select name="activo" class="form-select">
        <option value="true" <%= autorEditar.isActivo() ? "selected" : "" %>>Sí</option>
        <option value="false" <%= !autorEditar.isActivo() ? "selected" : "" %>>No</option>
      </select>
    </div>

    <div class="col-md-3">
      <label class="form-label">Número de obras</label>
      <input type="number" name="numero_obras" value="<%= autorEditar.getNumero_obras() %>" class="form-control">
    </div>

    <div class="col-md-12">
      <label class="form-label">Imagen (URL)</label>
      <input type="text" name="imagen" value="<%= autorEditar.getImagen() %>" class="form-control">
    </div>

    <div class="col-12 text-center">
      <button type="submit" class="btn btn-primary px-5"
              onclick="return confirm('¿Seguro que deseas editar este autor?');">Guardar cambios</button>
    </div>
  </form>
</div>

<%@ include file="includes/footer.jsp" %>