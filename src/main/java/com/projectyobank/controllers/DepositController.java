package com.projectyobank.controllers;

import com.projectyobank.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DepositController extends  Controller{
    @FXML
    private TextField depositfield;
    @FXML
    private Button depositButton;
    @FXML
    private Button prevButton;
    public void DepositMoney(ActionEvent e) {
        Users.accountHolderObj.deposit(Double.parseDouble(depositfield.getText()));
    }
    public void previous(ActionEvent e) {
        try{
            switchToScene("EnterAccountNumber.fxml", e);
        }
        catch (IOException exception) {
            System.out.println(exception);
        }
    }
}
