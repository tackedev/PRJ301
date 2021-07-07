<%-- 
    Document   : shop
    Created on : Jul 7, 2021, 2:55:20 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>Online Shopping Page</h1>

        <c:set var="productList" value="${requestScope.PRODUCT_LIST}" />

        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Book Title</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${productList}" varStatus="counter" >
                    <tr>
                        <td>
                            ${counter.count}.
                        </td>
                        <td>
                            ${dto.name}
                        </td>
                        <td>
                            ${dto.price}
                        </td>
                        <td>
                            ${dto.description}
                        </td>
                        <td>
                            ${dto.quantity}
                        </td>
                        <td>Action</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
