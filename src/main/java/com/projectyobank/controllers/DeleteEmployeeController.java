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

public class DeleteEmployeeController extends Controller implements Initializable {
    @FXML
    private JFXTextField username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void deleteButtonClick(ActionEvent e)
    {
        AlertGenerator alertGenerator  = new AlertGenerator();
        String username = this.username.getText();
        if(username.isEmpty())
        {
            alertGenerator.showErrorAlert("Delete Employee","Enter username of employee.");
        }
        else
        {
            if( dbcontroller.getInstance().Verify_Employee(username))
            {
                if(alertGenerator.showConfirmationAlert("Delete Employee","Do you want to remove employee?"))
                {
                    dbcontroller.getInstance().deleteEmployee(username);
                    alertGenerator.showInformationAlert("Delete Employee","Employee deleted successfully.");
                }
            }
            else
            {
                alertGenerator.showErrorAlert("Delete Employee","No employee matched.");
            }
        }
    }

}
