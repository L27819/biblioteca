<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.svalero.biblioteca.model.Libro" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
    Libro libro = (Libro) request.getAttribute("libro");
%>

<div class="container my-5">
    <h2 class="mb-4 text-center">Editar Libro</h2>

    <form action="EditarLibroServlet" method="post" class="row g-3">
        <input type="hidden" name="id_libro" value="<%= libro.getId_libro() %>">

        <div class="col-12">
            <label class="form-label">Título</label>
            <input type="text" name="titulo" class="form-control" value="<%= libro.getTitulo() %>" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Editorial</label>
            <input type="text" name="editorial" class="form-control" value="<%= libro.getEditorial() %>">
        </div>

        <div class="col-md-6">
            <label class="form-label">Género</label>
            <input type="text" name="genero" class="form-control" value="<%= libro.getGenero() %>">
        </div>

        <div class="col-md-6">
            <label class="form-label">Páginas</label>
            <input type="number" name="paginas" class="form-control" value="<%= libro.getPaginas() %>">
        </div>

        <div class="col-md-6">
            <label class="form-label">Precio</label>
            <input type="number" step="0.01" name="precio" class="form-control" value="<%= libro.getPrecio() %>">
        </div>

        <div class="col-12">
            <label class="form-label">Descripción</label>
            <textarea name="descripcion" class="form-control"><%= libro.getDescripcion() %></textarea>
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary w-100"
                    onclick="return confirm('¿Seguro que deseas editar este libro?');">Guardar cambios</button>
        </div>
    </form>
</div>

<%@ include file="includes/footer.jsp" %>