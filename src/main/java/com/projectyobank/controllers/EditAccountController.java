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

public class EditAccountController extends Controller implements Initializable{

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;

    public void editCustomerButtonClick(ActionEvent e)
    {
        AlertGenerator alertGenerator = new AlertGenerator();
        try
        {
            String username = this.username.getText();
            String address = this.address.getText();
            String email = this.email.getText();
            long phone = Integer.parseInt(this.phone.getText());
            if(username.isEmpty()|| address.isEmpty()||email.isEmpty())
            {
                alertGenerator.showErrorAlert("Edit Customer","Every box has to be filled ");
            }
            else
            {
                if(dbcontroller.getInstance().editCustomer(username,address,email,phone))
                {
                    alertGenerator.showInformationAlert("Edit Customer","Information updated successfully.");
                }
            }
        }
        catch(NumberFormatException exception)
        {
            alertGenerator.showErrorAlert("Edit Customer","Phone has to be number");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        username.setText(dbcontroller.getInstance().getBanker().getCustomer().getUsername());
        address.setText(dbcontroller.getInstance().getBanker().getCustomer().getAddress());
        email.setText(dbcontroller.getInstance().getBanker().getCustomer().getEmail());
        phone.setText(String.valueOf(dbcontroller.getInstance().getBanker().getCustomer().getPhone()));
    }

}
