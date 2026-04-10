package com.bookstore.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.bookstore.model.Book;
import com.bookstore.dao.DBConnection;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("🔥 BookServlet is running");

        List<Book> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            // ✅ Check connection
            if (con == null) {
                System.out.println("❌ DB CONNECTION FAILED");
            } else {
                System.out.println("✅ DB CONNECTED");
            }

            System.out.println("Running query...");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM books");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Book: " + rs.getString("title"));

                Book b = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("price")
                );

                list.add(b);
            }

            System.out.println("Total books: " + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("books", list);
        request.getRequestDispatcher("books.jsp").forward(request, response);
    }
}