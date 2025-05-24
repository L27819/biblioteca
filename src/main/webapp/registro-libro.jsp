<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<div class="container my-5">
  <h2 class="text-center mb-4">Añadir Nuevo Libro 📖</h2>

  <form action="RegistroLibroServlet" method="post" class="row g-3">
    <div class="col-md-6">
      <label class="form-label">Título</label>
      <input type="text" name="titulo" class="form-control" required>
    </div>

    <div class="col-md-6">
      <label class="form-label">ISBN</label>
      <input type="text" name="isbn" class="form-control" required>
    </div>

    <div class="col-md-6">
      <label class="form-label">Editorial</label>
      <input type="text" name="editorial" class="form-control">
    </div>

    <div class="col-md-6">
      <label class="form-label">Fecha de publicación</label>
      <input type="date" name="fecha_publicacion" class="form-control">
    </div>

    <div class="col-md-6">
      <label class="form-label">Género</label>
      <input type="text" name="genero" class="form-control">
    </div>

    <div class="col-md-6">
      <label class="form-label">Páginas</label>
      <input type="number" name="paginas" class="form-control">
    </div>

    <div class="col-md-6">
      <label class="form-label">Precio (€)</label>
      <input type="number" step="0.01" name="precio" class="form-control">
    </div>

    <div class="col-md-6">
      <label class="form-label">Imagen (URL)</label>
      <input type="text" name="imagen" class="form-control">
    </div>

    <div class="col-md-6">
      <label class="form-label">Autor (ID)</label>
      <input type="number" name="id_autor" class="form-control" required>
    </div>

    <div class="col-12">
      <button type="submit" class="btn btn-primary w-100">Registrar libro</button>
    </div>
  </form>
</div>

<%@ include file="includes/footer.jsp" %>