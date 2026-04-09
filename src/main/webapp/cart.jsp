<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.bookstore.model.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>

<style>
body {
    font-family: Arial;
    background: #f5f5f5;
    text-align: center;
}

h2 {
    margin-top: 20px;
}

table {
    width: 70%;
    margin: 20px auto;
    border-collapse: collapse;
    background: white;
    border-radius: 10px;
    overflow: hidden;
}

th, td {
    padding: 15px;
    border-bottom: 1px solid #ddd;
}

th {
    background: #007bff;
    color: white;
}

button {
    padding: 6px 10px;
    margin: 2px;
    border: none;
    cursor: pointer;
    border-radius: 4px;
}

.inc {
    background: green;
    color: white;
}

.dec {
    background: orange;
    color: white;
}

.remove {
    background: red;
    color: white;
}

.checkout {
    background: green;
    color: white;
    padding: 10px 20px;
    font-size: 16px;
}

.total {
    font-size: 22px;
    font-weight: bold;
    margin-top: 20px;
}

a {
    text-decoration: none;
    color: #007bff;
    font-weight: bold;
}
</style>

</head>
<body>

<h2>Your Cart</h2>

<%
List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
double total = 0;
int i = 0;
%>

<% if(cart != null && !cart.isEmpty()) { %>

<table>
<tr>
    <th>Book</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Subtotal</th>
    <th>Action</th>
</tr>

<% for(CartItem item : cart) {
    double sub = item.getBook().getPrice() * item.getQuantity();
    total += sub;
%>

<tr>
    <td><%= item.getBook().getTitle() %></td>
    <td>Rs.<%= item.getBook().getPrice() %></td>

    <td>
        <a href="updateCart?index=<%=i%>&action=dec">
            <button class="dec">-</button>
        </a>

        <%= item.getQuantity() %>

        <a href="updateCart?index=<%=i%>&action=inc">
            <button class="inc">+</button>
        </a>
    </td>

    <td>Rs.<%= sub %></td>

    <td>
        <a href="removeCart?index=<%=i%>">
            <button class="remove">Remove</button>
        </a>
    </td>
</tr>

<%
i++;
} %>

</table>

<div class="total">Total: Rs. <%= total %></div>

<br>

<!-- ✅ Checkout Button -->
<a href="checkout">
    <button class="checkout">Checkout</button>
</a>

<% } else { %>

<p>Cart is empty</p>

<% } %>

<br><br>

<a href="books">← Continue Shopping</a>

</body>
</html>