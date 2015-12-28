<%-- 
    Document   : catalogo
    Created on : 2-giu-2011, 18.56.52
    Author     : Davide
--%>

<div id="categoryLeftColumn">     
    <!--Elenco Categorie-->
    <jsp:include page="../includes/menucategorie.jsp"></jsp:include>   
</div>        
<div id="categoryRightColumn">
    <!--Breadcrumb-->
    <jsp:include page="../includes/percorso.jsp" />

    <table id="productTable">

        <c:forEach var="articolo" items="${categoriaSelezionata.articoloList}" varStatus="iter">
            
            <c:url var="url" value='getArticolo'>
                <c:param name="idCategoria" value="${categoriaSelezionata.id}"/>
                <c:param name="idCatalogo" value="${catalogoSelezionato.id}"/>
                <c:param name="idArticolo" value="${articolo.id}"/>
            </c:url>
            <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
                <td>
                    <a href="${url}"><img title="${articolo.nome}" src="img/articolo/${articolo.immagineURI}"
                         alt="${articolo.nome}" height="80"></a>
                </td>
                <td>
                    ${articolo.nome}           
                </td>               
                <td>
                    <fmt:formatNumber type="currency" currencySymbol="&euro; " value="${articolo.prezzo}"/>
                </td>
                <td>                    
                    <a href="${url}"><img title="dettaglio articolo" height="19" src="img/info.png"/></a>
                </td>
            </tr>

        </c:forEach>

    </table>
</div>