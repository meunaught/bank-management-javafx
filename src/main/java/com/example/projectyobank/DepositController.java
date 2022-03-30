package com.example.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.projectyobank.Users.accountHolderObj;

public class DepositController extends  Controller{
    @FXML
    private TextField depositfield;
    @FXML
    private Button depositButton;
    @FXML
    private Button prevButton;
    public void DepositMoney(ActionEvent e) {
        accountHolderObj.deposit(Integer.parseInt(depositfield.getText()));
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
