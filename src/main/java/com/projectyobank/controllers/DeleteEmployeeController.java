package com.projectyobank.controllers;

import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class DeleteEmployeeController extends BankerPageController{
    @FXML
    private JFXTextField username;

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
