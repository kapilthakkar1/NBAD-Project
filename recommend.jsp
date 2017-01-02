<%-- 
    Document   : recommend
    Created on : Sep 23, 2015, 8:00:46 PM
    Author     : MadhaviBhat
--%>

<jsp:include page="header.jsp"/>

<jsp:include page="leftmenu.jsp"/>
<div class="mainpagesection">
<h2>Recommend to friend</h2>

<form action="UserController" method="post">
<table class="contactbox">
    <tr>
        <td> <a href="main.jsp" class="menuItemStyle"> Back to the Main Page</a></td>
    </tr>
    <tr>
        <td>
            <jsp:text> Name*</jsp:text></td>
            <td>
                <input type="text" name="cName"  size="50" required/>
            </td></tr>
    <tr><td>&nbsp;</td><td></td></tr>    
    <tr>
            <td>
            <jsp:text> Email*</jsp:text></td>
            <td>
                <input type="text" name="fromEmail"  size="50" required>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
        
        <tr>
            <td>
            <jsp:text>Friends Email*</jsp:text></td>
            <td>
                <input type="text" name="cEmail"  size="50" required/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>

        <tr><td>
            <jsp:text> Message*</jsp:text></td><td>
            <textarea rows="5" cols="30" name="msg" required></textarea>
            </td>
        </tr>
        
        <tr><td>&nbsp;</td><td></td></tr>

        
        <tr><td></td><td>
                
                <input type="submit" value="Submit" name="contactBtn" id="contactBTN" />
<input type="hidden" name="action" value="recommend"/>
            </td></tr>
        <tr><td></td>
            <td></td></tr>
    </table>
</form>
            <br>
            <i><b>When your friend logs in one user study, you'll get 2 bonus
                    </b></i>
</div>

<jsp:include page="footer.jsp"/>