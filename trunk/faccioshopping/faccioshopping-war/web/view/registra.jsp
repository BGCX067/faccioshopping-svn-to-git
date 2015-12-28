<%-- 
    Document   : catalogo
    Created on : 2-giu-2011, 18.56.52
    Author     : Davide
--%>


<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Czech. --%>
<c:set var="view" value="/checkout" scope="session"/>


<script src="js/jquery.validate.js" type="text/javascript"></script>



<script type="text/javascript">

    $(document).ready(function(){
        $("#registerForm").validate({
            rules: {
                lastname: "required",
                firstname: "required",
                email: {
                    required: true,
                    email: true
                },
                password:"required",
                cpassword:
                    {
                    required:true,
                    equalTo: "#password"
                    }
            },
            messages: {
                        email: "*",
                        lastname:"*",
                        firstname:"*",
                        password:"*",
                        cpassword:"*"
            } 
        });
    });
</script>


<%-- HTML markup starts below --%>
<div id="register"><fmt:message key="insertData"/>&nbsp;<b><fmt:message key="title"/></b> <fmt:message key="and"/> <fmt:message key="register"/></div>
<div id="singleColumn">
    <fieldset>
    <legend><b><fmt:message key='register'/></b></legend>
    <form id="registerForm" action="<c:url value='registra'/>" method="post">
        <table id="checkoutTable" class="rounded">          
            <tr>
                <td><label for="lastname"><fmt:message key="customerLastname"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="name"
                           name="lastname"
                           value="${authentication.getLastname()}">
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
                           value="${authentication.getFirstname()}">
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
                           <c:if test="${authentication != null}">
                            readonly="readonly"
                           </c:if>
                           value="${authentication.getEmail()}">
                </td>
            </tr>
            <c:if test="${authentication == null}">
            <tr>
                <td><label for="password">password:</label></td>
                <td class="inputField">
                    <input type="password"
                           size="25"
                           maxlength="19"
                           id="password"
                           name="password"
                           value="${param.password}">
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
                           value="${param.cpassword}">
                </td>
            </tr>
            </c:if>
            <tr>
                <td colspan="2">
                    <input type="hidden" id="isInterno" name="isInterno" 
                           <c:choose>
                               <c:when test="${authentication != null}">
                                    value="0"
                               </c:when>
                               <c:otherwise>
                                    value="1"
                               </c:otherwise>
                           </c:choose> />
                    <input type="hidden" id="action" name="action" value="inserisci"/>
                    <input type="submit" class="rounded bubble hMargin" value="<fmt:message key='confirm'/>">                    
                </td>
            </tr>
        </table>
    </form>
    <br/>
    <c:if test="${authentication == null}">
        <div style="text-align: left;font-size:8pt;">
            <img src="img/lampadina.gif"><fmt:message key='loginOpenID'/>
            <b>&nbsp;<a href="login">login</a></b>
        </div>            
    </c:if>
    </fieldset>
</div>