<%-- 
    Document   : error
    Created on : May 10, 2020, 7:30:26 PM
    Author     : Aaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Database</title>
        <link rel="stylesheet" type="text/css" href="../css/mdb.css">
    </head>
    <body>
        <%@include file="header.jspf"%>
        <section>
            <h1>${message}</h1>
            <a href=".">Go to main page</a>
        </section>  
        <br>
        <%@include file="footer.jspf"%>
    </body>
</html>
