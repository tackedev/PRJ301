<%-- 
    Document   : jstlUrl
    Created on : Jul 1, 2021, 9:02:57 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL</title>
    </head>
    <body>
        <h1>JSTL URL Releting Demo</h1>
        <c:import var="s" url="jspIncludeTag.jsp" />
        ${s}
    </body>
</html>
