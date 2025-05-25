<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.model.Libro" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
    Libro libro = (Libro) request.getAttribute("libro");
%>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow">
                <img src="<%= libro.getImagen() %>" class="card-img-top" alt="Portada del libro">
                <div class="card-body">
                    <h3 class="card-title"><%= libro.getTitulo() %></h3>
                    <p class="card-text"><strong>Género:</strong> <%= libro.getGenero() %></p>
                    <p class="card-text"><strong>Editorial:</strong> <%= libro.getEditorial() %></p>
                    <p class="card-text"><strong>Fecha de publicación:</strong> <%= libro.getFecha_publicacion() %></p>
                    <p class="card-text"><strong>Páginas:</strong> <%= libro.getPaginas() %></p>
                    <p class="card-text"><strong>Precio:</strong> <%= libro.getPrecio() %> €</p>
                    <p class="card-text"><strong>Disponible:</strong> <%= libro.isDisponible() ? "Sí" : "No" %></p>
                    <p class="card-text"><strong>Descripción:</strong> <%= libro.getDescripcion() %></p>
                </div>

                <% if (usuario != null && "user".equalsIgnoreCase(usuario.getTipo_usuario())) { %>

                <form action="PrestarLibroServlet" method="post">
                    <input type="hidden" name="id_libro" value="<%= libro.getId_libro() %>">
                    <button type="submit" class="btn btn-success mt-3 w-100">Solicitar préstamo 📖</button>
                </form>
                <% } %>
            </div>
        </div>
    </div>
</div>


<%@ include file="includes/footer.jsp" %>