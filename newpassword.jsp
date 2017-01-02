<%-- 
    Document   : home
    Created on : Sep 23, 2015, 7:55:55 PM
    Author     : MadhaviBhat
--%>

<jsp:include page="header.jsp"/>

<form action="UserController" method="post" >   
    
    
    <input type="hidden" name="action" value="updateNewPas"/>

    <input type="hidden" name="resetEmail" value="${resetEmail}"/>

    <table class="loginbox">
        <tr><td colspan="2"><p style="color:red;">${message}</p></td></tr>
        <tr>
            <td>
                <jsp:text> Temp password:*</jsp:text></td>
                <td>
                    <input type="password" name="tempPaswd" size="50" required tabindex="1"
                           value="${tmpPasword}" readonly/>
                </td></tr>
            <tr><td>&nbsp;</td><td></td></tr>

            <tr>
                <td>
                <jsp:text> New password:*</jsp:text></td>
                <td>
                    <input type="password" pattern=".{6,}" placeholder="New Password"
                           title="6 Characters minimum"
                           name="newPaswd" size="50" required tabindex="1"/>
                </td></tr>

        <tr><td>&nbsp;</td><td></td></tr>

            <tr>
                <td>
                <jsp:text> Confirm New password:*</jsp:text></td>
                <td>
                    <input type="password" pattern=".{6,}" placeholder="Confirm Password"
                           title="6 Characters minimum"
                           name="cnfNewPaswd" size="50" required tabindex="1"/>
                </td></tr>

            <tr><td></td><td>

                    <input type="submit" value="Reset Password" name="loginBtn" id="loginBTN" 
                           tabindex="3"/>

                </td></tr>
        </table>

    </form>

<jsp:include page="footer.jsp"/>
