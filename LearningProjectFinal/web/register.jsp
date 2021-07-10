<%-- 
    Document   : register
    Created on : Jul 5, 2021, 10:26:40 AM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register page</h1>

        <c:set var="errors" value="${requestScope.INSERT_ERRORS}" />

        <form action="registerAction" method="POST">
            Username* <input type="text" name="txtUsername" value="${requestScope.USERNAME}" /> (6 - 20 chars)<br/>

            <c:if test="${not empty errors.usernameLengErr}" >
                <font color="red">
                ${errors.usernameLengErr}
                </font><br/>
            </c:if>

            <c:if test="${not empty errors.usernameIsExisted}" >
                <font color="red">
                ${errors.usernameIsExisted}
                </font><br/>
            </c:if>

            Password* <input type="password" name="txtPassword" value="" /> (6 - 30 chars)<br/>

            <c:if test="${not empty errors.passwordLengthErr}" >
                <font color="red">
                ${errors.passwordLengthErr}
                </font><br/>
            </c:if>

            Confirm* <input type="password" name="txtConfirm" value="" /><br/>

            <c:if test="${not empty errors.confirmNoMatch}" >
                <font color="red">
                ${errors.confirmNoMatch}
                </font><br/>
            </c:if>

            Full name <input type="text" name="txtFullName" value="${requestScope.FULLNAME}" /> (2 - 50 chars)<br/>

            <c:if test="${not empty errors.fullNameLengthErr}" >
                <font color="red">
                ${errors.fullNameLengthErr}
                </font><br/>
            </c:if>

            <input type="submit" value="Register" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
