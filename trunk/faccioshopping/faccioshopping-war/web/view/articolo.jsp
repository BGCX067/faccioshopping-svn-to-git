<%-- 
    Document   : articolo
    Created on : 4-giu-2011, 16.56.04
    Author     : Davide
--%>
<script type="text/javascript">


        jQuery(document).ready(function() {
            jQuery('#mcarousel').jcarousel();
        });


    
        $(document).ready(function(){
            $("#carrelloform").validate({
            rules: {
                        caratteristica: {
                                required: true
                        }		
            },
            messages: {
                        caratteristica: "*"
            }    
            });                          
        });
        
        
        function changeFormAction()
        {
                $("#carrelloform").attr("action","addRegalo");
                $("#carrelloform").submit();
        }
        
</script>
<div id="categoryLeftColumn">
        <!--Elenco Categorie-->
        <jsp:include page="../includes/menucategorie.jsp"></jsp:include>
</div>
<div id="categoryRightColumn">
        <!--Breadcrumb-->
        <jsp:include page="../includes/percorso.jsp" />

    <div>
        <div style="margin-bottom: 15px;">
            <img title="${articoloSelezionato.nome}" src="img/articolo/${articoloSelezionato.immagineURI}" height="140"/>
        </div>
        <c:if test="${utentefaccioshopping != null}">
            <div style="font-size:8pt;padding:10px;">
                <img src="img/lampadina.gif"/> <fmt:message key='faccioregaliGift'/> <b style="font-size: 8pt;"><a href="#" onclick="changeFormAction();" id="addRegalo" name="addRegalo"><fmt:message key="clickHere"></fmt:message></a></b>
            </div>
        </c:if>
        <div style="padding:2px 0px;margin-right: 25px;">
            <form action="<c:url value='addCarrello'/>" id="carrelloform" name="carrelloform" method="post">
            <table cellpadding="8" width="100%">
                <tr class="tableHeading">
                    <td colspan="2">
                        <b>${articoloSelezionato.nome}</b>
                        <input type="hidden" id="nomeArticolo" name="nomeArticolo" value="${articoloSelezionato.nome}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key='price' />:
                    </td>
                    <td>
                        <b>&euro; ${articoloSelezionato.prezzo}</b>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key='description' />:
                    </td>
                    <td>
                        <b class="smallText">${articoloSelezionato.descrizione}</b>
                        <input type="hidden" id="descrizione" name="descrizione" value="${articoloSelezionato.descrizione}"/>
                    </td>
                </tr>                  
                <tr class="tableHeading">
                    <td colspan="2">                                                                         
                                    <input type="hidden"
                                   name="idArticolo"
                                   value="${articoloSelezionato.id}"/> 
                                   <input type="image" src="img/add_item.png" height="19"
                                    name="submit"                                                                         
                                    value="<fmt:message key='addToCart'/>" title="<fmt:message key='addToCart'/>"/>                                 
                        <span class="smallText">
                            <fmt:message key="addToCart"/>
                        </span>                            
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
    <div style="font-size: 10pt;font-weight: bold;margin-top: 15px;"><fmt:message key="relatedArticle"/>:</div>                        
    <div id="relatedArticle"> 
        <ul id="mcarousel" class="jcarousel-skin-tango">    
    <c:forEach var="articoloCategoria" items="${categoriaSelezionata.articoloList}"> 
        <c:url var="url2" value='getArticolo'>
            <c:param name="idCatalogo" value="${catalogoSelezionato.id}"/>
            <c:param name="idCategoria" value="${categoriaSelezionata.id}"/>
            <c:param name="idArticolo" value="${articoloCategoria.id}"/>
        </c:url>
        <c:if test="${articoloCategoria.id != articoloSelezionato.id}">
            <li>
                <a href="${url2}" class="rounded" id="selectedArticolo">
                    <img title="${articoloCategoria.nome}" src="img/articolo/${articoloCategoria.immagineURI}" height="60" />
                </a> 
            </li>        
        </c:if>    
    </c:forEach>
        </ul>    
    </div>                       
</div>
                                                
     
