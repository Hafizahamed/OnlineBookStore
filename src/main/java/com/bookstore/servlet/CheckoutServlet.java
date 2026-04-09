package com.bookstore.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.bookstore.model.CartItem;
import com.bookstore.dao.DBConnection;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if(cart == null || cart.isEmpty()){
            response.sendRedirect("cart.jsp");
            return;
        }

        double total = 0;

        try {
            Connection con = DBConnection.getConnection();

            // Calculate total
            for(CartItem item : cart){
                total += item.getBook().getPrice() * item.getQuantity();
            }

            // Insert into orders
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO orders(total) VALUES(?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setDouble(1, total);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int orderId = 0;

            if(rs.next()){
                orderId = rs.getInt(1);
            }

            // Insert order items
            for(CartItem item : cart){
                PreparedStatement ps2 = con.prepareStatement(
                    "INSERT INTO order_items(order_id, book_title, price, quantity) VALUES(?,?,?,?)"
                );

                ps2.setInt(1, orderId);
                ps2.setString(2, item.getBook().getTitle());
                ps2.setDouble(3, item.getBook().getPrice());
                ps2.setInt(4, item.getQuantity());

                ps2.executeUpdate();
            }

            // Clear cart
            session.removeAttribute("cart");

            // Send order id to JSP
            request.setAttribute("orderId", orderId);
            request.getRequestDispatcher("success.jsp").forward(request, response);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}