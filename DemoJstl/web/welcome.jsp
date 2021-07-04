<%-- 
    Document   : welcome
    Created on : Jun 28, 2021, 12:09:02 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <c:set var="num" value="10" />
        Display value of num: <c:out value="${num}"/><br/>
        Removing value of num <c:remove var="num" /><br/>
        Display again value of num: <c:out value="${num}"/><br/>
        
        <br/>
        <%--
        <c:set var="addInfo" value="INFO" />
        ${addInfo} <br/>
        <%= request.getAttribute("addInfo") %><br/>
        
        
        
        <c:set var="addInfo" value="INFO" scope="request"/>
        ${addInfo}<br/>
        <%= request.getAttribute("addInfo")%><br/>
        --%>
        <c:set var="addInfo" value="INFO" scope="session" />
        ${addInfo}<br/>
        <%= request.getAttribute("addInfo")%><br/>
        ${requestScope.addInfo}<br/>
        <%= session.getAttribute("addInfo") %><br/>
        ${sessionScope.addInfo}<br/>
       
    </body>
</html>
