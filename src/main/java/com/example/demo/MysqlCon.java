package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MysqlCon {
    public static Connection getdbconnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoDB","Demo","pass123");
            return  con;
        } catch (Exception e) {
            throw new RuntimeException("Error in DB connection", e) ;
        }
    }
}
