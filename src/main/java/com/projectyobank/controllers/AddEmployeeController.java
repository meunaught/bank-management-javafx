package com.projectyobank.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController extends Controller implements Initializable {
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField designation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void addButtonClick(ActionEvent e)
    {
        AlertGenerator alertGenerator = new AlertGenerator();
        String username = this.username.getText();
        String password = this.password.getText();
        String designation = this.designation.getText();
        if(username.isEmpty() || password.isEmpty() || designation.isEmpty())
        {
            alertGenerator.showErrorAlert("Add Employee","Fill all the fields.");
        }
        else {
            if (designation.compareToIgnoreCase("manager") == 0 || designation.compareToIgnoreCase("Senior_Officer") == 0
                    || designation.compareToIgnoreCase("Junior_Officer") == 0) {
                if (!dbcontroller.getInstance().Verify_Employee(username)) {
                    dbcontroller.getInstance().addEmployee(username, password, designation);
                    alertGenerator.showInformationAlert("Add Employee", "Employee added successfully.");
                } else {
                    alertGenerator.showErrorAlert("Add Employee", "Username already taken.");
                }
            }
        }
    }
}
