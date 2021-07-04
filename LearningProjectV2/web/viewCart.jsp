<%-- 
    Document   : viewCart
    Created on : Jun 22, 2021, 11:18:10 PM
    Author     : tackedev
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="tackedev.cart.Cart"%>
<%@page import="tackedev.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <a href="Dispatcher?btnAction=ViewOnlineShopping">Back to Online Shopping page</a>
        <h1>Your cart include:</h1>
        <%
            //1. Customer goes to cart's place
            // always having session because didn't set Page Directive session to false
            //2. Customer takes cart
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                //3. Customer takes items from cart
                Map<ProductDTO, Integer> items = cart.getItems();
                if (items != null) {
                    //4. Show items
                    %>
                    <form action="Dispatcher">
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
                                <%
                                    for (ProductDTO product : items.keySet()) {
                                        int count = 0;
                                        %>
                                        <tr>
                                            <td>
                                                <%= ++count %>
                                            </td>
                                            <td>
                                                <%= product.getName() %>
                                            </td>
                                            <td>
                                                <% BigDecimal price = product.getPrice(); %>
                                                <%= price %>
                                            </td>
                                            <td>
                                                <% int quantity = items.get(product); %>
                                                <%= quantity %>
                                            </td>
                                            <td>
                                                <input type="checkbox" name="chkItem" value="<%= product.getSku() %>" />
                                            </td>
                                            <td>
                                                <%= price.multiply(BigDecimal.valueOf(quantity)) %>
                                            </td>
                                        </tr>
                                <%
                                    }
                                %>
                                <tr>
                                    <td colspan="4">
                                        <a href="Dispatcher?btnAction=ViewOnlineShopping">Add more items to cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="RemoveItems" name="btnAction"/>
                                    </td>
                                    <td>
                                        <input type="submit" value="Checkout" name="btnAction" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
        <%
                    return;
                }//end of items is existed
            }//end of cart is existed
        %>
        <h2>No cart is existed!!!</h2>        
    </body>
</html>
