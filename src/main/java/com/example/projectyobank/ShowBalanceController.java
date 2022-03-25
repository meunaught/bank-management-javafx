package com.example.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static com.example.projectyobank.Users.accountHolderObj;

public class ShowBalanceController extends Controller{
    @FXML
    private Label Balance;
    @FXML
    private Button Balance_Button;

    public void Get_Balance(ActionEvent e)
    {
        Balance.setText(String.valueOf(accountHolderObj.getBalance()));
    }

}
