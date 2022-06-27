package com.projectyobank.controllers;

import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.models.Customer;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DepositPageController extends Controller implements Initializable{
    @FXML
    private JFXTextField AccountNumberField;
    @FXML
    private JFXTextField WithdrawAmountField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void depositButtonClick(ActionEvent e)
    {
        try {
            long accountNumber = Integer.parseInt(AccountNumberField.getText());
            double amount = Double.parseDouble(WithdrawAmountField.getText());
            if(dbcontroller.getInstance().Verify_Account(accountNumber))
            {
                Customer customer = dbcontroller.getInstance().getBanker().getCustomer();
                customer.getAccount().getTransaction().deposit(amount,customer);
            }
            else
            {
                AccountNumberField.getStyleClass().add("wrong-credentials");
                AlertGenerator alertGenerator =new AlertGenerator();
                alertGenerator.showErrorAlert("Deposit Money","Your account number is not valid!");
            }
        }
        catch(NumberFormatException exception)
        {
            AccountNumberField.getStyleClass().add("wrong-credentials");
            WithdrawAmountField.getStyleClass().add("wrong-credentials");
            AlertGenerator alertGenerator = new AlertGenerator();
            alertGenerator.showErrorAlert("Deposit Money","Please enter appropriate values.");
        }
    }

}
