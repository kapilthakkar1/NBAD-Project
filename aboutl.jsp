<%-- 
    Document   : aboutl
    Created on : Sep 23, 2015, 7:58:48 PM
    Author     : MadhaviBhat
--%>
<jsp:include page="header.jsp"/>

    <%
    if (session.getAttribute("currentUser") != null) {
%>
<jsp:include page="leftmenu.jsp"/>

<%
    }
%>
<div class="howpagesection">

<h2><b>About us</b></h2>
<p style="text-align: justify;">
Researchers Exchange Participation is a platform for researchers to exchange participations.
<br>
<br>
The aim of this platform is to encourage researchers participate in each others user
studies. Moreover, recruiting serious participants has been a burden on a researcher's shoulder,
submit your research study here to get starting Participations.
thus, this platform gives researchers the opportunity to do that effectively and in a suitable time.
</p>

</div>

<jsp:include page="footer.jsp"/>