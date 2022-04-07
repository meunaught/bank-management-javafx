package com.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;

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
        try {
            if (Verify_Account_Number(AccountNumberField.getText())) {
                if (AccountHolders.type_Of_Functionality.equals("Check Balance")) {
                    giveFilename("ShowBalance.fxml", e);
                } else if (AccountHolders.type_Of_Functionality.equals("Withdraw")) {
                    giveFilename("WithdrawPage.fxml", e);
                } else if (AccountHolders.type_Of_Functionality.equals("Deposit")) {
                    giveFilename("DepositPage.fxml", e);
                } else if (AccountHolders.type_Of_Functionality.equals("FundTransfer")) {
                    giveFilename("FundTransferPage.fxml", e);
                }
            } else {
                ErrorShowLabel.setText("Invalid Account Number!!!");
            }
        }
        catch(Exception exception)
        {
            System.out.println(exception);
            ErrorShowLabel.setText("Please Enter Your Account Number!");
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
