package com.example.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

import static com.example.projectyobank.Model_Sqlite.conection;
import static com.example.projectyobank.Users.accountHolderObj;
import static com.example.projectyobank.Users.userobj;

public class EnterAccountNumberController extends Controller{
    @FXML
    private PasswordField AccountNumberField;
    @FXML
    private Button VerifyButton;
    @FXML
    private Button Previous;
    @FXML
    private Label ErrorShowLabel;

    public boolean Verify_Account_Number(String accountNumber)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "SELECT Balance, Username, PassWord, AccountNumber, AccountType FROM Login_Info_For_Users WHERE Username = ? and PassWord = ? and AccountNumber = ? and AccountType = ?";
        try{
            preparedStatement = conection.prepareStatement(Query);
            preparedStatement.setString(1,accountHolderObj.getUsername());
            preparedStatement.setString(2,accountHolderObj.getPassword());
            preparedStatement.setInt(3,Integer.parseInt(AccountNumberField.getText()));
            preparedStatement.setString(4,accountHolderObj.getAccountType());

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                accountHolderObj.setBalance(resultSet.getDouble("Balance"));
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
    }

    public void ShowBalance(ActionEvent e) {
        if (Verify_Account_Number(AccountNumberField.getText())) {
            try {
                switchToScene("ShowBalance.fxml",e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            ErrorShowLabel.setText("Invalid Account Number!!!");
        }
    }

}
