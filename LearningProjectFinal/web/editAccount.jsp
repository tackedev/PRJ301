<%-- 
    Document   : editAccount
    Created on : Jul 5, 2021, 10:09:16 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account</title>
    </head>
    <body>
        <h1>Edit account page</h1>

        <c:set var="dto" value="${sessionScope.EDIT_USER}" />

        <form action="confirmEdit" method="POST">
            Username ${dto.username}<br/>
            <input type="hidden" name="txtUsername" value="${dto.username}" />
            Password <input type="text" name="txtPassword" value="${dto.password}" /> (6 - 30 chars)<br/> 
            Full name <input type="text" name="txtFullName" value="${dto.lastName}" /> (2 - 50 chars)<br/>
            Role <input type="checkbox" name="chkRole" value="ON"
                        <c:if test="${dto.role}" >
                            checked="checked"
                        </c:if>
                        /><br/>
            <input type="submit" value="Update" />
        </form>
    </body>
</html>
