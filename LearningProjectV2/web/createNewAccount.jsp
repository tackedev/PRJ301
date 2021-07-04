<%-- 
    Document   : createNewAccount
    Created on : Jun 28, 2021, 1:33:56 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="Dispatcher" method="POST">
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /><br/>
            <c:set var="errors" value="${requestScope.INSERT_ERRORS}"/>
            <c:if test="${not empty errors.usernameLengthErr}" >
                <font color="red">
                    ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}" >
                <font color="red">
                    ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /><br/>
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
            Full name <input type="text" name="txtFullName" value="${param.txtFullName}" /><br/>
            <c:if test="${not empty errors.fullNameLengthErr}" >
                <font color="red">
                    ${errors.fullNameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="CreateAccount" name="btnAction" />
            <input type="reset" value="Reset" />
        </form>

        <br/>
    </body>
</html>
