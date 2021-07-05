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
        <a href="logoutAction">Logout</a><br/>
        <font color="red">Welcome, ${sessionScope.USER.lastName}</font>

        <h1>Search page</h1>

        <c:set var="searchValue" value="${param.txtSearch}" />

        <form action="searchAction">
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
                            <!--                        <th>Delete</th>
                                                    <th>Update</th>
                                                    <th>Edit</th>-->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter" >
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                </td>
                                <td>
                                    ${dto.password}
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
                            </tr>
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
