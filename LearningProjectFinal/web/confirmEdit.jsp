<%-- 
    Document   : confirmEdit
    Created on : Jul 10, 2021, 12:05:33 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm edit</title>
    </head>
    <body>
        <h2>Check your edited infomation</h2>

        <c:set var="dto" value="${sessionScope.EDIT_USER}" />

        Username ${dto.username}<br/>
        Password ${dto.password}<br/>
        Fullname ${dto.lastName}<br/>
        Role <input type="checkbox" disabled="disabled"
                    <c:if test="${dto.role}" >
                        checked="checked"
                    </c:if>
                    /><br/>
        
        <br/>
        
        <a href="updateEditAction" style="margin: 1px">
            <button type="button">Confirm</button>
        </a>
        <a href="editAccountPage" style="margin: 1px">
            <button type="button">Cancel</button>
        </a>
    </body>
</html>
