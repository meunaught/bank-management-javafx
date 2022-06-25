package com.projectyobank.database;

import com.projectyobank.models.*;
import javafx.event.ActionEvent;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.Date;

public class dbcontroller {
    private static final dbcontroller instance = new dbcontroller();
    public static dbcontroller getInstance() {
        return instance;
    }
    //models
    private Banker banker;
    private Customer customer;
//    private Account account;

    public Banker getBanker() {
        return banker;
    }
    public Customer getCustomer(){return customer;}
//    public Account getAccount() {return account;}


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
        try {
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
                String temp = resultSet.getString("Designation");
                if(temp.compareToIgnoreCase("Manager") == 0)
                {
                    banker = new Manager(resultSet.getString("Username"),
                            resultSet.getString("PassWord"),
                            resultSet.getString("Designation"));
                }
                else if(temp.compareToIgnoreCase("Senior_Officer") ==0)
                {
                    banker = new Senior_Officer(resultSet.getString("Username"),
                            resultSet.getString("PassWord"),
                            resultSet.getString("Designation"));

                }
                else if(temp.compareToIgnoreCase("Junior_Officer")==0)
                {
                    banker = new Junior_Officer(resultSet.getString("Username"),
                            resultSet.getString("PassWord"),
                            resultSet.getString("Designation"));
                }
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
                System.out.println("Yes in verify user login");
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
        try {
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
                        resultSet.getLong("phone"),resultSet.getString("Address"));

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
                System.out.println("Yes in  verify account");
            }
            catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void SetProperties(Account account)
    {
        try {
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from Account_Properties where Type = ?";
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,account.getType());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                System.out.println("Set properties");
                account.getInterest().setRate(resultSet.getDouble("InterestRate"));
                account.getInterest().setRate_hour(resultSet.getDouble("InterestRateHour"));
                account.setMax_withdraw_amount(resultSet.getDouble("MaxWithdraw"));
                account.setMinimum_amount_for_account_creation(resultSet.getDouble("MinimumAmount"));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                conection.close();
                System.out.println("Yes in set properties");
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
        try {
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
        }

        PreparedStatement preparedStatement = null;
        String Query = "UPDATE Login_Info_For_Users SET Balance = ? ,MainBalance = ? ,WithdrawAmount = ?,Time = ?,Status = ? WHERE" +
                " AccountNumber = ?";
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setDouble(2,account.getMain_balance());
            preparedStatement.setDouble(3,account.getCurrent_withdraw_amount());
            preparedStatement.setLong(4,account.getTime().getTime());
            preparedStatement.setString(5,account.getStatus());
            preparedStatement.setLong(6,account.getNumber());

            System.out.println("eije");
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
                //System.out.println("Yes in update database");
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

    public void addCustomer(String username,String accountType,long accountNumber,double mainBalance,double balance,String email,long phone,String address)
    {
        try {
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        String Query = "INSERT INTO Login_Info_For_Users(Username,AccountType,AccountNumber,MainBalance,Balance,Email," +
                "Date_of_Account_Creation,WithdrawAmount,Time,phone,Address,Status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try
        {

            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,accountType);
            preparedStatement.setLong(3,accountNumber);
            preparedStatement.setDouble(4,mainBalance);
            preparedStatement.setDouble(5,balance);
            preparedStatement.setString(6,email);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            preparedStatement.setString(7,formatter.format(date));
            preparedStatement.setDouble(8,0);
            preparedStatement.setLong(9,System.currentTimeMillis());
            preparedStatement.setLong(10,phone);
            preparedStatement.setString(11,address);
            preparedStatement.setString(12,"Unmatured");
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
                //System.out.println("Yes in update database");
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

    public void deleteAccount(long accountNumber)
    {
        try {
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        String Query = "DELETE FROM Login_info_For_Users WHERE AccountNumber = ?";
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setLong(1,accountNumber);
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
                //System.out.println("Yes in update database");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addEmployee(String username,String password,String designation)
    {
        try {
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        String Query = "INSERT INTO admin_login(Username,PassWord,Designation) VALUES(?,?,?)";
        try
        {

            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,designation);
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
                //System.out.println("Yes in update database");
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

    public void addTransaction(double previousBalance,double amount,String transactionType,Customer customer)
    {
        try {
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
//            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        String Query = "INSERT INTO Transaction(Username,AccountNumber,AccountType,TransactionType," +
                "TransactionAmount,CurrentBalance,PreviousBalance,Date) VALUES(?,?,?,?,?,?,?,?)";
        try
        {

            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,customer.getUsername());
            preparedStatement.setLong(2,customer.getAccount().getNumber());
            preparedStatement.setString(3,customer.getAccount().getType());
            preparedStatement.setString(4,transactionType);
            preparedStatement.setDouble(5,amount);
            preparedStatement.setDouble(6,customer.getAccount().getBalance());
            preparedStatement.setDouble(7,previousBalance);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            preparedStatement.setString(8,formatter.format(date));
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
                //System.out.println("Yes in update database");
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


