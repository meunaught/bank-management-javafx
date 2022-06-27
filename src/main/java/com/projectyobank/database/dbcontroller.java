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
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Connection conection;
    public dbcontroller () {
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
        }
    }


    public void connectDatabase()
    {
        try {
            conection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        conection = dbcontroller.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
        }
    }

    public boolean Verify_User_Login(String Username,String Password)  {
        connectDatabase();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from admin_login where Username = ? and PassWord = ?";

        try {
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,Username);
            preparedStatement.setString(2,Password);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String temp = resultSet.getString("Designation");
                if(temp.compareToIgnoreCase("Manager") == 0)
                {
                    System.out.println("Manager");
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

        }
        catch(SQLException | NullPointerException exception)
        {
            System.out.println(exception.getMessage());
            return false;
        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                conection.close();
            }
            catch(SQLException | NullPointerException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }

    public boolean Verify_Employee(String Username)  {
        connectDatabase();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from admin_login where Username = ?";

        try {
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,Username);
            resultSet = preparedStatement.executeQuery();
            System.out.println("verify te dhukse");

            if(resultSet.next()) {
                return true;
            }
            return false;

        } catch (SQLException | NullPointerException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }

    public boolean Verify_Employee(String username,String password)  {
        connectDatabase();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from admin_login where Username = ? and PassWord = ?";

        try {
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            System.out.println("verify te dhukse");

            if(resultSet.next()) {
                return true;
            }
            return false;

        } catch (SQLException | NullPointerException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                conection.close();
                System.out.println("Yes in verify user login");
            }
            catch (SQLException | NullPointerException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }



    public boolean Verify_Account(long account_number)
    {
        connectDatabase();
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

        } catch (SQLException | NullPointerException exception) {
            System.out.println(exception.getMessage());;
            return false;
        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                conection.close();
                System.out.println("Yes in  verify account");
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void SetProperties(Account account)
    {
        connectDatabase();
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
        catch (SQLException | NullPointerException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void Update_Account(Account account)
    {
        connectDatabase();
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
            preparedStatement.executeUpdate();

        }
        catch (SQLException | NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void editEmployee(String username,String update,String Query)
    {
        connectDatabase();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,update);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }


    public void addCustomer(String username,String accountType,long accountNumber,double mainBalance,double balance,String email,long phone,String address)
    {
        connectDatabase();
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
        catch(SQLException | NullPointerException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void deleteAccount(long accountNumber)
    {
        connectDatabase();
        PreparedStatement preparedStatement = null;
        String Query = "DELETE FROM Login_info_For_Users WHERE AccountNumber = ?";
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setLong(1,accountNumber);
            preparedStatement.executeUpdate();
        }
        catch(SQLException | NullPointerException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void addEmployee(String username,String password,String designation)
    {
        connectDatabase();
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
        catch(SQLException | NullPointerException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }

    }

    public boolean editCustomer(String username,String address,String email,long phone)
    {
        connectDatabase();
        PreparedStatement preparedStatement = null;
        String Query = "UPDATE Login_Info_For_Users SET Username = ? ,Email = ? ,Address = ?,phone = ? WHERE" +
                " AccountNumber = ?";
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,address);
            preparedStatement.setLong(4,phone);
            preparedStatement.setLong(5,dbcontroller.getInstance().getCustomer().getAccount().getNumber());
            int a = preparedStatement.executeUpdate();
            if(a == 1)
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        catch (SQLException | NullPointerException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void deleteEmployee(String username)
    {
        connectDatabase();
        PreparedStatement preparedStatement = null;
        String Query = "DELETE FROM admin_login WHERE Username = ?";
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,username);
            preparedStatement.executeUpdate();
        }
        catch(SQLException | NullPointerException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }


    public void addTransaction(double previousBalance,double amount,String transactionType,Customer customer)
    {
        connectDatabase();
        PreparedStatement preparedStatement = null;
        String Query = "INSERT INTO trans(Username,AccountNumber,AccountType,TransactionType,TransactionAmount,CurrentBalance,PreviousBalance,Date) " +
                "VALUES(?,?,?,?,?,?,?,?)";
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
        catch(SQLException | NullPointerException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void limitsManager(String rate,String hour,String maxWithdraw,String minAmount,String Query,String type)
    {
        connectDatabase();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setDouble(1,Double.parseDouble(rate));
            preparedStatement.setDouble(2,Double.parseDouble(hour));
            preparedStatement.setDouble(3,Double.parseDouble(maxWithdraw));
            preparedStatement.setDouble(4,Double.parseDouble(minAmount));
            preparedStatement.setString(5,type);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                preparedStatement.close();
                conection.close();
            }
            catch (SQLException | NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}


