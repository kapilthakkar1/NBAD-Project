<%-- 
    Document   : newstudy
    Created on : Sep 23, 2015, 8:01:25 PM
    Author     : MadhaviBhat
--%>

<jsp:include page="header.jsp"/>

<jsp:include page="leftmenu.jsp"/>
<div class="mainpagesection">
<h2>Adding a study</h2>

 <a href="main.jsp" class="menuItemStyle">
      Back to the Main Page</a>
<form action="StudyController" method="post">
<table class="contactbox">
    <tr>
        <td>
            <jsp:text>Study Name*</jsp:text></td>
            <td>
                <input type="text" name="newStudyName"  size="50" required/>
            </td></tr>
    <tr><td>&nbsp;</td><td></td></tr>    
    <tr>
            <td>
            <jsp:text>Question Text*</jsp:text></td>
            <td>
                <input type="text" name="studyQues"  size="50" required/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
        
        <tr>
            <td>
            <jsp:text>Question Answer Choices*</jsp:text></td>
            <td>
                <input type="text" name="studyAns"  size="50" required 
                       placeholder="Enter choices seperated by ;"
                       pattern=".{3,}" title="3 Characters minimum"/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
        
        <tr>
            <td>
            <jsp:text>Image*</jsp:text></td>
            <td>
                <input type="file" name="quesImg" accept="image/*"/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
        
        <tr>
            <td>
            <jsp:text>#Participants*</jsp:text></td>
            <td>
                <input type="text" name="noOfParticip" value="0" size="50" required/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>

        <tr><td>
            <jsp:text> Description*</jsp:text></td><td>
            <textarea rows="5" cols="30" name="desc"> </textarea>
            </td>
        </tr>
        
        <tr><td>&nbsp;</td><td></td></tr>

        
        <tr><td></td><td>
                
                    <input type="hidden" name="action"  value="add"/>
                <input type="submit" value="Submit" name="contactBtn" id="contactBTN" />
                
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
    </table>
</form>
</div>

<jsp:include page="footer.jsp"/>
