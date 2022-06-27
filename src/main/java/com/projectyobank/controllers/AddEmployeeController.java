package com.projectyobank.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

public class AddEmployeeController extends BankerPageController{
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField designation;

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

    public void previousButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/BankerPage.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println( exception.getMessage());
        }
    }

}
