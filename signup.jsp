<%-- 
    Document   : home
    Created on : Sep 23, 2015, 7:55:55 PM
    Author     : MadhaviBhat
--%>

<jsp:include page="header.jsp"/>

<br>
<form action="UserController" method="post">
<table class="loginbox">
    <tr><td colspan="2"><p style="color:red;">${message}</p></td></tr>
    <tr>
        <td>
            <jsp:text> First Name*</jsp:text></td>
            <td>
                <input type="text" name="fName" placeholder="name" size="50" required/>
            </td></tr>
    <tr><td>&nbsp;</td><td></td></tr>
    <tr>
        <td>
            <jsp:text> Last Name*</jsp:text></td>
            <td>
                <input type="text" name="lName" placeholder="name" size="50" required/>
            </td></tr>
    <tr><td>&nbsp;</td><td></td></tr>
    <tr>
            <td>
            <jsp:text> Email*</jsp:text></td>
            <td>
                <input type="text" name="signupEmail" placeholder="enter email" size="50" required/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>

        <tr><td>
            <jsp:text> Password:*</jsp:text></td><td>
                <input type="password" name="signupPass" placeholder="password" size="50" 
                       pattern=".{6,}" title="6 Characters minimum"/>
            </td>
        </tr>
        
        <tr><td>&nbsp;</td><td></td></tr>

        <tr><td>
            <jsp:text>Confirm Password:*</jsp:text></td><td>
                <input type="password" name="signupPassCnf" 
                       placeholder="confirm password" size="50" 
                       pattern=".{6,}" title="6 Characters minimum"/>
            </td>
        </tr>
        
        <tr><td>&nbsp;</td><td></td></tr>

        <tr><td></td><td>
                <input type="hidden" name="action" value="create"/>
                <input type="submit" value="Create Account" name="signupBtn" id="signupBTN" />
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
    </table>
</form>


<jsp:include page="footer.jsp"/>
