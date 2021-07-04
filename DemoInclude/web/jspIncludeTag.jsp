<%-- 
    Document   : jspIncludeTag
    Created on : Jun 30, 2021, 10:16:32 AM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo Include</title>
    </head>
    <body>
        <h1>JSP Include Tag</h1>
        Current date is 
        <jsp:include page="myDate.jsp" flush="true" />
    </body>
</html>
