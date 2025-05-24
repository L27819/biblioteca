<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<%
    if (usuario == null || !"admin".equalsIgnoreCase(usuario.getTipo_usuario())) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<div class="container my-5">
    <h2 class="text-center mb-5">Panel de Administración 📚</h2>

    <div class="row g-4 justify-content-center">

        <div class="col-md-4">
            <div class="card shadow-sm text-center p-3">
                <h5>Gestión de Libros</h5>
                <p>Visualiza, edita o elimina los libros disponibles.</p>
                <a href="lista-libros.jsp" class="btn btn-primary">Ver Libros</a>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow-sm text-center p-3">
                <h5>Gestión de Usuarios</h5>
                <p>Revisa cuentas registradas y roles de usuario.</p>
                <a href="lista-usuarios.jsp" class="btn btn-primary">Ver Usuarios</a>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow-sm text-center p-3">
                <h5>Añadir nuevo libro</h5>
                <p>Registra un nuevo libro en el sistema.</p>
                <a href="añadir-libro.jsp" class="btn btn-primary">Nuevo Libro</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>