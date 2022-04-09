package com.projectyobank.controllers;

import com.projectyobank.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class WithdrawPageController extends Controller{
    @FXML
    private TextField WithdrawAmountField;
    @FXML
    private Button WithdrawButton;
    @FXML
    private Button PreviousButton;

    public void Withdraw_Money(ActionEvent e)
    {
        Users.accountHolderObj.setWithdrawAmount(Double.parseDouble(WithdrawAmountField.getText()));
        Users.accountHolderObj.withdraw(Double.parseDouble(WithdrawAmountField.getText()));
    }

    public void Previous(ActionEvent e)
    {
        giveFilename("EnterAccountNumber.fxml",e);
    }
}
