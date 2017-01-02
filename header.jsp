<%-- 
    Document   : header
    Created on : Sep 23, 2015, 8:03:32 PM
    Author     : MadhaviBhat
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JSP Page</title>
        <link href="styles/allStyles.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        
            <div class="header1">

                <form action="UserController" method="get">   
                <input type="hidden" name="action" value="home"/>
                <input class="headerTitle" type="submit" 
                       value="Researchers Exchange Participations">
                </form>
                
                <%
                    if (session.getAttribute("currentUser") != null) {
                %>
                <form action="UserController" method="get">   
                <input type="hidden" name="action" value="logout"/>
                <input class="logoutLink" type="submit" value="Logout"/>
                </form>
                <b class="headerLinks">    
                <%="Hello, " +request.getSession().getAttribute("currentUser")%>
                </b>
                
                <%
                } else {
                %> 


                <a class="headerLinks" href="login.jsp">Login</a>
                <%  }
                %>

                <form action="UserController" method="get">   
                <input type="hidden" name="action" value="how"/>
                <input class="logoutLink" type="submit" value="How It Works"/>
                </form>
                
                <form action="UserController" method="get">   
                <input type="hidden" name="action" value="about"/>
                <input class="logoutLink" type="submit" value="About Us"/>
                </form>
                
            </div>
        