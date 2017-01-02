<%-- 
    Document   : studies
    Created on : Sep 23, 2015, 8:01:11 PM
    Author     : MadhaviBhat
--%>

<%@page import="com.java.nbad.project.entity.Study"%>
<%@page import="java.util.List"%>
<jsp:include page="header.jsp"/>

<jsp:include page="leftmenu.jsp"/>

<div class="mainpagesection">
    <h2><b>My Studies</b></h2>

    <a href="newstudy.jsp" class="menuItemStyle"> Add a new study</a><br>
    <a href="main.jsp" class="menuItemStyle"> Back to the Main Page</a><br><br>


    <table class="TFtable">

        <tr>
            <th>Study Name</th>
            <th>Requested Participants</th>
            <th>Participations</th>
            <th>Status</th>
            <th>Action</th>
        </tr>

        <%
            if (request.getAttribute("studyList") != null) {

                List<Study> studyItems = (List<Study>) request.getAttribute("studyList");

                for (int i = 0; i < studyItems.size(); i++) {


        %>

        <tr>
            <td><%= studyItems.get(i).getName()%></td>
            <td><%= studyItems.get(i).getRequestedParticipants()%></td>
            <td><%= studyItems.get(i).getNoOfParticipants()%></td>
            <td>
                <form action="StudyController" method="post">
                    <input type="hidden" name="studyCode" 
                           value="<%= studyItems.get(i).getCode()%>"/>


                    <%
                        String statusAction = "start";
                        String displayAction = "Start";
                        if (studyItems.get(i).getStudyStatus() != null && 
                                "start".equalsIgnoreCase(studyItems.get(i).getStudyStatus())) {
                            statusAction = "stop";
                            displayAction = "Stop";
                        }
                    %>


                    <input type="hidden" value= "<%= statusAction%>" name="action"/>

                    <input  type="submit" name="Edit" value="<%= displayAction%>"/>
                </form>

            </td>
            <td><form action="StudyController" method="post">
                    <input type="hidden" name="studyCode" 
                           value="<%= studyItems.get(i).getCode()%>"/>
                    <input type="hidden" name="action" value="edit" />

                    <input  type="submit" name="Edit" value="Edit" 
                            /></form></td>
        </tr>

        <% }
            }
        %>

    </table>


</div>

<jsp:include page="footer.jsp"/>
