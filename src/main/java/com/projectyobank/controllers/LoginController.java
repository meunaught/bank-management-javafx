package com.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController extends Controller{

    @FXML
    private Button LoginButton;
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField  passwordField;
    @FXML
    private Label ErrorShowLabel;
    @FXML
    private RadioButton UserRadioButton;
    @FXML
    private RadioButton AdminRadioButton;

    public void Login(ActionEvent e)
    {
        if(UserRadioButton.isSelected()) {

            if(Verify_User_Login(UsernameField.getText(),passwordField.getText()))
            {
                Users.userobj = new AccountHolders();
                Users.userobj.setUsername(UsernameField.getText());
                Users.userobj.setPassword(passwordField.getText());
                try {
                    switchToScene("options.fxml", e);
                } catch (IOException exception) {
                    System.out.println("Cannot Open options.fxml");
                    System.out.println(exception);
                }
            }
            else
            {
                ErrorShowLabel.setText("Invalid Username or Password");
            }
        }
    }
}
