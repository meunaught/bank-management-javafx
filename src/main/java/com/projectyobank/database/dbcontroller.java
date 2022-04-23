package com.projectyobank.database;

import com.projectyobank.models.*;

import java.sql.*;

public class dbcontroller {
    private static final dbcontroller instance = new dbcontroller();
    public static dbcontroller getInstance() {
        return instance;
    }
    private Banker banker;
    private Customer customer;

    public Banker getBanker() {
        return banker;
    }
    public Customer getCustomer(){return customer;}


    public static Connection Connector()
    {
        try {
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


    public boolean Verify_Account(long account_number)
    {
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from Login_Info_For_Users where AccountNumber = ?";

        try {
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setLong(1,account_number);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                customer = new Customer(resultSet.getString("Username"),resultSet.getString("email"),
                        resultSet.getString("phone"),resultSet.getString("Address"));
                System.out.println("Customer Created");
                //Setup_Account(resultSet);
                customer.setAccount(resultSet.getString("AccountType"),resultSet.getLong("AccountNumber"),
                        resultSet.getLong("Time"),resultSet.getDouble("Balance"),resultSet.getDouble("MainBalance"),
                        resultSet.getDouble("WithdrawAmount"));

                return true;
            }
            else
            {
                System.out.println("didn't find account number");
                return false;
            }

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
            catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void Update_Account(Account account)
    {
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
        }

        PreparedStatement preparedStatement = null;
        String Query = "UPDATE Login_Info_For_Users SET Balance = ? ,MainBalance = ? ,WithdrawAmount = ?,Time = ? WHERE" +
                " AccountNumber = ?";
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setDouble(2,account.getMain_balance());
            preparedStatement.setDouble(3,account.getWithdraw_amount());
            preparedStatement.setLong(4,account.getTime().getTime());
            preparedStatement.setLong(5,account.getNumber());

            int a = preparedStatement.executeUpdate();
            System.out.println("Update database " + a);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
                System.out.println("Yes");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e) {
                System.out.println(e);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}
