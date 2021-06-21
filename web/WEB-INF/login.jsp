<%-- 
    Document   : login
    Created on : Jun 20, 2021, 10:32:23 AM
    Author     : 840979
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="login">
            <label>Username:</label>
            <input type="text" name="usernameEntered" value=${usernameEnetered}><br>
            <label>Password:</label>
            <input type="password" name="passwordEnetered"><br>
            <input type="submit" value="Login">
        </form>
        <p>${msg}</p>
    </body>
</html>
