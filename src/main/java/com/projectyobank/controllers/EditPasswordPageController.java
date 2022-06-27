package com.projectyobank.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditPasswordPageController extends Controller implements Initializable {
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField currentPassword;
    @FXML
    private JFXPasswordField newPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void changePasswordButtonClick(ActionEvent e)
    {
        AlertGenerator alertGenerator = new AlertGenerator();
        String username = this.username.getText();
        String currentPassword = this.currentPassword.getText();
        String newPassword  = this.newPassword.getText();
        if(username.isEmpty() || currentPassword.isEmpty() || newPassword.isEmpty())
        {
            alertGenerator.showErrorAlert("Change Password","Fill out all the fields.");
        }
        else
        {
            if(dbcontroller.getInstance().Verify_Employee(username,currentPassword))
            {
                dbcontroller.getInstance().editEmployee(username,newPassword,"UPDATE admin_login SET PassWord = ? WHERE Username = ?");
                alertGenerator.showInformationAlert("Change Password","Password changed successfully.");
            }
            else
            {
                alertGenerator.showErrorAlert("Change Password","Credentials not matched.");
            }
        }
    }

    public void previousButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/BankerPage.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
