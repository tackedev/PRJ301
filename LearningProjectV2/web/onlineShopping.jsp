<%-- 
    Document   : onlineShopping
    Created on : Jun 22, 2021, 9:55:58 PM
    Author     : tackedev
--%>

<%@page import="java.util.List"%>
<%@page import="tackedev.cart.Cart"%>
<%@page import="tackedev.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <a href="Dispatcher">Back to Login page</a>
        <h1>Online Shopping page</h1>
        
        <%
            List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
        %>
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
                <%
                    for (ProductDTO product: productList) {
                        int count = 0;
                        %>
                        <form action="Dispatcher">
                            <tr>
                                <td>
                                    <%= ++count %>.
                                </td>
                                <td>
                                    <%= product.getName() %>
                                    <input type="hidden" name="txtSku" value="<%= product.getSku() %>" />
                                </td>
                                <td>
                                    <%= product.getPrice()%>
                                </td>
                                <td>
                                    <%= product.getDescription()%>
                                </td>
                                <td>
                                    <%
                                        int quantity = product.getQuantity();
                                        //get current Cart
                                        Cart currentCart = (Cart) session.getAttribute("CART");
                                        if (currentCart != null) {
                                            String sku = product.getSku();
                                            quantity -= currentCart.getItemQuantity(sku);
                                        }
                                    %>
                                    <%= quantity %>
                                </td>
                                <td>
                                    <input type="submit" value="AddToCart" name="btnAction" />
                                </td>
                            </tr>
                        </form>
                <%
                    }
                %>
            </tbody>
        </table>
        <form action="Dispatcher">
            <input type="submit" value="ViewCart" name="btnAction" />
        </form>
    </body>
</html>
