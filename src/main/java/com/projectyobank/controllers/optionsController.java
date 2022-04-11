package com.projectyobank.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class optionsController extends Controller{
    @FXML
    private Button previousButton;
    @FXML
    private Button checkBalanceButton;

    public void previous(ActionEvent e)
    {
        giveFilename("view/AdminPage.fxml",e);
    }

    public void checkBalance(ActionEvent e)
    {
        giveFilename("view/ShowBalance.fxml",e);
    }
}
