<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-header text-center bg-light">
                    <h4 class="mb-0">Registro de Usuario</h4>
                </div>
                <div class="card-body">
                    <form action="RegisterServlet" method="post" class="row g-3">

                        <div class="col-12">
                            <label for="inputEmail" class="form-label">Correo electrónico</label>
                            <input type="email" name="email" class="form-control" id="inputEmail" required>
                        </div>

                        <div class="col-md-6">
                            <label for="inputNombre" class="form-label">Nombre</label>
                            <input type="text" name="nombre" class="form-control" id="inputNombre" required>
                        </div>

                        <div class="col-md-6">
                            <label for="inputApellidos" class="form-label">Apellidos</label>
                            <input type="text" name="apellidos" class="form-control" id="inputApellidos" required>
                        </div>

                        <div class="col-md-6">
                            <label for="inputUsername" class="form-label">Nombre de usuario</label>
                            <input type="text" name="nombre_usuario" class="form-control" id="inputUsername" required>
                        </div>

                        <div class="col-md-6">
                            <label for="inputPassword" class="form-label">Contraseña</label>
                            <input type="password" name="contrasena" class="form-control" id="inputPassword" required>
                        </div>

                        <div class="col-12">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="termsCheck" required>
                                <label class="form-check-label" for="termsCheck">
                                    Acepto los términos y condiciones
                                </label>
                            </div>
                        </div>

                        <div class="col-12">
                            <button type="submit" class="btn btn-primary w-100">Registrarse</button>
                        </div>

                        <% if (request.getAttribute("errorMessage") != null) { %>
                        <div class="alert alert-danger mt-2 text-center">
                            <%= request.getAttribute("errorMessage") %>
                        </div>
                        <% } %>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>