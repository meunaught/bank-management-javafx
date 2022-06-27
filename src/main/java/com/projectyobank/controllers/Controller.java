package com.projectyobank.controllers;

import com.projectyobank.Main;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label BankerName;
    @FXML
    Label Designation;

    public void switchToScene(String FileName, ActionEvent e) throws IOException
    {
        root  = FXMLLoader.load(Main.class.getResource(FileName));
        stage  = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene =  new Scene(root, Color.DEEPSKYBLUE);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutButtonClick(ActionEvent e)
    {
        AlertGenerator alertGenerator = new AlertGenerator();
        if(alertGenerator.showConfirmationAlert("Logout","Do you want to logout?")) {
            try {
                switchToScene("view/login.fxml", e);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void transactionButtonClick(ActionEvent e)
    {
        if(dbcontroller.getInstance().getBanker().makeTransaction()) {
            try {
                switchToScene("view/transaction.fxml", e);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void addAccountButtonClick(ActionEvent e)
    {
        if(dbcontroller.getInstance().getBanker().add_Customer())
        {
            try{
                switchToScene("view/addCustomer.fxml",e);
            }
            catch(IOException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void editAccountButtonClick(ActionEvent e)
    {
        if(dbcontroller.getInstance().getBanker().edit_Customer())
        {
            try{
                switchToScene("view/Edit_DeleteAccount.fxml",e);
            }
            catch(IOException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void deleteAccountButtonClick(ActionEvent e)
    {
        if (dbcontroller.getInstance().getBanker().delete_Customer()) {
            try {
                switchToScene("view/Edit_DeleteAccount.fxml", e);
            }
            catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void addEmployeeButtonClick(ActionEvent e)
    {
        if(dbcontroller.getInstance().getBanker().add_Employee())
        {
            try{
                switchToScene("view/addEmployee.fxml",e);
            }
            catch(IOException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void editEmployeeButtonClick(ActionEvent e)
    {
        if(dbcontroller.getInstance().getBanker().edit_Employee())
        {
            try{
                switchToScene("view/editEmployee.fxml",e);
            }
            catch(IOException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void deleteEmployeeButtonClick(ActionEvent e)
    {
        if(dbcontroller.getInstance().getBanker().delete_Employee())
        {
            try{
                switchToScene("view/deleteEmployee.fxml",e);
            }
            catch(IOException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void editPasswordButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/editPassword.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void limitsManagerButtonClick(ActionEvent e)
    {
        if(dbcontroller.getInstance().getBanker().limitsManger()) {
            try {
                switchToScene("view/limitsManager.fxml", e);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void homeButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/Dashboard.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void statementButtonClick(ActionEvent e) {
        try{
            System.out.println("cdi");
            switchToScene("view/Statement.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
