<%-- 
    Document   : home
    Created on : Sep 23, 2015, 7:55:55 PM
    Author     : MadhaviBhat
--%>

<jsp:include page="header.jsp"/>

<form action="UserController" method="post" >   
    <input type="hidden" name="action" value="login"/>

    <table class="loginbox">
        <tr><td colspan="2"><p style="color:red;">${message}</p></td></tr>
        <tr>
            <td>
                <jsp:text> Email Address:*</jsp:text></td>
                <td>
                    <input type="text" name="loginEmail" placeholder="enter email" 
                           size="50" required tabindex="1"/>
                </td></tr>
            <tr><td>&nbsp;</td><td></td></tr>

            <tr><td>
                <jsp:text> Password:*</jsp:text></td><td>
                    <input type="password" name="loginPaswd" 
                           placeholder="password" size="50" required tabindex="2"/>
                </td>
            </tr>
            <tr><td>&nbsp;</td><td></td></tr>

            <tr><td></td><td>

                    <input type="submit" value="Log in" name="loginBtn" id="loginBTN" 
                           tabindex="3"/>

                </td></tr>
            <tr><td>&nbsp;</td><td></td></tr>
            <tr><td><a href="forgot.jsp">Forgot password? </td>
                <td></td>
            </tr>
             <tr><td>&nbsp;</td><td></td></tr>   
                <tr>
                <td>
                    <a href="signup.jsp">Sign up for a new account 
                    </a></td>
                     <td></td>
            </tr>
        </table>

    </form>

<jsp:include page="footer.jsp"/>
