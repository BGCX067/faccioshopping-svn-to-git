<%-- 
    Document   : login
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
    <fmt:message key='registerMessage' /><a href="registra"><b><fmt:message key='clickHere' /></b></a>!
</div>

<div id="form_login">
     <form id="loginForm" action="login" method="POST">
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
                    <td colspan="2">
                        <input type="hidden" id="action" name="action" value="entra" />
                        <input class="rounded bubble hMargin" type="submit" value="<fmt:message key='enter' />"></input>
                    </td>        
                </tr>
            </table>
        </fieldset>
    </form>
</div> 

<div id="form_openID">
        <fieldset>
            <legend><b><fmt:message key="or"/></b></legend>                  
              <div id="gBtn" style="float:left;"><a class="rounded bubble hMargin" style="position: relative; top:4px;" href="openid?op=Google">Google</a></div> 
              <div id="yBtn" style="float:left;"><a class="rounded bubble hMargin" style="position: relative; top:4px;" href="openid?op=Yahoo">Yahoo</a></div>                          
              <div style="clear:both;"></div>
              <br/>
              <div style="text-align: left;font-size:8pt;"><img src="img/lampadina.gif"><fmt:message key='enterTooltip'/></div>   
              
        </fieldset> 
</div>
