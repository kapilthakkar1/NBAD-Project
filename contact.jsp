<%-- 
    Document   : contact
    Created on : Sep 23, 2015, 7:58:59 PM
    Author     : MadhaviBhat
--%>

<jsp:include page="header.jsp"/>

<jsp:include page="leftmenu.jsp"/>
<div class="mainpagesection">
<h2>Contact</h2>

<form action="UserController" method="post">
<table class="contactbox">
    <tr>
        <td> <a href="main.jsp" class="menuItemStyle"> Back to the Main Page</a></td>
    </tr>
    <tr>
        <td>
            <jsp:text> Name*</jsp:text></td>
            <td>
                <input type="text" name="cName" placeholder="name" size="50" required/>
            </td></tr>
    <tr><td>&nbsp;</td><td></td></tr>    
    <tr>
            <td>
            <jsp:text> Email*</jsp:text></td>
            <td>
                <input type="text" name="cEmail" placeholder="enter email" size="50" required/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>

        <tr><td>
            <jsp:text> Message*</jsp:text></td><td>
            <textarea rows="5" cols="30" name="cMsg"></textarea>
            </td>
        </tr>
        
        <tr><td>&nbsp;</td><td></td></tr>

        
        <tr><td></td><td>
                
                    <input type="hidden" name="action" value="contact"/>
                <input type="submit" value="Submit" name="contactBtn" id="contactBTN" />
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
    </table>
                            </form>

</div>

<jsp:include page="footer.jsp"/>