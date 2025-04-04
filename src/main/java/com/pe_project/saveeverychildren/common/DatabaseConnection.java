package com.pe_project.saveeverychildren.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    String url = "jdbc:mysql://localhost:3306/charity";
    String username = "root";
    String password = "1234";
    Connection conn = null;

    public Connection getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);

            System.out.println("Connection Successful.");
        } catch(Exception e){
            System.out.println(e);
        }

        return conn;
    }
}
