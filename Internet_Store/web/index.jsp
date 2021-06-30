<%-- 
    Document   : index
    Created on : Sep 27, 2015, 3:34:58 PM
    Author     : rajashekar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Form</title>
    </head>
    <body>
        <style>
            body{
                background: #d0e4fe;
            }
            h1{
                text-align: center;
            }
        </style>
        <h1>NEW CUSTOMER REGISTRATION</h1>
        <form style="text-align:center; font-family: calibri; font-size: 20px; color: black;"  action="RegisterNewCustomer" method = "GET" >
            <label for="Cust_FirstName">Cust_FirstName:</label><br>
            <input type="text" name="firstname" placeholder="Enter firstname">
            <br>
            <label for="Cust_LastName">Cust_LastName:</label><br>
            <input type="text" name="lastname" placeholder="Enter lastname">
            <br>
            <label for="Cust_Phno">Cust_Phno:</label>
            <br>
            <input type="number" name="Phno" placeholder="Enter Phone number">
            <br>
            <label for="Cust_Address">Cust_Address:</label>
            <br>
            <input type="text" name="Address" placeholder="Enter Address">
            <br>
            <label for="Cust_CardNo">Cust_CardNo:</label>
            <br>
            <input type="number" name="CardNo" placeholder="Enter Card Number">
            <br>
            
            <label for="Cust_Email">Cust_Email:</label><br>
            <input type="text" name="Email" placeholder="Enter Email">
            <br>
          
            <label for="Cust_Password">Cust_Password:</label><br>
            <input type="text" name="password" placeholder="Enter password">
            <br>
            <label for="Cust_Age">Cust_Age:</label><br>
            <input type="number" name="age" placeholder="Enter age">
            <br>
            <br>
            <input type="SUBMIT">
        </form>
    </body>
</html>
