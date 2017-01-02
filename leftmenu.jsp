
<%@page import="com.java.nbad.project.entity.User"%>
<div class="nav">
<% User user = (User)request.getSession().getAttribute("theUser");
%>

    <a href="#" class="menuItemStyle">Coins 
    (<%= user.getCoins() %>)
    </a>
    <hr>
    <a href="#" class="menuItemStyle">Participant (<%= user.getParticipants() %>)</a>
    <hr>
    <a href="#" class="menuItemStyle">Participation (<%= user.getParticipation() %>)</a>
    <hr>
    &nbsp;
    <hr>

    <form action="UserController" method="get">   
        <input type="hidden" name="action" value="main"/>
        <input class="menuItemStyle" type="submit" value="Home"/>
    </form>
    
    <hr>
    <form action="StudyController" method="post">   
        <input type="hidden" name="action" value="participate"/>
        <input class="menuItemStyle" type="submit" value="Participate"/>
    </form>
    
    <hr>
    
    <form action="StudyController" method="post">   
        <input type="hidden" name="action" value="myStudies"/>
        <input class="menuItemStyle" type="submit" value="My Studies"/>
    </form>
    
    <hr>
    <a href="recommend.jsp" class="menuItemStyle">Recommend</a>
    <hr>
    <a href="question.jsp" class="menuItemStyle">My Questions</a>
    <hr>
    <a href="contact.jsp" class="menuItemStyle">Contact</a>

</div>

