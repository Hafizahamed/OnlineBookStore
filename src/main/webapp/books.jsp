<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.bookstore.model.Book" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Book Store</title>

<style>

/* Page animation */
body {
    font-family: Arial;
    background: #f5f5f5;
    margin: 0;
    animation: fadeIn 1s ease-in;
}

@keyframes fadeIn {
    from { opacity: 0; }
    to { opacity: 1; }
}

/* Header */
.header {
    background: linear-gradient(90deg, #007bff, #00c6ff);
    color: white;
    padding: 15px;
    text-align: center;
    font-size: 22px;
}

.header a {
    color: white;
    text-decoration: none;
    margin-left: 10px;
}

/* Grid */
.container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
    gap: 25px;
    padding: 25px;
}

/* Card */
.card {
    position: relative;
    overflow: hidden;
    background: white;
    padding: 15px;
    border-radius: 12px;
    box-shadow: 0 5px 15px rgba(0,0,0,0.1);
    text-align: center;
    transition: all 0.3s ease;
}

.card:hover {
    transform: translateY(-10px) scale(1.03);
    box-shadow: 0 10px 25px rgba(0,0,0,0.2);
}

/* Overlay */
.card::after {
    content: "View";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 160px;
    background: rgba(0,0,0,0.6);
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    opacity: 0;
    transition: 0.3s;
}

.card:hover::after {
    opacity: 1;
}

/* Image */
.card img {
    width: 120px;
    height: 160px;
    object-fit: cover;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.2);
    transition: transform 0.3s ease;
}

.card:hover img {
    transform: scale(1.1);
}

/* Title */
.title {
    font-weight: bold;
    margin: 10px 0;
}

/* Price */
.price {
    color: green;
    margin-bottom: 10px;
}

/* Button */
button {
    padding: 8px 14px;
    background: linear-gradient(45deg, green, limegreen);
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: all 0.3s ease;
}

button:hover {
    transform: scale(1.1);
    background: darkgreen;
    box-shadow: 0 0 10px green;
}

</style>

</head>
<body>

<!-- Header -->
<div class="header">
    📚 Online Book Store |
    🛒 <a href="cart.jsp">Cart</a>
</div>

<div class="container">

<%
List<Book> books = (List<Book>) request.getAttribute("books");

if(books != null){
    for(Book b : books){
%>

<div class="card">

    <!-- Image -->
    <img src="images/<%= b.getId() %>.jpg"
         onerror="this.src='https://via.placeholder.com/120x160?text=No+Image'">

    <div class="title"><%= b.getTitle() %></div>
    <div class="price">Rs.<%= b.getPrice() %></div>

    <form action="addCart" method="post">
        <input type="hidden" name="id" value="<%= b.getId() %>">
        <button>Add to Cart</button>
    </form>

</div>

<%
    }
} else {
%>

<p style="text-align:center;">No books available</p>

<%
}
%>

</div>

</body>
</html>