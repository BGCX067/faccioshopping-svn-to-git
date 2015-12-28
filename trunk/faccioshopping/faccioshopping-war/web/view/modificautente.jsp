<%-- 
    Document   : modificautente
    Created on : 12-ott-2011, 19.58.33
    Author     : Davide
--%>

<script src="js/jquery.validate.js" type="text/javascript"></script>



<script type="text/javascript">

    $(document).ready(function(){
        $("#modifyForm").validate({
            rules: {
                lastname: "required",
                firstname: "required",
                email: {
                    required: true,
                    email: true
                },
                city: "required",
                opassword: "required",
                npassword:"required",
                cpassword:
                    {
                    required:true,
                    equalTo: "#npassword"
                    }
            },
            messages: {
                        email: "*",
                        lastname:"*",
                        firstname:"*",
                        opassword:"*",
                        npassword:"*",
                        cpassword:"*"
            } 
        });
    });
</script>


<%-- HTML markup starts below --%>
<div id="register"><fmt:message key="modifydata"/>&nbsp;<b><fmt:message key="title"/></b></div>
<div id="singleColumn">
    <fieldset>
    <legend><b><fmt:message key='modify'/></b></legend>
    <form id="modifyForm" action="<c:url value='modifica'/>" method="post">
        <table id="checkoutTable" class="rounded">          
            <tr>
                <td><label for="lastname"><fmt:message key="customerLastname"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="name"
                           name="lastname"
                           value="${utentefaccioshopping.cognome}">
                </td>
            </tr>
            <tr>
                <td><label for="firstname"><fmt:message key="customerFirstname"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="surname"
                           name="firstname"
                           value="${utentefaccioshopping.nome}">
                </td>
            </tr>
            <tr>
                <td><label for="email"><fmt:message key="email"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="email"
                           name="email"
                           <c:if test="${!utentefaccioshopping.isInterno}">
                            readonly="readonly"
                           </c:if>
                           value="${utentefaccioshopping.email}">
                </td>
            </tr>
            <c:if test="${utentefaccioshopping.isInterno}">
            <tr>
                <td><label for="opassword"><fmt:message key="old" /> password:</label></td>
                <td class="inputField">
                    <input type="password"
                           size="25"
                           maxlength="19"
                           id="opassword"
                           name="opassword"
                           value="">
                </td>
            </tr>    
            <tr>
                <td><label for="password"><fmt:message key="new" /> password:</label></td>
                <td class="inputField">
                    <input type="password"
                           size="25"
                           maxlength="19"
                           id="npassword"
                           name="npassword"
                           value="">
                </td>
            </tr>
            <tr>
                <td><label for="cpassword"><fmt:message key="confirm" /> password:</label></td>
                <td class="inputField">
                    <input type="password"
                           size="25"
                           maxlength="19"
                           id="cpassword"
                           name="cpassword"
                           value="">
                </td>
            </tr>
            </c:if>
            <tr>
                <td colspan="2">                   
                    <input type="hidden" id="action" name="action" value="aggiorna"/>
                    <input type="submit" class="rounded bubble hMargin" value="<fmt:message key='confirm'/>">                    
                </td>
            </tr>
        </table>
    </form>           
    </fieldset>
</div>
