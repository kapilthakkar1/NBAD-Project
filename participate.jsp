<%-- 
    Document   : participate
    Created on : Sep 23, 2015, 8:01:52 PM
    Author     : MadhaviBhat
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.java.nbad.project.entity.Study"%>
<jsp:include page="header.jsp"/>

<jsp:include page="leftmenu.jsp"/>

<div class="mainpagesection">
    <h2><b>Studies</b></h2>


    <table class="TFtable">

        <tr>
            <th>Study Name</th>
            <th>Image</th>
            <th>Question</th>
            <th>Action</th>
        </tr>


        <%
            if(request.getAttribute("partList") != null){
                
            List<Study> studyItems =  (List<Study>)request.getAttribute("partList");
            
            for (int i = 0; i < studyItems.size(); i++) {
            
            
                        
        %>
        <tr><td>
            <%= studyItems.get(i).getName()%>
            </td><td>
                <img src="<%= studyItems.get(i).getImageURL()%>" width="30" height="30" alt="q1"/>
            </td><td><%= studyItems.get(i).getQuestion()%></td>
            <td><form action="StudyController" method="post">
                    <input type="hidden" name="studyCode" 
                           value="<%= studyItems.get(i).getCode()%>"/>
                    <input type="hidden" name="action" 
                           value="participate"/>
                    <input  type="submit" name="Participate" value="Participate" 
                            /></form></td></tr>
        
            
        <% }
        }
        %>



    </table>


</div>

<jsp:include page="footer.jsp"/>
