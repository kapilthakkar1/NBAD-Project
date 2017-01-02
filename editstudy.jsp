<%-- 
    Document   : editstudy
    Created on : Sep 23, 2015, 8:01:40 PM
    Author     : MadhaviBhat
--%>
<%@page import="com.java.nbad.project.entity.Study"%>
<jsp:include page="header.jsp"/>

<jsp:include page="leftmenu.jsp"/>
<div class="mainpagesection">
    
    
<h2>Editing a study</h2>

<%
        
    Study edtStudy = null;
    if(request.getAttribute("edtStudy") != null){
        edtStudy= (Study)request.getAttribute("edtStudy"); 
    } else {
        edtStudy = new Study();
    }
%>

 <a href="main.jsp" class="menuItemStyle"> Back to the Main Page</a><br><br>
 <form action="StudyController" method="post">
<table class="contactbox">
    <tr>
        <td>
            <jsp:text>Study Name*</jsp:text></td>
            <td>
                <input type="text" name="newStudyName"  size="50" 
                       value="<%= edtStudy.getName() %>"/>
            </td></tr>
    <tr><td>&nbsp;</td><td></td></tr>    
    <tr>
            <td>
            <jsp:text>Question Text*</jsp:text></td>
            <td>
                <input type="text" name="studyQues"  size="50" value="<%= edtStudy.getQuestion() %>"/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
        
         <tr>
            <td>
            <jsp:text>Question Answer Choices*</jsp:text></td>
            <td>
                <input type="text" name="studyAns"  size="50" required 
                       placeholder="Enter choices seperated by ;"
                       value="<%= edtStudy.getAnswerChoices() %>"
                pattern=".{3,}" title="3 Characters minimum"/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
       
        
        <tr>
            <td>
            <jsp:text>Image*</jsp:text> 
        
        </td>
            <td>
                <img src="<%= edtStudy.getImageURL() %>" width="30" height="30" alt="edQ"/>
                
                <input type="file" name="quesImg" title="Browse" accept="image/*"/>
            
                </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>
        
        <tr>
            <td>
            <jsp:text>#Participants*</jsp:text></td>
            <td>
                <input type="text" name="noOfParticip"  size="50" 
                       value="<%= edtStudy.getRequestedParticipants() %>"/>
            </td></tr>
        <tr><td>&nbsp;</td><td></td></tr>

        <tr><td>
            <jsp:text> Description*</jsp:text></td><td>
                <textarea rows="5" cols="30" name="desc" ><%= edtStudy.getStudyDesc() %></textarea> 
            </td>
        </tr>
        
        <tr><td>&nbsp;</td><td></td></tr>

        
        <tr><td></td><td>
                
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="studyCode" 
                           value="<%= edtStudy.getCode() %>"/>
                <input type="submit" value="Submit" name="contactBtn" id="contactBTN" />
                
            </td></tr>
    </table>
                </form>
</div>

<jsp:include page="footer.jsp"/>
