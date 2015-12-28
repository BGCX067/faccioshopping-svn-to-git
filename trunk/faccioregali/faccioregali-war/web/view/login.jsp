<%-- 
    Document   : index
    Created on : 3-ago-2011, 13.27.06
    Author     : Davide
--%>

<script type="text/javascript">

    $(document).ready(function(){
        $("#loginForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },
                password:"required"                
            },
            messages: {
                        email: "*",                        
                        password:"*"
            } 
        });
       
    });
</script>

<div id="register">
    <fmt:message key='registerMessage' /><a href="login?action=registra"><b><fmt:message key='clickHere' /></b></a>!
</div>
<div id="singleColumn">
<div id="form_login">
     <form id="loginForm" action="login?action=entra" method="POST">
        <fieldset>
            <legend><b>login</b></legend>
            <table>
                <tr>
                    <td>email:</td>
                    <td><input type="text" id="email" name="email" /></td>
                </tr>
                <tr>
                    <td>password:</td>
                    <td><input type="password" id="password" name="password" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input class="rounded bubble hMargin" type="submit" value="<fmt:message key='enter' />"></input></td>        
                </tr>
            </table>
        </fieldset>
    </form>
</div> 
                

<div class="clear"></div>
</div>

