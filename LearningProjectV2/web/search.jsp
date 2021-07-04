<%-- 
    Document   : search
    Created on : Jun 22, 2021, 10:12:54 AM
    Author     : tackedev
--%>

<%--<%@page import="java.util.List"%>
<%@page import="tackedev.registration.RegistrationDTO"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USERNAME}
        </font>
        <h1>Search page</h1>
        <form action="Dispatcher">
            Search <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" name="btnAction" />
        </form>
        <br/>

        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="Dispatcher">
                            <tr>
                                <td>
                                    ${counter.count}.
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.lastName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkRole" value="ON"
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="Dispatcher">
                                        <c:param name="btnAction" value="Delete" />
                                        <c:param name="txtUsername" value="${dto.username}" />
                                        <c:param name="txtLastSearch" value="${searchValue}" />
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="txtLastSearch" value="${searchValue}" />
                                    <input type="submit" value="Update" name="btnAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched!!!
            </h2>
        </c:if>
    </c:if>
    <%--
    <%
        String username = (String) session.getAttribute("USERNAME");
        String searchValue = (String) request.getParameter("txtSearch");
        if (username != null) {
            %>
            <font color="red">Welcome <%= username %>!</font>
    <%
        }
    %>
    <h1>Search page</h1>
    <form action="Dispatcher">
        Search <input type="text" name="txtSearch" value="<%= (searchValue != null) ? searchValue : "" %>" />
        <input type="submit" value="Search" name="btnAction" />
    </form>
    <br/>
    
    <%
        if (searchValue != null) {
            List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
            if (result != null) {
                %>
                <table border="1">
                    <thead>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </thead>
                    <tbody>
                        <%
                            int count = 0;
                            for (RegistrationDTO dto: result) {
                                String urlRewriting = "Dispatcher?btnAction=Delete"
                                                    + "&txtUsername=" + dto.getUsername()
                                                    + "&txtLastSearch=" + searchValue;
                                %>
                                <form action="Dispatcher">
                                    <tr>
                                        <td>
                                            <%= ++count %>.
                                        </td>
                                        <td>
                                            <%= dto.getUsername() %>
                                            <input type="hidden" name="txtUsername" value="<%= dto.getUsername() %>" />
                                        </td>
                                        <td>
                                            <input type="text" name="txtPassword" value="<%= dto.getPassword() %>" />
                                        </td>
                                        <td>
                                            <%= dto.getLastName() %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkRole" value="ON" 
                                                   <%
                                                       if (dto.isRole()) {
                                                           %>
                                                           checked="checked"
                                                   <%
                                                       }//end admin role is true
                                                   %>
                                                   />
                                        </td>
                                        <td>
                                            <a href="<%= urlRewriting %>">Delete</a>
                                        </td>
                                        <td>
                                            <input type="submit" value="Update" name="btnAction" />
                                            <input type="hidden" name="txtLastSearch" value="<%= searchValue %>" />
                                        </td>
                                    </tr>
                                </form>
                        <%
                            }
                        %>
                    </tbody>
                </table>
    <%
            }
        }
    %>
    --%>
</body>
</html>
