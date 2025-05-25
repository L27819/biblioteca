<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.biblioteca.model.Prestamo" %>
<%@ page import="java.util.Map" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
  List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");
%>

<div class="container my-5">
  <h2 class="text-center mb-4">Mis Préstamos 📚</h2>

  <% if (prestamos == null || prestamos.isEmpty()) { %>
  <p class="text-center text-muted">No has solicitado ningún préstamo todavía.</p>
  <% } else { %>
  <table class="table table-bordered table-hover text-center">
    <thead class="table-light">
    <tr>
      <th>Nº Préstamo</th>
      <th>Titulo</th>
      <th>Fecha Inicio</th>
      <th>Fecha Fin</th>
      <th>Estado</th>
    </tr>
    </thead>
    <tbody>
    <% for (Prestamo p : prestamos) { %>
    <tr>
      <td><%= p.getId_prestamo() %></td>
      <%
        Map<Integer, String> titulosLibros = (Map<Integer, String>) request.getAttribute("titulosLibros");
      %>
      <td><%= titulosLibros.get(p.getId_libro()) %></td>

      <td><%= p.getFecha_inicio() %></td>
      <td><%= p.getFecha_fin() != null ? p.getFecha_fin() : "-" %></td>
      <td><%= p.getEstado() %></td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <% } %>
</div>

<%@ include file="includes/footer.jsp" %>