<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<div class="container d-flex justify-content-center my-5">
        <form action="LoginServlet" method="post">
            <h1 class=" h3 mb-3 fw-normal text-center">Inicia sesión:</h1>

            <div class="mb-3">
              <label for="nombre_usuario" class="form-label">Usuario</label>
              <input type="text" name="nombre_usuario" class="form-control" required>
            </div>

            <div class="mb-3">
              <label for="contrasena" class="form-label">Contraseña</label>
              <input type="password" name="contrasena" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Iniciar sesión</button>

            <div class="mt-3 text-center">
                <p>¿No tienes cuenta? <a href="registro-usuario.jsp">Regístrate aquí</a></p>
            </div>

        </form>
    </div>

<%@ include file="includes/footer.jsp" %>