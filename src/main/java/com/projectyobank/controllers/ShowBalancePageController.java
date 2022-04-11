package com.projectyobank.controllers;

import com.projectyobank.database.dbcontroller;
import com.projectyobank.models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ShowBalancePageController extends Controller{
    @FXML
    private Button previousButton;
    @FXML
    private Button balanceButton;
    @FXML
    private TextField accountNumberField;
    @FXML
    private TextField balanceField;
    @FXML
    private Label ErrorShowLabel;

    public void previous(ActionEvent e)
    {
        giveFilename("view/options.fxml",e);
    }

    public void Get_Balance(ActionEvent e)
    {
        if(dbcontroller.getInstance().Verify_Account(Integer.parseInt(accountNumberField.getText())))
        {
            ErrorShowLabel.setText("");
            Account account = dbcontroller.getInstance().getCustomer().getAccount();
            account.createBalance();
            balanceField.setText(String.valueOf(account.getBalance()));
        }
        else
        {
            ErrorShowLabel.setText("Account number is not valid!!!");
        }
    }
}
