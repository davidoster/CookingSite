<%-- 
    Document   : subscriber
    Created on : Mar 21, 2019, 2:04:45 PM
    Author     : George.Pasparakis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subscriber View</title>
    </head>
    <body>
        <jsp:include page="../jspf/header.jsp"></jsp:include>
        <h1>Hello Subscriber ...</h1>
        <jsp:useBean id="recipe" class="models.Recipe" scope="request" />
        Recipe Id: <%= ((models.Recipe)request.getAttribute("recipe")).getId() %><br />
        Recipe Title: <%= ((models.Recipe)request.getAttribute("recipe")).getTitle() %><br />
        Description: <%= ((models.Recipe)request.getAttribute("recipe")).getDescription() %><br />
        Image: <img src="<%= ((models.Recipe)request.getAttribute("recipe")).getImage() %>" /><br />
    </body>
</html>
