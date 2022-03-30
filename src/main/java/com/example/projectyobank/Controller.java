package com.example.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.projectyobank.Model_Sqlite.conection;
import static com.example.projectyobank.Users.accountHolderObj;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public boolean Verify_User_Login(String Username,String Password)
    {
        /*try {
            conection.close();
        } catch (SQLException e) {
            System.out.println("Aihai");
            System.out.println(e);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println("Aihai");
            System.out.println(e);
            e.printStackTrace();
        }*/
        conection = SqliteController.Connector();
        if (conection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from Login_Info_For_Users where Username = ? and PassWord = ?";

        try {
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,Username);
            preparedStatement.setString(2,Password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
            else
            {
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
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void switchToScene(String FileName,ActionEvent e) throws IOException
    {
        root  = FXMLLoader.load(getClass().getResource(FileName));
        stage  = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene =  new Scene(root, Color.DEEPSKYBLUE);
        stage.setScene(scene);
        stage.show();
    }

    public boolean Verify_Account_Number(String accountNumber)
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
        String Query = "SELECT Balance, MainBalance, Year, Month, Day, Hour, Minute, Second,  Username, PassWord, AccountNumber, AccountType" +
                " FROM Login_Info_For_Users WHERE Username = ? and PassWord = ? and AccountNumber = ? and AccountType = ?";
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,accountHolderObj.getUsername());
            preparedStatement.setString(2,accountHolderObj.getPassword());
            preparedStatement.setInt(3,Integer.parseInt(accountNumber));
            preparedStatement.setString(4,accountHolderObj.getAccountType());

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                accountHolderObj.setBalance(resultSet.getDouble("Balance"));
                accountHolderObj.setMain_Balance(resultSet.getDouble("MainBalance"));
                accountHolderObj.setAccountNumber(Integer.parseInt(accountNumber));
                accountHolderObj.createBalance(resultSet.getInt("Year"),resultSet.getInt("Month"),
                        resultSet.getInt("Day"),resultSet.getInt("Hour"),resultSet.getInt("Minute"),
                        resultSet.getInt("Second"));

                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                conection.close();
                System.out.println("Yes2");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void giveFilename(String filename,ActionEvent e)
    {
        try {
            switchToScene(filename,e);
        } catch (IOException ex) {
            System.out.println(filename);
            ex.printStackTrace();
        }
    }
}
