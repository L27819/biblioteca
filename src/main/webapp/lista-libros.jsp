<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.biblioteca.model.Libro" %>
<%@ include file="includes/header.jsp" %>

<div class="container my-5">
    <h2 class="mb-4 text-center">Listado de Libros 📚</h2>

    <div class="row">
        <%
            List<Libro> listaLibros = (List<Libro>) request.getAttribute("libros");
            if (listaLibros != null && !listaLibros.isEmpty()) {
                for (Libro libro : listaLibros) {
        %>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <img src="<%= libro.getImagen() %>" class="card-img-top" alt="Imagen del libro">
                <div class="card-body">
                    <h5 class="card-title"><%= libro.getTitulo() %></h5>
                    <p class="card-text">Género: <%= libro.getGenero() %></p>
                    <p class="card-text">Editorial: <%= libro.getEditorial() %></p>
                    <p class="card-text">Precio: <%= libro.getPrecio() %> €</p>
                    <p class="card-text"><small class="text-muted"><%= libro.getPaginas() %> páginas</small></p>
                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p class="text-center text-danger">No hay libros disponibles.</p>
        <%
            }
        %>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>