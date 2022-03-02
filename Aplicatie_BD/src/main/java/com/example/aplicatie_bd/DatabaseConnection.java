package com.example.aplicatie_bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.lang.Boolean;

import java.sql.*;

public class DatabaseConnection {
    public static Connection databaseLink;

    public static Connection getConnection() {
        String username = "sa";
        String password = "parola123";
        String url = "jdbc:jtds:sqlserver://DESKTOP-7PVTQE8;instanceName=SQLEXPRESS;databaseName=Proiect";
        try {
            databaseLink=DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println("Ooops, something is wrong!");
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }



}
