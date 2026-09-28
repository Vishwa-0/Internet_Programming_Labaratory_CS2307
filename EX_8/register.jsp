<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body { 
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif; 
            background-color: #f1f5f9; 
            color: #334155;
            margin: 0;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .details-container {
            width: 100%;
            max-width: 420px; 
            background: #ffffff; 
            padding: 36px; 
            border-radius: 16px;
            box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.08), 0 8px 10px -6px rgba(0, 0, 0, 0.04);
            border: 1px solid #e2e8f0;
            position: relative;
            overflow: hidden;
        }

        /* Decorative top accent bar using a light/fresh green gradient */
        .details-container::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 4px;
            background: linear-gradient(90deg, #10b981, #34d399, #6ee7b7);
        }

        h2 { 
            margin: 0 0 20px 0; 
            color: #0f172a; 
            font-size: 22px;
            font-weight: 600;
            text-align: center;
        }

        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 15px; 
        }

        td { 
            padding: 10px 8px; 
            border-bottom: 1px solid #f1f5f9; 
            font-size: 14px;
        }

        td.label { 
            font-weight: 500; 
            color: #475569; 
            width: 45%; 
        }
    </style>
</head>
<body>
<%
    // Retrieve form parameters submitted from register.html
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String name     = request.getParameter("name");
    String ccnumber = request.getParameter("ccnumber");
    String email    = request.getParameter("email");
    String phone    = request.getParameter("phone");
%>
    <div class="details-container">
        <h2>Registration Successful</h2>
        <table>
            <tr><td class="label">User Name:</td><td><%= username %></td></tr>
            <tr><td class="label">Password:</td><td><%= password %></td></tr>
            <tr><td class="label">Name:</td><td><%= name %></td></tr>
            <tr><td class="label">Credit Card Number:</td><td><%= ccnumber %></td></tr>
            <tr><td class="label">Email:</td><td><%= email %></td></tr>
            <tr><td class="label">Phone Number:</td><td><%= phone %></td></tr>
        </table>
    </div> 
</body>
</html>