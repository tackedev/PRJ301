<%-- 
    Document   : confirmCheckout
    Created on : Jul 10, 2021, 11:12:30 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm checkout</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.CART.items}">
            <a href="viewCart">Back to view cart</a>
        </c:if>
        <c:if test="${empty sessionScope.CART.items}">
            <a href="shop">Back to shop page</a>
        </c:if>
        
        <h1>Confirm checkout</h1>

        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}" />
            <c:if test="${not empty items}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}" varStatus="counter">
                            <c:set var="dto" value="${item.key}" />
                            <c:set var="quantity" value="${item.value}" />
                            <tr>
                                <td align="right">
                                    ${counter.count}.
                                </td>
                                <td>
                                    ${dto.name}
                                </td>
                                <td align="right">
                                    <fmt:formatNumber type = "currency" value="${dto.price}" maxFractionDigits="2" />
                                </td>
                                <td align="right">
                                    ${quantity}
                                </td>
                                <td align="right">
                                    <fmt:formatNumber type = "currency" value="${dto.price * quantity}" maxFractionDigits="2" />
                                    <c:set var="totalOrder" value="${totalOrder + dto.price * quantity}" />
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3">
                            </td>
                            <td colspan="2">
                                Total Order: <fmt:formatNumber type = "currency" value="${totalOrder}" maxFractionDigits="2" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                <form action="checkoutAction">
                    <input type="submit" value="Checkout" />
                    <a href="viewCart"><button type="button">Cancel</button></a>
                </form>
            </c:if>
        </c:if>

        <c:if test="${empty items}">
            <h2>No cart is existed!!!</h2>
        </c:if>
    </body>
</html>
