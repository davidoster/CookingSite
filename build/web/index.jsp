<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>Login</div>
        <form name="login" action="LoginController" method="POST">
            <input type="text" name="username" value="" />
            <input type="text" name="password" value="" />
            <input type="submit" value="login" name="login" />
        </form>
        <jsp:useBean id="status" class="java.lang.String" scope="request" />
        <jsp:useBean id="status2" class="java.lang.String" scope="request" />
        <div><h4><%= ((String)request.getAttribute("status")).toString() %></h4></div>
        <div><%= ((String)request.getAttribute("status2")).toString() %></div>
    </body>
</html>
