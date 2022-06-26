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

public class WithdrawPageController extends Controller implements Initializable {
    @FXML
    private JFXTextField AccountNumberField;
    @FXML
    private JFXTextField WithdrawAmountField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void withdrawButtonClick(ActionEvent e)
    {
        try {
            long accountNumber = Integer.parseInt(AccountNumberField.getText());
            double amount = Double.parseDouble(WithdrawAmountField.getText());
            if(dbcontroller.getInstance().Verify_Account(accountNumber))
            {
                Customer customer = dbcontroller.getInstance().getCustomer();
                customer.getAccount().getTransaction().withdraw(amount,customer);
            }
            else
            {
                AccountNumberField.getStyleClass().add("wrong-credentials");
                AlertGenerator alertGenerator =new AlertGenerator();
                alertGenerator.showErrorAlert("Withdraw Money","Your account number is not valid!");
            }
        }
        catch(NumberFormatException exception)
        {
            AccountNumberField.getStyleClass().add("wrong-credentials");
            WithdrawAmountField.getStyleClass().add("wrong-credentials");
            AlertGenerator alertGenerator = new AlertGenerator();
            alertGenerator.showErrorAlert("Withdraw Money","Please enter appropriate values.");
        }
    }

    public void previousButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/transaction.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
