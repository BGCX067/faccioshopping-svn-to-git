<%-- 
    Document   : index
    Created on : 2-giu-2011, 15.14.34
    Author     : Davide
--%>  

<div id="singleColumn">
    <c:forEach var="catalogo" items="${cataloghi}">
        <div class="catalogoBox tableHeading">
            <c:url value="getCatalogo" var="url">
                <c:param name="idCatalogo" value="${catalogo.id}"/>
            </c:url>
            <a href="${url}" style="font-size:21pt; letter-spacing: -0.05em;">                
                ${catalogo.nome}                
            </a>            
        </div>
    </c:forEach>
</div>


