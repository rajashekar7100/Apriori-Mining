<%-- 
    Document   : customerhome
    Created on : Nov 18, 2015, 12:47:27 PM
    Author     : rajashekar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Details</title>
    </head>
    <body>
        <style>
            body{
                background: #d0e4fe;
            }
        </style>
        <form action="/Internet_Store/ItemsList.jsp" method="POST">
            <h1> Customer Home </h1>
            <%
                String customer_id = (String) session.getAttribute("customer_id");
                String customer_firstname = (String) session.getAttribute("customer_firstname");
                String customer_lastname = (String) session.getAttribute("customer_lastname");
                String customer_phonenumber = (String) session.getAttribute("customer_phonenumber");
                String customer_address = (String) session.getAttribute("customer_address");
                String customer_card = (String) session.getAttribute("customer_card");
                String customer_email = (String) session.getAttribute("customer_email");
                String customer_age = (String) session.getAttribute("customer_age");
            %>
            <h3>ID: <% out.println(customer_id); %> </h3>
            <h3>FIRST NAME: <% out.println(customer_firstname); %></h3>
            <h3>LAST NAME: <% out.println(customer_lastname); %></h3>
            <h3>PHONE NUMBER: <% out.println(customer_phonenumber); %></h3>
            <h3>ADDRESS: <% out.println(customer_address); %></h3>
            <h3>CARD_NO: <% out.println(customer_card); %></h3>
            <h3>EMAIL: <% out.println(customer_email);%></h3>
            <h3>AGE: <% out.println(customer_age);%></h3>
            <input type="submit" value="Shop">

        </form>

    </body>
</html>
