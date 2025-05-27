<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.model.Usuario" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
  if (usuario == null || !"user".equalsIgnoreCase(usuario.getTipo_usuario())) {
    response.sendRedirect("index.jsp");
    return;
  }
%>

<div class="container my-5">
  <h2 class="text-center mb-5">Zona privada de <%= usuario.getNombre_usuario() %></h2>

  <div class="row g-4 justify-content-center">

    <div class="col-md-4">
      <div class="card shadow-sm text-center p-3">
        <h5>Mis prestamos</h5>
        <p>Consulta tus libros prestados.</p>
        <a href="zona-usuario" class="btn btn-primary">Ver prestamos</a>
      </div>
    </div>

    <div class="col-md-4">
      <div class="card shadow-sm text-center p-3">
        <h5>Mis datos</h5>
        <p>Consulta y modifica tus datos personales. </p>
        <a href="mis-datos.jsp" class="btn btn-primary">Ver Datos</a>
      </div>
    </div>
  </div>
</div>

<%@ include file="includes/footer.jsp" %>