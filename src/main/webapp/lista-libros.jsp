<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.biblioteca.model.Libro" %>
<%@ page import="com.svalero.biblioteca.model.Usuario" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

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
                    <h5 class="card-title">
                        <a href="detalle-libro?id=<%= libro.getId_libro() %>" class="text-decoration-none text-dark">
                            <%= libro.getTitulo() %>
                        </a>
                    </h5>
                    <p class="card-text">Género: <%= libro.getGenero() %></p>
                    <p class="card-text">Editorial: <%= libro.getEditorial() %></p>
                    <p class="card-text">Precio: <%= libro.getPrecio() %> €</p>
                    <p class="card-text"><small class="text-muted"><%= libro.getPaginas() %> páginas</small></p>

                    <div class="d-flex flex-wrap gap-2 mt-3">
                        <% if (usuario != null && "admin".equalsIgnoreCase(usuario.getTipo_usuario())) { %>
                        <a href="editar-libro.jsp?id=<%= libro.getId_libro() %>" class="btn btn-warning btn-sm">Editar</a>
                        <a href="EliminarLibroServlet?id=<%= libro.getId_libro() %>" class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Seguro que deseas eliminar este libro?');">Eliminar</a>
                        <% } else if (usuario != null && "user".equalsIgnoreCase(usuario.getTipo_usuario())) { %>
                        <form action="AddToUserServlet" method="post" class="d-inline">
                            <input type="hidden" name="idLibro" value="<%= libro.getId_libro() %>">
                            <button type="submit" class="btn btn-primary btn-sm">Solicitar</button>
                        </form>
                        <% } %>
                    </div>
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