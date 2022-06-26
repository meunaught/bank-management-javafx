package com.projectyobank.controllers;

import com.projectyobank.database.dbcontroller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BankerPageController extends Controller implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void transactionButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/transaction.fxml",e);
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void addAccountButtonClick(ActionEvent e)
    {

    }


}
