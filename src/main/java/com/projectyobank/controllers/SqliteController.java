package com.projectyobank;

import java.sql.*;

public class SqliteController {

    public static Connection Connector()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn =DriverManager.getConnection("jdbc:sqlite:my_Database.db");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
