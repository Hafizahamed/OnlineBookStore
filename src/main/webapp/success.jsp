<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Success</title>

<style>
body {
    font-family: Arial;
    text-align: center;
    background: #f5f5f5;
}

.box {
    background: white;
    padding: 30px;
    margin: 100px auto;
    width: 40%;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.2);
}

h2 {
    color: green;
}
</style>

</head>
<body>

<div class="box">
    <h2>🎉 Order Placed Successfully!</h2>
    <p>Your Order ID: <b><%= request.getAttribute("orderId") %></b></p>

    <br>
    <a href="books">Continue Shopping</a>
</div>

</body>
</html>