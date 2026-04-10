package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Railway MySQL connection
            String url = "jdbc:mysql://mysql.railway.internal:3306/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String password = "tzjYFbLZLXNdCweAzzsEvLxMJmEyTNkZ";

            con = DriverManager.getConnection(url, user, password);

            System.out.println("✅ Connected to Railway DB");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}