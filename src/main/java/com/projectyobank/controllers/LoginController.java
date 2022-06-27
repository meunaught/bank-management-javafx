package com.projectyobank.controllers;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import com.projectyobank.database.dbcontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginController extends Controller{
    @FXML
    private JFXButton LoginButton;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;


    public void handleLoginButtonClick(ActionEvent e) {
//        System.out.println("login method e dhuksee");
        if(dbcontroller.getInstance().Verify_User_Login(usernameField.getText(), passwordField.getText())) {
            try {
//                System.out.println("switchToScene er agei asi");
                switchToScene("view/BankerPage.fxml", e);
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        else {
            usernameField.getStyleClass().add("wrong-credentials");
            passwordField.getStyleClass().add("wrong-credentials");
        }
    }
}
