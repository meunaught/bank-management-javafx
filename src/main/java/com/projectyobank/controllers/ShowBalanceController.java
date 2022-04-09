package com.projectyobank.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static com.projectyobank.Users.accountHolderObj;

public class ShowBalanceController extends Controller{
    @FXML
    private Label Balance;
    @FXML
    private Button Balance_Button;
    @FXML
    private Button Previous;

    public void Get_Balance(ActionEvent e)
    {
        Balance.setText(String.valueOf(accountHolderObj.getBalance()));
    }

    public void Go_Back(ActionEvent e)
    {
        try{
            switchToScene("EnterAccountNumber.fxml",e);
        }
        catch(Exception exception)
        {
            System.out.println(e);
            System.out.println("Previous Button of Check Balance Not Working");
        }
    }
}
