package com.projectyobank.controllers;

import com.projectyobank.database.dbcontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends Controller{
    @FXML
    private Button LoginButton;
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label ErrorShowLabel;

    public void Login(ActionEvent e) {
//        System.out.println("login method e dhuksee");
        if(dbcontroller.getInstance().Verify_User_Login(UsernameField.getText(), passwordField.getText())) {
            try {
//                System.out.println("switchToScene er agei asi");
                switchToScene("view/AdminPage.fxml", e);
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        else {
            ErrorShowLabel.setText("Invalid Username or Password");

        }
    }

}
