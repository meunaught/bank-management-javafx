package com.projectyobank.controllers;

import com.projectyobank.database.dbcontroller;
import com.projectyobank.models.Banker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController extends Controller implements Initializable {
    @FXML
    private Label adminname;
    @FXML
    private Label adminid;

    private Banker banker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("initialize e dhukse");
        banker = dbcontroller.getInstance().getBanker();
        adminname.setText(banker.getUsername());
        adminid.setText(banker.getDesignation());
    }

    public void logout(ActionEvent  e)
    {
        giveFilename("view/LOGINPAGE.fxml",e);
    }

    public void operations(ActionEvent e)
    {
        giveFilename("view/options.fxml",e);
    }
}
