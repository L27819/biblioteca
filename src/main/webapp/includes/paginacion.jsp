<%
    String ruta = (String) request.getAttribute("ruta");
    int paginaActual = (int) request.getAttribute("paginaActual");
    int totalPaginas = (int) request.getAttribute("totalPaginas");
%>

<% if (totalPaginas > 1) { %>
<nav aria-label="Paginación" class="mt-4">
    <ul class="pagination justify-content-center">

        <% if (paginaActual > 1) { %>
        <li class="page-item">
            <a class="page-link" href="<%= ruta %>?pagina=<%= paginaActual - 1 %>">Anterior</a>
        </li>
        <% } else { %>
        <li class="page-item disabled">
            <span class="page-link">Anterior</span>
        </li>
        <% } %>

        <% for (int i = 1; i <= totalPaginas; i++) { %>
        <li class="page-item <%= (i == paginaActual) ? "active" : "" %>">
            <a class="page-link" href="<%= ruta %>?pagina=<%= i %>"><%= i %>
            </a>
        </li>
        <% } %>

        <% if (paginaActual < totalPaginas) { %>
        <li class="page-item">
            <a class="page-link" href="<%= ruta %>?pagina=<%= paginaActual + 1 %>">Siguiente</a>
        </li>
        <% } else { %>
        <li class="page-item disabled">
            <span class="page-link">Siguiente</span>
        </li>
        <% } %>

    </ul>
</nav>
<% } %>