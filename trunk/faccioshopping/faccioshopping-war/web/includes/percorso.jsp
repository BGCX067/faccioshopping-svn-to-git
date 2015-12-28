<%-- 
    Document   : percorso
    Created on : 11-giu-2011, 16.51.43
    Author     : Davide
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="categoryTitle">
    catalogo: <b>${catalogoSelezionato.nome}</b> -> categoria: <b>${categoriaSelezionata.nome}</b>
            <% if (request.getParameter("idArticolo") != null && request.getParameter("idArticolo") != "") {%>
             -> articolo:
                <b>
                    ${articoloSelezionato.nome}
                </b>
            <%}%>    
</div>
