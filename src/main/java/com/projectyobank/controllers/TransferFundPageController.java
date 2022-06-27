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

public class TransferFundPageController extends Controller implements Initializable{
    @FXML
    private JFXTextField PayerAccountNumberField;
    @FXML
    private JFXTextField ReceiverAccountNumberField;
    @FXML
    private JFXTextField WithdrawAmountField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void transferfundsButtonClick(ActionEvent e)
    {
        try {
            long payerAccountNumber = Integer.parseInt(PayerAccountNumberField.getText());
            long receiverAccountNumber = Integer.parseInt(ReceiverAccountNumberField.getText());
            double amount = Double.parseDouble(WithdrawAmountField.getText());
            Customer payer;
            Customer receiver;
            if(dbcontroller.getInstance().Verify_Account(payerAccountNumber))
            {
                payer = dbcontroller.getInstance().getBanker().getCustomer();
                if(dbcontroller.getInstance().Verify_Account(receiverAccountNumber))
                {
                    receiver = dbcontroller.getInstance().getBanker().getCustomer();
                    receiver.getAccount().getTransaction().TransferMoney(amount,payer,receiver);
                }
                else
                {
                    ReceiverAccountNumberField.getStyleClass().add("wrong-credentials");
                    AlertGenerator alertGenerator =new AlertGenerator();
                    alertGenerator.showErrorAlert("TransferFunds","Receiver account number is not valid!");
                }
            }
            else
            {
                PayerAccountNumberField.getStyleClass().add("wrong-credentials");
                AlertGenerator alertGenerator =new AlertGenerator();
                alertGenerator.showErrorAlert("TransferFunds","Your account number is not valid!");
            }
        }
        catch(NumberFormatException exception)
        {
            PayerAccountNumberField.getStyleClass().add("wrong-credentials");
            ReceiverAccountNumberField.getStyleClass().add("wrong-credentials");
            WithdrawAmountField.getStyleClass().add("wrong-credentials");
            AlertGenerator alertGenerator = new AlertGenerator();
            alertGenerator.showErrorAlert("TransferFunds","Please enter appropriate values.");
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
