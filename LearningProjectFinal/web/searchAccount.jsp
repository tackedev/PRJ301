<%-- 
    Document   : search
    Created on : Jul 4, 2021, 1:39:38 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <a href="logoutAction" style="margin-right: 3px">Logout</a>
        <a href="shop">Go to Shop page</a><br/>
        
        <c:if test="${not empty sessionScope.USER.lastName}" >
            <h2 style="color:red">Welcome, ${sessionScope.USER.lastName}</h2>
        </c:if>

        <h1>Search page</h1>

        <c:set var="searchValue" value="${param.txtSearch}" />

        <form action="searchAccountAction">
            Search <input type="text" name="txtSearch" value="${searchValue}" />
            <input type="submit" value="Search" />
        </form><br/>

        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <c:if test="${not empty result}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter" >
                        <form action="updateAccountAction" method="POST">
                            <tr>
                                <td align="right">
                                    ${counter.count}.
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                    <!-- Show errors -->
                                    <c:if test="${dto.username eq param.txtUsername}">
                                        <c:if test="${not empty requestScope.INSERT_ERRORS}" >
                                            <br/>
                                            <font color="red">${requestScope.INSERT_ERRORS.passwordLengthErr}</font>
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    ${dto.lastName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkRole" value="ON" 
                                           <c:if test="${dto.role}" >
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="deleteAccountAction">
                                        <c:param name="txtUsername" value="${dto.username}" />
                                        <c:param name="txtSearch" value="${searchValue}" />
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                    <!-- Show errors -->
                                    <c:if test="${dto.username eq param.txtUsername}">
                                        <c:if test="${not empty requestScope.DELETE_ACCOUNT_ERRORS}" >
                                            <br/><font color="red">${requestScope.DELETE_ACCOUNT_ERRORS.deleteYourAccount}</font>
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="hidden" name="txtSearch" value="${searchValue}" />
                                    <input type="submit" value="Update" />
                                </td>
                                <td>
                                    <c:url var="editLink" value="editAccount" >
                                        <c:param name="txtUsername" value="${dto.username}" />
                                        <c:param name="txtSearch" value="${searchValue}" />
                                    </c:url>
                                    <a href="${editLink}">
                                        <button type="button">Edit</button>
                                    </a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}" >
            <h2>No record is matched!!!</h2>
        </c:if>
    </c:if>
</body>
</html>
