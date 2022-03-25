package com.example.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static com.example.projectyobank.Users.userobj;

public class Scene2Controller extends  Controller{
    @FXML
    private Button LogoutButton;
    @FXML
    private Button AccountButton;
    @FXML
    private Button Edit_Button;

    public void Logout(ActionEvent e)
    {
        try {
            switchToScene("LOGINPAGE.fxml", e);
        }
        catch(IOException exception)
        {
            System.out.println("Cannot logout");
        }
    }

    public void ChooseAccountType(ActionEvent e)
    {
        try{
            switchToScene("TypeOfAccounts.fxml",e);
        }
        catch(Exception exception)
        {
            System.out.println("Cannot Go to type of accounts fxml file");
            System.out.println(exception);
        }
    }

}
