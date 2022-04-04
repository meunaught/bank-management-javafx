package com.example.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.example.projectyobank.Users.accountHolderObj;

public class WithdrawPageController extends Controller{
    @FXML
    private TextField WithdrawAmountField;
    @FXML
    private Button WithdrawButton;
    @FXML
    private Button PreviousButton;

    public void Withdraw_Money(ActionEvent e)
    {
        accountHolderObj.setWithdrawAmount(Double.parseDouble(WithdrawAmountField.getText()));
        accountHolderObj.withdraw(Double.parseDouble(WithdrawAmountField.getText()));
    }

    public void Previous(ActionEvent e)
    {
        giveFilename("EnterAccountNumber.fxml",e);
    }
}
