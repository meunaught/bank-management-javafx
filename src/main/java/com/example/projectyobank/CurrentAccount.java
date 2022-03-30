package com.example.projectyobank;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.projectyobank.Model_Sqlite.conection;

public class CurrentAccount extends AccountHolders{

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    @Override
    public void createBalance(int Year,int Month,int Day,int Hour,int Minute,int Second)
    {
        try {
            Calendar calendar = Calendar.getInstance();
            double passed_Hours = accountHolderObj.Count_Hours(calendar,arrayList,Year,Month,Day,Hour,Minute,Second);

            if(passed_Hours>=1) {
                accountHolderObj.setBalance(accountHolderObj.getBalance() + (accountHolderObj.getMain_Balance() * currentAccount_IR * passed_Hours) / 100);
                accountHolderObj.Update_Database(calendar);
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("ArrayList e problem");
            System.out.println(e);
        }
    }

    public void Update_Database(int amount,double value)
    {
        try {
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conection = SqliteController.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "SELECT NumberOfWithdraw FROM Login_Info_For_Users " +
                "WHERE Username = ? and PassWord = ? and AccountNumber = ? and AccountType = ?";
        try {
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,accountHolderObj.getUsername());
            preparedStatement.setString(2,accountHolderObj.getPassword());
            preparedStatement.setLong(3,accountHolderObj.getAccountNumber());
            preparedStatement.setString(4,accountHolderObj.getAccountType());

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                int temp = resultSet.getInt("NumberOfWithdraw");
                temp++;
                preparedStatement = null;
                resultSet = null;
                Query = "UPDATE Login_Info_For_Users SET Balance = ? ,MainBalance = ? ,NumberOfWithDraw = ? WHERE Username = ? and " +
                        "PassWord = ?  and AccountNumber = ? and AccountType = ?";
                try{
                    preparedStatement = conection.prepareStatement(Query);
                    preparedStatement.setDouble(1,accountHolderObj.getBalance() - amount);
                    preparedStatement.setDouble(2,value);
                    preparedStatement.setInt(3,temp);
                    preparedStatement.setString(4,accountHolderObj.getUsername());
                    preparedStatement.setString(5,accountHolderObj.getPassword());
                    preparedStatement.setLong(6,accountHolderObj.getAccountNumber());
                    preparedStatement.setString(7,accountHolderObj.getAccountType());

                    int a = preparedStatement.executeUpdate();
                    System.out.println(a);
                }
                catch(SQLException e)
                {
                    System.out.println("Cannot Update data in wthdraw");
                    System.out.println(e);
                }
            }
            else
            {
                System.out.println("Cannot Load data in withdraw");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                conection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void withdraw(int amount)
    {
        if(accountHolderObj.getBalance()<amount)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Withdraw Money");
            alert.setHeaderText("");
            alert.setContentText("Not sufficient balance in your account!!!");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else if(accountHolderObj.getMain_Balance()>(accountHolderObj.getBalance()-amount))
        {
            ButtonType type = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Withdraw Money");
            alert.setHeaderText("");
            alert.setContentText("TK: " + amount + " will be withdrawn from your account and your main balance will become Tk: "
                    + (accountHolderObj.getBalance() - amount) +  "\nDo you want to confirm that withdrawal ?");
            alert.getDialogPane().getButtonTypes().add(type);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            if (alert.showAndWait().get() == ButtonType.OK) {
                Update_Database(amount,accountHolderObj.getBalance()-amount);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Withdraw Money");
            alert.setHeaderText("");
            alert.setContentText("TK: " + amount + " will be withdrawn from your account\nDo you want to confirm that withdrawal ?");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            if (alert.showAndWait().get() == ButtonType.OK) {
                Update_Database(amount,accountHolderObj.getMain_Balance());
            }
        }
    }
}
