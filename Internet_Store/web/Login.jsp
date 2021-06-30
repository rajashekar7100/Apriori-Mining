<%-- 
    Document   : Login
    Created on : Nov 13, 2015, 1:27:25 AM
    Author     : rajashekar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <style>
            body{
                background: #d0e4fe;
            }
        </style>
        <form style="text-align:center; font-family: calibri; font-size: 20px; color: black;" action="LogintoCustomer" method="post">
            <h1>Login</h1>
            <label for="Email">Email:</label>
            <br>
            <input type="text" name="email">
            <br>
            <label for="password">Password:</label>
            <br>
            
            <input type="password" name="pwd"  class="password" autocomplete="off">

            <br>
            <br>
            <input type="submit">
        </form>
    </body>
</html>
