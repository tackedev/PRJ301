<%-- 
    Document   : demo
    Created on : Jun 20, 2021, 9:58:24 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Demo Standard Action</h1>
        
        <h2>Student 1</h2>
        <p>
            <jsp:useBean id="student1" class="beans.student.Student" scope="request"/>
            <jsp:setProperty name="student1" property="name" value="LE Quang Ky" />
            <jsp:getProperty name="student1" property="name" />
        </p>
        
    </body>
</html>
