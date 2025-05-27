<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.model.Autor" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
  Autor autor = (Autor) request.getAttribute("autor");
  if (autor == null) {
%>
<div class="alert alert-danger text-center my-5">Autor no encontrado.</div>
<%
    return;
  }
%>

<div class="container my-5">
  <h2 class="mb-4 text-center">📚 Detalles del Autor</h2>

  <div class="card mx-auto shadow p-4" style="max-width: 600px;">
    <img src="../biblio_images/<%= autor.getImagen() %>" class="card-img-top mb-3" alt="Imagen del autor">
    <div class="card-body">
      <h5 class="card-title"><%= autor.getNombre() %> <%= autor.getApellidos() %></h5>
      <p class="card-text"><strong>Nacionalidad:</strong> <%= autor.getNacionalidad() %></p>
      <p class="card-text"><strong>Fecha de nacimiento:</strong> <%= autor.getFecha_nacimiento() %></p>
      <p class="card-text"><strong>Fecha de defunción:</strong>
        <%= autor.getFecha_defuncion() != null ? autor.getFecha_defuncion() : "Sigue vivo" %></p>
      <p class="card-text"><strong>Activo:</strong> <%= autor.isActivo() ? "Sí" : "No" %></p>
      <p class="card-text"><strong>Número de obras:</strong> <%= autor.getNumero_obras() %></p>
    </div>
  </div>
</div>

<%@ include file="includes/footer.jsp" %>