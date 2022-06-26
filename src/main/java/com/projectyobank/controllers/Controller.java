package com.projectyobank.controllers;

import com.projectyobank.Main;
import com.projectyobank.database.dbcontroller;
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
    public void giveFilename(String filename,ActionEvent e)
    {
        try {
            switchToScene(filename,e);
        }
        catch (IOException ex) {
            System.out.println(filename);
            ex.printStackTrace();
        }
    }

    public void logoutButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/login.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void addAccountButtonClick(ActionEvent e)
    {

    }

    public void transactionButtonClick(ActionEvent e)
    {
        try{
            switchToScene("view/transaction.fxml",e);
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
