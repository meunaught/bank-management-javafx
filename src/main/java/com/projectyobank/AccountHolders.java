package com.projectyobank;

import com.projectyobank.controllers.SqliteController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class AccountHolders extends Users implements UserMethods{

    private double Balance;
    private double main_Balance;
    private String accountType;
    private long accountNumber;
    public static String type_Of_Functionality ;
    private double withdrawAmount = 0;

    public void setBalance(double balance)
    {
        this.Balance = balance;
    }
    public double getBalance()
    {
        return this.Balance;
    }

    public void setAccountType(String type) { this.accountType = type; }
    public String getAccountType() { return this.accountType; }

    public void setAccountNumber(long accountNumber){ this.accountNumber = accountNumber; }
    public long getAccountNumber(){return this.accountNumber ; }

    public void setMain_Balance(double main_Balance){ this.main_Balance = main_Balance ;}
    public double getMain_Balance(){return this.main_Balance ;}

    public void setWithdrawAmount(double amount) {this.withdrawAmount = amount;}
    public double getWithdrawAmount(){return this.withdrawAmount;}


    @Override
    public void withdraw(double amount)
    {
        if(accountHolderObj.getBalance()<accountHolderObj.getWithdrawAmount())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Withdraw Money");
            alert.setHeaderText("");
            alert.setContentText("Not sufficient balance in your account!!!");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Withdraw Money");
            alert.setHeaderText("");
            alert.setContentText("TK: " + amount + " will be credited from your account\nDo you want to confirm?");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            if (alert.showAndWait().get() == ButtonType.OK) {
                Update_Database((accountHolderObj.getBalance()-amount),accountHolderObj.getMain_Balance());
            }
        }
    }


    @Override
    public void deposit(double amount) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deposit Money");
        alert.setHeaderText("");
        alert.setContentText("TK: " + amount + " will be debited from your account\nDo you want to confirm?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        if (alert.showAndWait().get() == ButtonType.OK) {
            Update_Database((accountHolderObj.getBalance()+amount),accountHolderObj.getMain_Balance() + amount);
        }
    }

    @Override
    public void askForLoan() {

    }

    @Override
    public void checkBalance() {

    }

    @Override
    public void getStatement() {

    }

    @Override
    public void fundTransfer() {

    }
    @Override
    public void createBalance(int Year,int Month,int Day,int Hour,int Minute,int Second)
    {

    }

    public double maxWithdraw()
    {
        return 1000000000000.0;
    }

    public int getProperDifference(int first_time,int second_time,int differ,ArrayList<Integer> arrayList,int index)
    {
        if(second_time<first_time)
        {
            arrayList.set(index,arrayList.get(index)-1);
            return differ + second_time - first_time;
        }
        else
        {
            return second_time-first_time;
        }
    }

    public int getProperDifference(int first_time,int second_time)
    {
        return second_time -first_time;
    }

    public double Count_Hours(Calendar calendar, ArrayList<Integer> arrayList,int Year,int Month,int Day,int Hour,int Minute,int Second)
    {
        try {
            arrayList.add(getProperDifference(Year, calendar.get(Calendar.YEAR)));
            arrayList.add(getProperDifference(Month, calendar.get(Calendar.MONTH), 12, arrayList, 0));
            arrayList.add(getProperDifference(Day, calendar.get(Calendar.DATE), 30, arrayList, 1));
            arrayList.add(getProperDifference(Hour, calendar.get(Calendar.HOUR_OF_DAY), 24, arrayList, 2));
            arrayList.add(getProperDifference(Minute, calendar.get(Calendar.MINUTE), 60, arrayList, 3));
            arrayList.add(getProperDifference(Second, calendar.get(Calendar.SECOND), 60, arrayList, 4));

            double passed_Hours = 0;
            passed_Hours += arrayList.get(0) * 365 * 24;
            passed_Hours += arrayList.get(1) * 30 * 24;
            passed_Hours += arrayList.get(2) * 24;
            passed_Hours += arrayList.get(3);
            passed_Hours += arrayList.get(4)/60;
            passed_Hours += arrayList.get(5)/3600;
            passed_Hours /= 2;

            return passed_Hours;
        }
        catch(Exception e)
        {
            System.out.println("Problem in counting hours");
            System.out.println(e);
            return 0;
        }
    }

    public void Update_Database(Calendar calendar)
    {
        try {
            Model_Sqlite.conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Model_Sqlite.conection = SqliteController.Connector();
        if (Model_Sqlite.conection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        String Query = "UPDATE Login_Info_For_Users SET Balance = ? ,Year = ? ,Month = ? ,Day = ? ,Hour = ? ,Minute = ? ,Second = ?," +
                "WithdrawAmount = ? " +
                "WHERE Username = ? and PassWord = ? and AccountType = ? and AccountNumber = ?";
        try{
            preparedStatement = Model_Sqlite.conection.prepareStatement(Query);
            preparedStatement.setDouble(1,accountHolderObj.getBalance());
            preparedStatement.setInt(2,calendar.get(Calendar.YEAR));
            preparedStatement.setInt(3,calendar.get(Calendar.MONTH));
            preparedStatement.setInt(4,calendar.get(Calendar.DATE));
            preparedStatement.setInt(5,calendar.get(Calendar.HOUR_OF_DAY));
            preparedStatement.setInt(6,calendar.get(Calendar.MINUTE));
            preparedStatement.setInt(7,calendar.get(Calendar.SECOND));
            preparedStatement.setDouble(8,0);
            preparedStatement.setString(9,accountHolderObj.getUsername());
            preparedStatement.setString(10,accountHolderObj.getPassword());
            preparedStatement.setString(11,accountHolderObj.getAccountType());
            preparedStatement.setLong(12,accountHolderObj.getAccountNumber());

            int a = preparedStatement.executeUpdate();
            System.out.println(a);
        }
        catch (SQLException e)
        {
            System.out.println("Problem in updating database after creating balance");
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        finally {
            try {
                preparedStatement.close();
                Model_Sqlite.conection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void Update_Database(double amount,double value_MainBalance)
    {
        try {
            Model_Sqlite.conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Model_Sqlite.conection = SqliteController.Connector();
        if (Model_Sqlite.conection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "SELECT WithdrawAmount FROM Login_Info_For_Users " +
                "WHERE Username = ? and PassWord = ? and AccountNumber = ? and AccountType = ?";
        try {
            preparedStatement = Model_Sqlite.conection.prepareStatement(Query);
            preparedStatement.setString(1,accountHolderObj.getUsername());
            preparedStatement.setString(2,accountHolderObj.getPassword());
            preparedStatement.setLong(3,accountHolderObj.getAccountNumber());
            preparedStatement.setString(4,accountHolderObj.getAccountType());

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                double temp = resultSet.getDouble("WithdrawAmount");
                if(temp + accountHolderObj.getWithdrawAmount() > accountHolderObj.maxWithdraw() && type_Of_Functionality.equals("Withdraw") )
                {
                    System.out.println("Beshi taka uthtese");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Withdraw Money");
                    alert.setHeaderText("");
                    alert.setContentText("You can't withdraw more than " + accountHolderObj.maxWithdraw() + " in 2 hours from " +
                            accountHolderObj.getAccountType() + " account!!!");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        return ;
                    }
                }
                else
                {
                    preparedStatement = null;
                    //resultSet = null;
                    Query = "UPDATE Login_Info_For_Users SET Balance = ? ,MainBalance = ? ,WithdrawAmount = ? WHERE Username = ? and " +
                            "PassWord = ?  and AccountNumber = ? and AccountType = ?";
                    try{
                        preparedStatement = Model_Sqlite.conection.prepareStatement(Query);
                        preparedStatement.setDouble(1,amount);
                        preparedStatement.setDouble(2,value_MainBalance);
                        preparedStatement.setDouble(3,temp + accountHolderObj.getWithdrawAmount());
                        preparedStatement.setString(4,accountHolderObj.getUsername());
                        preparedStatement.setString(5,accountHolderObj.getPassword());
                        preparedStatement.setLong(6,accountHolderObj.getAccountNumber());
                        preparedStatement.setString(7,accountHolderObj.getAccountType());

                        int a = preparedStatement.executeUpdate();
                        System.out.println(a);
                    }
                    catch(SQLException e)
                    {
                        System.out.println("Cannot Update data in withdraw");
                        System.out.println(e);
                    }

                }
            }
            else
            {
                System.out.println("Cannot Load data in withdraw");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally {
            try {
                preparedStatement.close();
                assert resultSet != null;
                resultSet.close();
                Model_Sqlite.conection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
