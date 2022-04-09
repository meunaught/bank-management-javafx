package com.projectyobank.database;

import com.projectyobank.models.Banker;
import com.projectyobank.models.Customer;

import java.sql.*;

public class dbcontroller {
    private static final dbcontroller instance = new dbcontroller();
    public static dbcontroller getInstance() {
        return instance;
    }
    //models
    private Banker banker;
    private Customer customer;


    public static Connection Connector()
    {
        try {
//            System.out.println("fffffffffffffffffffffffffff");
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:my_Database.db");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Connection conection;
    public dbcontroller () {
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
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

    public boolean Verify_User_Login(String Username,String Password)  {
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from admin_login where Username = ? and PassWord = ?";

        try {
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,Username);
            preparedStatement.setString(2,Password);
            resultSet = preparedStatement.executeQuery();
            System.out.println("verify te dhukse");

            if(resultSet.next()) {
                banker = new Banker(resultSet.getString("Username"), resultSet.getString("PassWord"), resultSet.getString("Designation"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                conection.close();
                System.out.println("Yes");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            catch (NullPointerException e) {
                System.out.println(e);
            }
        }
    }

    public Banker getBanker() {
        return banker;
    }
}
