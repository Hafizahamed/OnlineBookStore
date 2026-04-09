package com.bookstore.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

import com.bookstore.model.CartItem;

@WebServlet("/removeCart")
public class RemoveFromCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int index = Integer.parseInt(request.getParameter("index"));

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if(cart != null && index < cart.size()){
            cart.remove(index);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}