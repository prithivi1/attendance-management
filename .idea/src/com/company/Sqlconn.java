package com.company;

import java.sql.*;

public class Sqlconn {

    public static Connection getconnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/student";
            String dbUser ="user";
            String dbPass ="useruser";
            conn = DriverManager.getConnection(url,dbUser,dbPass);
            System.out.println("Connection Established");
            return conn;
        }
        catch(Exception c)
        {
            System.out.println(c.getMessage());
            return null;
        }
    }
}
