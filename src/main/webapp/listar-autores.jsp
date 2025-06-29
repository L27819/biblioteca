<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.svalero.biblioteca.model.Autor" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
    List<Autor> listaAutores = (List<Autor>) request.getAttribute("autores");
%>

<div class="container my-5 img-fluid">
    <h2 class="text-center mb-4">Listado de Autores ✍️</h2>

    <form action="listar-autores" method="get" class="row mb-4">
        <div class="col-md-10">
            <input type="text" name="busqueda" class="form-control" placeholder="Buscar autor por nombre o nacionalidad">
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-success w-100">Buscar</button>
        </div>
    </form>

    <div class="row">
        <% if (listaAutores != null && !listaAutores.isEmpty()) {
            for (Autor autor : listaAutores) {
        %>
        <div class="col-md-4 mb-4">
            <div class="card h-100 shadow-sm rounded-4">
                <img src="../biblio_images/<%= autor.getImagen() %>" class="card-img-top" alt="Imagen del autor" style="height: 300px; object-fit: cover;">
                <div class="card-body">
                    <h5 class="card-title fw-bold">
                        <a href="detalle-autor?id=<%= autor.getId_autor() %>" class="text-decoration-none text-dark">
                            <%= autor.getNombre() %> <%= autor.getApellidos() %>
                        </a>
                    </h5>
                    <p class="card-text">
                        <strong>Nacionalidad:</strong> <%= autor.getNacionalidad() %><br>
                        <strong>Vivo:</strong> <%= autor.isActivo() ? "Sí" : "No" %><br>
                        <strong>Obras:</strong> <%= autor.getNumero_obras() %>
                    </p>

                    <% if (usuario != null && "admin".equalsIgnoreCase(usuario.getTipo_usuario())) { %>

                    <div class="d-flex gap-2 mt-3">
                        <a href="EditarAutorServlet?id=<%= autor.getId_autor() %>" class="btn btn-warning btn-sm">Editar</a>
                        <a href="EliminarAutorServlet?id=<%= autor.getId_autor() %>" class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Seguro que deseas eliminar este autor?');">Eliminar</a>
                    </div>

                    <% } %>

                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="col-12">
            <p class="text-center text-danger">No hay autores registrados aún.</p>
        </div>
        <% } %>
    </div>
    <%@ include file="includes/paginacion.jsp" %>
</div>

<%@ include file="includes/footer.jsp" %>