<%-- 
    Document   : implicitObj
    Created on : Jun 4, 2021, 8:30:11 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Implicit Object</title>
    </head>
    <body>
        <h1>Request - Response - Out Object</h1>
        <%
            response.setDateHeader("Expires", 0);
            response.setHeader("Pragma", "no-cache");
            if (request.getProtocol().equals("HTTP/1.1")) {
                response.setHeader("Cache-Control", "no-cache");
                //out.flush();
                System.out.println(response.isCommitted());
                out.print("<b>The protocol used is:<b/> " + request.getProtocol());
            }
        %>
    </body>
</html>
