<%-- 
    Document   : confirmCheckout
    Created on : Jul 10, 2021, 11:12:30 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm checkout</title>
    </head>
    <body>
        <h1>Confirm checkout</h1>

        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}" />
            <c:if test="${not empty items}">
                <form action="checkoutAction">
                    <c:if test="${not empty sessionScope.USER}">
                        Customer:  ${sessionScope.USER.lastName}
                        <input type="hidden" name="txtCustomer" value="${sessionScope.USER.lastName}" />
                    </c:if>
                    <c:if test="${empty sessionScope.USER}">
                        Customer:  <input type="text" name="txtCustomer" value="" />
                    </c:if>
                    <c:if test="${not empty requestScope.EMPTY_CUSTOMER_ERRORS.emptyCustomer}">
                        <br/><font color="red">${requestScope.EMPTY_CUSTOMER_ERRORS.emptyCustomer}</font>
                    </c:if>
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
                                        ${dto.price * quantity}
                                        <c:set var="totalOrder" value="${totalOrder + dto.price * quantity}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                </td>
                                <td colspan="2">
                                    Total Order: ${totalOrder}
                                </td>
                            </tr>
                        </tbody>
                    </table>
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
