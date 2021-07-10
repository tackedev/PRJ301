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
        <!-- If don't have session or Authenticated User, AuthenticationFilter will redirect to Login page  -->
        <a href="search">Back</a>
        <h1>Online Shopping Page</h1>

        <c:set var="productList" value="${requestScope.PRODUCT_LIST}" />
        <c:set var="currentCart" value="${sessionScope.CART}" />

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
                            <!--Display quantity after minus quantity in Cart-->
                            <c:set var="remainQuantity" value="${dto.quantity - currentCart.getItemQuantityBySku(dto.sku)}" />
                            ${remainQuantity}
                        </td>
                        <td>
                            <form action="addToCart" method="POST">
                                <input type="hidden" name="txtSku" value="${dto.sku}" />
                                <input type="submit" value="Add to cart"
                                       <c:if test="${remainQuantity == 0}" >
                                           disabled
                                       </c:if>
                                       />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="viewCart">
            <input type="submit" value="View cart" />
        </form>
    </body>
</html>
