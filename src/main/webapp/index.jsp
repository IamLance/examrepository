<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="Bean.AlchemyConnector" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mystyle.css"> 
        <title>AlchemyAPI - Feature Extraction and Image Analysis</title>
    </head>
    <body>
        <div class ="main">
            <input id="tab2" type="radio" name="tabs">
            <label for="tab2"> Image Analysis </label>
            <div class ="content">
                <div id ="content1">
                    <form action="FServlet" method="GET">
                        <input type="text" name="furl" size="80">
                        <input type="submit" value="Extract">
                    </form> <br/>
                </div>
                <div id="content2">
                    <form action="IServlet" method="GET">
                        <input type="text" name="gurl" size="80">
                        <input type="submit" value="Extract">
                    </form> <br/>
					<%
						if (request.getAttribute("face") != null){
							out.println("<h3>" + request.getAttribute("face") + "</h3>");
						}
					%>
                </div>
            </div>
			 
        </div>
    </body>
</html>