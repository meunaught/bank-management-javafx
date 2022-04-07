package com.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        Model_Sqlite.conection = SqliteController.Connector();
        if (Model_Sqlite.conection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from Login_Info_For_Users where Username = ? and PassWord = ?";

        try {
            preparedStatement = Model_Sqlite.conection.prepareStatement(Query);
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
                Model_Sqlite.conection.close();
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
        String Query = "SELECT Balance, MainBalance, Year, Month, Day, Hour, Minute, Second,  Username, PassWord, AccountNumber, AccountType" +
                " FROM Login_Info_For_Users WHERE Username = ? and PassWord = ? and AccountNumber = ? and AccountType = ?";
        try{
            preparedStatement = Model_Sqlite.conection.prepareStatement(Query);
            preparedStatement.setString(1, Users.accountHolderObj.getUsername());
            preparedStatement.setString(2, Users.accountHolderObj.getPassword());
            preparedStatement.setInt(3,Integer.parseInt(accountNumber));
            preparedStatement.setString(4, Users.accountHolderObj.getAccountType());

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Users.accountHolderObj.setBalance(resultSet.getDouble("Balance"));
                Users.accountHolderObj.setMain_Balance(resultSet.getDouble("MainBalance"));
                Users.accountHolderObj.setAccountNumber(Integer.parseInt(accountNumber));
                Users.accountHolderObj.createBalance(resultSet.getInt("Year"),resultSet.getInt("Month"),
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
                Model_Sqlite.conection.close();
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
        }
        catch (IOException ex) {
            System.out.println(filename);
            ex.printStackTrace();
        }
    }
}
