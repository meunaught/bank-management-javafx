package com.projectyobank.controllers;

import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditEmployeeController extends Controller implements Initializable {
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField designation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void updateButtonClick(ActionEvent e)
    {
        AlertGenerator alertGenerator  = new AlertGenerator();
        String username = this.username.getText();
        String designation = this.designation.getText();
        if(username.isEmpty() || designation.isEmpty())
        {
            alertGenerator.showErrorAlert("Edit Employee","Fill out all the fields.");
        }
        else
        {
            if( dbcontroller.getInstance().Verify_Employee(username))
            {
                    dbcontroller.getInstance().editEmployee(username,designation,"UPDATE admin_login SET Designation = ? WHERE Username = ?");
                    alertGenerator.showInformationAlert("Edit Employee","Employee edited successfully.");
            }
            else
            {
                alertGenerator.showErrorAlert("Edit Employee","No employee matched.");
            }
        }
    }

}
