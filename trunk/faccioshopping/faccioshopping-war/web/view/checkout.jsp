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
        $("#checkoutForm").validate({
            rules: {                
                address: {
                    required: true
                },
                creditcard: {
                    required: true,
                    creditcard: true
                },
                city: {required: true},
                county: {required: true},
                cap: {required: true}
            },
            messages: {
                        city:"*",
                        cap:"*",
                        county:"*",
                        address:"*",
                        creditcard:"*"
            } 
        });
    });
</script>


<%-- HTML markup starts below --%>
<div id="register"><fmt:message key="checkoutText"/></div>
<div id="singleColumn">

    <fieldset>
            <legend><b><fmt:message key='register'/></b></legend>

    <form id="checkoutForm" action="<c:url value='acquisto'/>" method="post">
        <table id="checkoutTable" class="rounded">
            <tr>
                <td><label for="lastname"><fmt:message key="customerLastname"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="lastname"
                           name="lastname"
                           readonly="readonly"
                           value="${utentefaccioshopping.cognome}">
                </td>
            </tr>
            <tr>
                <td><label for="firstname"><fmt:message key="customerFirstname"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="firstname"
                           name="firstname"
                           readonly="readonly"
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
                           readonly="readonly"
                           value="${utentefaccioshopping.email}">
                </td>
            </tr>          
            <tr>
                <td><label for="address"><fmt:message key="address"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="address"
                           name="address"
                           value="">
                    
                </td>
            </tr>
            <tr>
                <td><label for="city"><fmt:message key="city"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="city"
                           name="city"
                           value="">
                    
                </td>
            </tr>
            <tr>
                <td><label for="county"><fmt:message key="county"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="city"
                           name="county"
                           value="">
                    
                </td>
            </tr>
            <tr>
                <td><label for="cap">CAP:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="45"
                           id="cap"
                           name="cap"
                           value="">
                    
                </td>
            </tr>
            <tr>
                <td><label for="creditcard"><fmt:message key="creditCard"/>:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="25"
                           maxlength="19"
                           id="creditcard"
                           name="creditcard"
                           class="creditcard"
                           value="">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" class="rounded bubble hMargin" value="<fmt:message key='submit'/>">
                </td>
            </tr>
        </table>
    </form>
    </fieldset>
    <div id="infoBox">
        <table id="priceBox" class="rounded">
            <tr>
                <td><fmt:message key="subtotal"/>:</td>
                <td class="checkoutPriceColumn">
                    <fmt:formatNumber type="currency" currencySymbol="&euro; " value="${carrello.subtotale}"/></td>
            </tr>
            <tr>
                <td><fmt:message key="surcharge"/>:</td>
                <td class="checkoutPriceColumn">
                    <fmt:formatNumber type="currency" currencySymbol="&euro; " value="${initParam.deliverySurcharge}"/></td>
            </tr>
            <tr>
                <td class="total"><fmt:message key="total"/>:</td>
                <td class="total checkoutPriceColumn">
                    <fmt:formatNumber type="currency" currencySymbol="&euro; " value="${carrello.subtotale + initParam.deliverySurcharge}"/></td>
            </tr>
        </table>
    </div>
            
            
</div>