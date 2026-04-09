package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;

        try {
            // ✅ FORCE driver load (IMPORTANT FIX)
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookstore?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "1234"
            );

            System.out.println("✅ Connected to MySQL");

        } catch(Exception e) {
            System.out.println("❌ Connection Error:");
            e.printStackTrace();
        }

        return con;
    }
}