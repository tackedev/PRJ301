<%-- 
    Document   : viewCart
    Created on : Jul 7, 2021, 4:23:25 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View cart</title>
    </head>
    <body>
        <a href="shop">Back to Shop page</a>
        <h1>View cart page</h1>

        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}" />
            <c:if test="${not empty items}">
                <form action="removeItems">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Action</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <c:set var="dto" value="${item.key}" />
                                <c:set var="quantity" value="${item.value}" />
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
                                        ${quantity}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${dto.sku}" />
                                    </td>
                                    <td>
                                        ${dto.price * quantity}
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="4">
                                    <a href="shop">Add more items to cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Items" />
                                </td>
                                <td>
                                    <a href="checkout"><button type="button">Checkout</button></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </c:if>
        </c:if>

        <c:if test="${empty items}">
            <h2>No cart is existed!!!</h2>
        </c:if>
    </body>
</html>
