<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="Bean.AlchemyConnector" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mystyle.css"> 
        <title>Information Extraction Complete</title>
    </head>
    <body>

    <% if (request.getAttribute("msg") != null) { %>
       	<div><%= request.getAttribute("msg") %></div>
    <% } %>
    <% 
        List<String> result = (List<String>) request.getAttribute("result");
         if (result == null) {
        	 result = new ArrayList<String>();
         }

         for (String rs : result) {
      %>
        	<div><%= rs %></div>
      <% } %>
    </body>
</html>