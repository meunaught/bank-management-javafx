package com.projectyobank.controllers;

import com.projectyobank.database.dbcontroller;
import com.projectyobank.models.Banker;
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
}
