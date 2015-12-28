<%-- 
    Document   : index
    Created on : 3-ago-2011, 13.27.06
    Author     : Davide
--%>

<script type="text/javascript">

    $(document).ready(function(){
        
        $("#searchForm").validate({
            rules: {
                pRicerca: {
                    required: true
                }            
            },
            messages: {
                        pRicerca: "*"
            } 
        });
    });
</script>

<div id="register">
    <fmt:message key='welcomeMessage' />!
</div>

<div id="singleColumn">
             
<div style="font-size:10pt;">
    <form id="searchForm" action="getUtenti" method="POST">
    <fieldset>
    <legend><b>cerca</b></legend>
        <fmt:message key='searchUser' />
        <br/>
        <br/>
        <div style="display:inline;">
            <img src="img/search_magnifier.png" />
            <input type="textbox" id="pRicerca" name="pRicerca" />
        </div> 
        <input class="rounded bubble hMargin" type="submit" value="<fmt:message key="search"/>"/>
        <br/>
        <br/>
        <br/>
        <div style="font-size:8pt;"><img src="img/lampadina.gif"><fmt:message key='searchTooltip'/></div>
    </fieldset>
    </form>
</div>

<div class="clear"></div>
</div>
<c:if test="${utenti != null}">
<div id="register">     
<c:choose>
    <c:when test="${!utenti.isEmpty()}">
        <fmt:message key="resultSearch"></fmt:message>
    </c:when>
    <c:otherwise>                    
            <fmt:message key="noresultSearch"></fmt:message>
    </c:otherwise>        
</c:choose>
</div>
<div id="singleColumn">
        <c:if test="${utenti != null && !utenti.isEmpty()}">            
            <c:forEach var="utente" items="${utenti}">
                <div class="utenteBox tableHeading">
                    <c:url value="getUtente" var="url">
                        <c:param name="id" value="${utente.id}"/>
                    </c:url>
                    <a href="${url}" style="font-size:17pt; letter-spacing: -0.05em;">                
                        ${utente.nome}&nbsp;${utente.cognome}          
                    </a>
                </div>
            </c:forEach>
        </c:if>                       
</div>
</c:if>