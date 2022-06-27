package com.projectyobank.controllers;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Edit_DeleteAccountController extends Controller implements Initializable{
    @FXML
    private JFXTextField AccountNumberField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void editAccount(ActionEvent e)
    {
        AlertGenerator alertGenerator = new AlertGenerator();
        try {
            long accountNumber = Integer.parseInt(AccountNumberField.getText());
            if(dbcontroller.getInstance().Verify_Account(accountNumber))
            {
                switchToScene("view/editCustomer.fxml",e);
            }
            else
            {
                alertGenerator.showErrorAlert("Edit Account","Your account number is not valid");
            }
        }
        catch(NumberFormatException exception)
        {
            alertGenerator.showErrorAlert("Edit Account","Account Number has to be a number");
            System.out.println(exception.getMessage());
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteAccount(ActionEvent e)
    {
        AlertGenerator alertGenerator = new AlertGenerator();
        try{
            long accountNumber = Integer.parseInt(AccountNumberField.getText());
            if(dbcontroller.getInstance().Verify_Account(accountNumber))
            {
                if(alertGenerator.showConfirmationAlert("Delete Account","Are you sure to delete the account?"))
                {
                    dbcontroller.getInstance().deleteAccount(accountNumber);
                }
            }
            else
            {
                alertGenerator.showErrorAlert("Delete Account","Your account number is not valid");
            }
        }
        catch(NumberFormatException exception)
        {
            alertGenerator.showErrorAlert("Delete Account","Account Number has to be a number");
            System.out.println(exception.getMessage());
        }
    }

    public void previousButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/BankerPage.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println( exception.getMessage());
        }
    }
}
