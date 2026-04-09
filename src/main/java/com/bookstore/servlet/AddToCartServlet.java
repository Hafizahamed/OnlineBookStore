package com.bookstore.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.bookstore.model.*;
import com.bookstore.dao.DBConnection;

@WebServlet("/addCart")
public class AddToCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if(cart == null) cart = new ArrayList<>();

        boolean found = false;

        for(CartItem item : cart){
            if(item.getBook().getId() == id){
                item.increaseQty();
                found = true;
                break;
            }
        }

        if(!found){
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE id=?");
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    Book b = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("price")
                    );

                    cart.add(new CartItem(b, 1));
                }

            } catch(Exception e){
                e.printStackTrace();
            }
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}