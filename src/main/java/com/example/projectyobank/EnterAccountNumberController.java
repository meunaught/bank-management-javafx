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

import static com.example.projectyobank.AccountHolders.type_Of_Functionality;
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

    public void Verify(ActionEvent e) {
        if (Verify_Account_Number(AccountNumberField.getText())) {
            if(type_Of_Functionality.equals("Check Balance"))
            {
                giveFilename("ShowBalance.fxml",e);
            }
            else if(type_Of_Functionality.equals("Withdraw"))
            {
                giveFilename("WithdrawPage.fxml",e);
            }
            else if(type_Of_Functionality.equals("Deposit"))
            {
                giveFilename("DepositPage.fxml",e);
            }
            else if(type_Of_Functionality.equals("FundTransfer"))
            {
                giveFilename("FundTransferPage.fxml",e);
            }
        }
        else {
            ErrorShowLabel.setText("Invalid Account Number!!!");
        }
    }

    public void Go_Back(ActionEvent e)
    {
        try {
            switchToScene("TypeOfAccounts.fxml",e);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Cannot go back to TypeOfAccounts.fxml from EnterAccountNumberController");
        }
    }
}
