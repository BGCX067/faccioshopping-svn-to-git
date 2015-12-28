<%-- 
    Document   : menuCategorie
    Created on : 23-ott-2011, 13.45.06
    Author     : Davide
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="entity.Catalogo" %>
<%@page import="entity.Categoria" %>
<%
 Catalogo catalogoSelezionato = (Catalogo) request.getAttribute("catalogoSelezionato");
 Categoria categoriaSelezionata = (Categoria) request.getAttribute("categoriaSelezionata");
%>

    <span class="rounded categoryButton" style="font-size:11pt; background-color:#FFF;border-bottom: solid 3px #FE9;">
        <b>${catalogoSelezionato.nome}</b>
    </span>     
        
    <% for (Categoria categoriaCatalogo : catalogoSelezionato.getCategoriaList()) {%> 
            <% if (categoriaCatalogo.getId() == categoriaSelezionata.getId()) {%>
                <a href="" class="rounded categoryButton" id="selectedCategory">
                    <span class="categoryText">
                        <%= categoriaCatalogo.getNome() %>
                    </span>
                </a>
            <%} else {%>               
                <a href="getCategoria?idCatalogo=${catalogoSelezionato.id}&idCategoria=<%=categoriaCatalogo.getId()%>" class="rounded categoryButton">
                    <span class="categoryText">
                        <%= categoriaCatalogo.getNome() %>
                    </span>
                </a>
            <%}%>    
     <%}%>