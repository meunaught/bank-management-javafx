package com.projectyobank.controllers;

import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.models.Account;
import com.projectyobank.models.Customer;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AddCustomerController extends Controller implements Initializable{
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField accountType;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField balance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
    }

    public void addCustomerButtonClick(ActionEvent e)
    {
        AlertGenerator alertGenerator = new AlertGenerator();
        try {
            String username = this.username.getText();
            String accountType = this.accountType.getText();
            String address = this.address.getText();
            String email = this.email.getText();
            long phone = Integer.parseInt(this.phone.getText());
            double balance = Double.parseDouble(this.balance.getText());
            if (username.isEmpty() || accountType.isEmpty() || address.isEmpty() || email.isEmpty()) {
                alertGenerator.showErrorAlert("New Customer","Every box has to be filled ");
            }
            else if(!(accountType.equals("Current")|| accountType.equals("Savings") || accountType.equals("Islamic")
            || accountType.equals("FixedDeposit")||accountType.equals("CreditCard")))
            {
                alertGenerator.showErrorAlert("New Customer","Please Enter Validated Account Type");
            }
            else {
                long accountNumber;
                for ( ; ; ) {
                    Random rn = new Random();
                    long temp = (long) rn.nextInt();
                    if (temp < 0) temp *= -1;
                    temp += 100000000;
                    if(temp>999999999) continue;
                    if (!dbcontroller.getInstance().Verify_Account(temp)) {
                        accountNumber = temp;
                        break;
                    }
                }
                Customer customer = new Customer(username,email,phone,address);
                customer.setAccount(accountType,accountNumber,System.currentTimeMillis(),balance,balance,0);
                dbcontroller.getInstance().SetProperties(customer.getAccount());
                if(customer.getAccount().getMinimum_amount_for_account_creation()>balance)
                {
                    alertGenerator.showErrorAlert("New Customer","Minimum amount for " + accountType +" is " +
                            customer.getAccount().getMinimum_amount_for_account_creation());
                }
                else
                {
                    dbcontroller.getInstance().addCustomer(username,accountType,accountNumber,balance,balance,email,phone,address);
                    alertGenerator.showInformationAlert("New Account Number","Your Account Number is " + String.valueOf(accountNumber));
                }
            }
        }
        catch(NumberFormatException exception)
        {
            alertGenerator.showErrorAlert("New Customer","Phone/Balance has to be numbers");
        }
        catch(Exception exception)
        {
            System.out.println( exception.getMessage());
        }
    }

    public void previousButtonClick(ActionEvent e)
    {
        try {
            switchToScene("view/BankerPage.fxml",e);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
