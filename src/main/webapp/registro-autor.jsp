<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<div class="container my-5">
    <h2 class="text-center mb-4">Registrar Nuevo Autor ✍️</h2>

    <form action="RegistroAutorServlet" method="post" class="row g-3">

        <div class="col-md-6">
            <label class="form-label">Nombre</label>
            <input type="text" name="nombre" class="form-control" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Apellidos</label>
            <input type="text" name="apellidos" class="form-control">
        </div>

        <div class="col-md-6">
            <label class="form-label">Nacionalidad</label>
            <input type="text" name="nacionalidad" class="form-control">
        </div>

        <div class="col-md-6">
            <label class="form-label">Fecha de nacimiento</label>
            <input type="date" name="fecha_nacimiento" class="form-control">
        </div>

        <div class="col-md-6">
            <label class="form-label">Fecha de defunción (opcional)</label>
            <input type="date" name="fecha_defuncion" class="form-control">
        </div>

        <div class="col-md-6">
            <label class="form-label">Número de obras</label>
            <input type="number" name="numero_obras" class="form-control">
        </div>

        <div class="col-md-12">
            <label class="form-label">Imagen (URL)</label>
            <input type="text" name="imagen" class="form-control">
        </div>

        <div class="col-12 text-center">
            <button type="submit" class="btn btn-primary px-5">Registrar Autor</button>
        </div>
    </form>
</div>

<%@ include file="includes/footer.jsp" %>