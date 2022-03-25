package com.example.projectyobank;

import java.sql.*;

public class Model_Sqlite {
    public static Connection conection;
    public Model_Sqlite () {
        conection = SqliteController.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
    }

    public boolean isDbConnected() {
        try {
            return !conection.isClosed();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
