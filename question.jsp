<%-- 
    Document   : question
    Created on : Sep 23, 2015, 8:02:07 PM
    Author     : MadhaviBhat
--%>

<%@page import="com.java.nbad.project.entity.Study"%>
<jsp:include page="header.jsp"/>

<jsp:include page="leftmenu.jsp"/>

<img src="images/tree.jpg" width="250" height="350" alt="tree" />

<div class="mainpagesection">


    <%
        if (request.getAttribute("thisStudy") != null) {

            Study studyItem = (Study) request.getAttribute("thisStudy");


    %>
    <h2><b>Question</b></h2>
    <div style="text-align: justify;">

<form action="StudyController" method="post">
    
         <input type="hidden" name="studyCode" 
                           value="<%= studyItem.getCode()%>"/>
        <%= studyItem.getQuestion()%>

        <br>
    
        <select name="choice">
            <%
                for (int i = 0; i < studyItem.getAnswers().length; i++) {
            %>


            <option value="<%= studyItem.getAnswers()[i]%>">
                <%= studyItem.getAnswers()[i]%>

            </option>


            <%
                }
            %>
        </select>
        
            <input type="hidden" name="action" value="answer"/>
            <input  type="submit" name="Submit" value="Submit"/>
        </form>
    </div>

    <% }
    %>

</div>

<jsp:include page="footer.jsp"/>