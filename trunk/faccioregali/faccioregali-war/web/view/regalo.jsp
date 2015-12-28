<%-- 
    Document   : regalo
    Created on : 19-set-2011, 13.15.05
    Author     : Davide
--%>


<script type="text/javascript">

    $(document).ready(function(){
        $("#addGiftForm").validate({
            rules: {
                nomeregalo: "required",
                caratteristica:"required",
                descrizione: "required",
                url:"required"
            },
            messages: {
                        nomeregalo: "*",                        
                        caratteristica:"*",
                        descrizione:"*",
                        url:"*"
            } 
        });
       
    });
</script>

<c:choose>
    <c:when test="${regali != null && !regali.isEmpty()}">
        <div id="register">
            <fmt:message key="giftsList"/>
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
                </tr>

            </c:forEach>

        </table>  
        </div>
    </c:when>
    <c:otherwise>
        <div id="register">
            <fmt:message key="giftsEmpty"/>
        </div>        
    </c:otherwise>    
</c:choose>

<!--Aggiungi Regalo--> 
<div>
        <fieldset>
            <legend><b><fmt:message key='addGift'/></b></legend>
        <form id="addGiftForm" name="addGiftForm" method="POST" action="regalo?action=addRegalo">
            <table>
                <tr>
                    <td>
                        <fmt:message key="name"/>: 
                    </td>
                    <td>
                        <input type="text" value="" id="nomeregalo" name="nomeregalo"/>
                    </td>                    
                </tr>                
                <tr>
                    <td>
                        <fmt:message key="description"/>:
                    </td>
                    <td>
                        <input type="text" value="" id="descrizione" name="descrizione"/>
                        <div style="text-align: left;font-size:8pt;"><img src="img/lampadina.gif"><fmt:message key='descTooltip'/></div>
                    </td>
                </tr>
                <tr>
                    <td>
                        link URL:
                    </td>
                    <td>
                        <input type="text" value="" id="url" name="url"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" id="addGrift" name="addGift" value="Aggiungi" class="bubble rounded hMargin"></input>
                    </td>
                </tr>
            </table>            
        </form>
        </fieldset>
</div>