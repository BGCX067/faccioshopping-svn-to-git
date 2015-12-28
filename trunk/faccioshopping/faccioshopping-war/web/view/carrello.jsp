<%-- 
    Document   : carrello
    Created on : 2-giu-2011, 16.07.44
    Author     : Davide
--%>
<%-- HTML markup starts below --%>

<div id="singleColumn">

    <c:choose>
        <c:when test="${carrello.numeroDiElementi > 1}">
            <p class="smallText"><fmt:message key="yourCartContains"/> ${carrello.numeroDiElementi} <fmt:message key="items"/>.</p>
        </c:when>
        <c:when test="${carrello.numeroDiElementi == 1}">
            <p class="smallText"><fmt:message key="yourCartContains"/> ${carrello.numeroDiElementi} <fmt:message key="item"/>.</p>
        </c:when>
        <c:otherwise>
            <p class="smallText"><fmt:message key="yourCartEmpty"/></p>
        </c:otherwise>
    </c:choose>

    <div id="actionBar">
        <%-- clear carrello widget --%>
        <c:if test="${!empty carrello && carrello.numeroDiElementi != 0}">

            <c:url var="url" value="getCarrello">
                <c:param name="svuota" value="true"/>
            </c:url>

            <a href="${url}" class="rounded bubble hMargin"><fmt:message key="clearCart"/></a>
        </c:if>

        <%-- continue shopping widget --%>
        <c:set var="value">
            <c:choose>
                <%-- if 'selectedCategory' session object exists, send user to previously viewed category --%>
                <c:when test="${!empty idCatalogo && !empty idCategoria}">
                    <c:choose>
                        <c:when test="${!empty idCategoria}">
                            getCategoria?idCatalogo=${idCatalogo}&idCategoria=${idCategoria}
                        </c:when>
                        <c:otherwise>
                            getCatalogo?idCatalogo=${idCatalogo}
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <%-- otherwise send user to welcome page --%>
                <c:otherwise>
                    home
                </c:otherwise>
            </c:choose>
        </c:set>

        <c:url var="url" value="${value}"/>
        <a href="${url}" class="rounded bubble hMargin"><fmt:message key="continueShopping"/></a>        
    </div>

    <c:if test="${!empty carrello && carrello.numeroDiElementi != 0}">      

      <table id="carrelloTable">

        <tr class="header">
            <th><fmt:message key="product"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="price"/></th>
            <th><fmt:message key="quantity"/></th>
            <th><fmt:message key="delete"/></th>
        </tr>

        <c:forEach var="elementoCarrello" items="${carrello.elementiCarrello}" varStatus="iter">

          <c:set var="articolo" value="${elementoCarrello.articolo}"/>

          <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
            <td>
                <img src="img/articolo/${articolo.immagineURI}"
                     alt="${articolo.nome}" height="100">
            </td>

            <td>${articolo.nome}</td>                       
            
            
            <td>
                <fmt:formatNumber type="currency" currencySymbol="&euro; " value="${elementoCarrello.totale}"/>
                <br>
                <span class="smallText">(
                    <fmt:formatNumber type="currency" currencySymbol="&euro; " value="${articolo.prezzo}"/>
                    / <fmt:message key="unit"/> )</span>
            </td>

            <td>
                <form action="<c:url value='updateCarrello'/>" method="post">
                    <input type="hidden"
                           name="idArticolo"
                           value="${articolo.id}"/>
                    <input type="text"
                           maxlength="2"
                           size="2"
                           value="${elementoCarrello.quantita}"
                           name="quantita"
                           style="margin:5px"/>
                    <input type="image"
                           name="submit"
                           src="img/accept_item.png"
                           value="<fmt:message key='update'/>"
                           title="<fmt:message key='update'/>&nbsp;<fmt:message key='quantity'/>"/>                                       
                </form>                          
            </td>
            <c:url value="deleteCarrello" var="url">
                <c:param name="idArticolo" value="${articolo.id}"/> 
            </c:url>  
            <td>
                <a href="${url}"><img title="cancella elemento" src="img/trash.png" /></a>
            </td>
               
          </tr>

        </c:forEach>                                      
      </table>
        <div style="text-align: right;">
            <h4><fmt:message key="subtotal"/>:
                <fmt:formatNumber type="currency" currencySymbol="&euro; " value="${carrello.subtotale}"/>
            </h4>
        </div>
            
      <div id="googleCheckout">
          
          <a href="javascript:checkoutPopUp('googleCheckout','height=800,width=500,scrollbars=yes,toolbar=no');" class="rounded bubble hMargin">
              Google Checkout
          </a> 
          <c:choose>
            <c:when test="${!empty carrello && carrello.numeroDiElementi != 0 && utentefaccioshopping != null}">
                <a href="<c:url value='checkout'/>" class="rounded bubble hMargin"><fmt:message key="proceedCheckout"/></a>
            </c:when>            
          </c:choose>
      </div>  
      <div class="clear"></div>
    </c:if>
</div>