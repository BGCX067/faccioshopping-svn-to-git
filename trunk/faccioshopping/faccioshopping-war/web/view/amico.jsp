<%-- 
    Document   : regalo
    Created on : 19-set-2011, 13.15.05
    Author     : Davide
--%>
<c:choose>
    <c:when test="${regali != null && !regali.isEmpty()}">
        <div id="register">
            <fmt:message key="otherGiftsList"/>&nbsp;<b>${amico.nome}&nbsp;${amico.cognome}</b>
        </div> 
        <div id="singleColumn">
        <table id="productTable">

            <c:forEach var="regalo" items="${regali}" varStatus="iter">

                <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
                    <td style="width:50px;">
                        <img src="img/favorite.png"/>
                    </td>
                    <td>
                        <span style="font-size: 16pt; color:#006666;">${regalo.nome}</span>
                    </td>
                    
                    <td>
                        <a target="_blank" href="${regalo.urlArticolo}">${regalo.urlArticolo}</a>
                    </td>

                    <td>
                        ${regalo.descrizione}
                    </td> 
                    <td>    
                        <form id="regaloCarrello" name="regaloCarrello" action="addRegaloAmico" method="POST">    
                           <input type="hidden" id="nomeArticolo"
                           name="nomeArticolo"
                           value="${regalo.nome}"/>                            
                           <input type="image" src="img/add_item.png"
                            name="submit"
                            value="<fmt:message key='giftToCart'/>" title="<fmt:message key='giftToCart'/>"/>
                        </form>                                                         
                        <span class="smallText">
                            <fmt:message key="giftToCart"/>
                        </span>                            
                    </td>
                </tr>

            </c:forEach>

        </table>  
        </div>
    </c:when>
    <c:otherwise>
        <div id="register">
            <b style="font-size:11pt;">${amico.nome}&nbsp;${amico.cognome}</b>&nbsp;<fmt:message key="otherGiftsEmpty"/>
        </div>             
    </c:otherwise>    
</c:choose>
