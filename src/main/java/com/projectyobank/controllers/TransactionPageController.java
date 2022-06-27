package com.projectyobank.controllers;

import com.projectyobank.database.dbcontroller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactionPageController extends Controller implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void withdrawButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/Withdraw.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void depositButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/Deposit.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void transferfundsButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/TransferFunds.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

}
