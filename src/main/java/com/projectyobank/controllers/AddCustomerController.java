package com.projectyobank.controllers;

import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.models.Account;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Random;

public class AddCustomerController extends Controller{
    @FXML
    private TextField username;
    @FXML
    private TextField accountType;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField balance;

    public void addCustomer(ActionEvent e)
    {
        try {
            String username = this.username.getText();
            String accountType = this.accountType.getText();
            String address = this.address.getText();
            String email = this.email.getText();
            long phone = Integer.parseInt(this.phone.getText());
            double balance = Double.parseDouble(this.balance.getText());
            if (username.isEmpty() || accountType.isEmpty() || address.isEmpty() || email.isEmpty()) {
                System.out.println("Every box has to be filled ");
            }
            else if(!(accountType.equals("Current")|| accountType.equals("Savings") || accountType.equals("Islamic")
            || accountType.equals("FixedDeposit")||accountType.equals("CreditCard")))
            {
                System.out.println("Please Enter Validated Account Type");
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
                dbcontroller.getInstance().addCustomer(username,accountType,accountNumber,balance,balance,email,phone,address);
                AlertGenerator alertGenerator = new AlertGenerator();
                alertGenerator.showInformationAlert("New Account Number","Your Account Number is " + String.valueOf(accountNumber));
            }
        }
        catch(NumberFormatException exception)
        {
            System.out.println("phone/balance has to be numbers");
            System.out.println(exception);
        }
        catch(Exception exception)
        {
            System.out.println("add customer e problem");
            System.out.println( exception);
        }
    }

    public void goBack(ActionEvent e)
    {
        giveFilename("view/AdminPage.fxml",e);
    }
}
