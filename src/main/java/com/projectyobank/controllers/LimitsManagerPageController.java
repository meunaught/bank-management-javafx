package com.projectyobank.controllers;

import com.jfoenix.controls.JFXTextField;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.models.Account;
import com.projectyobank.models.Interest;
import com.projectyobank.utils.AlertGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class LimitsManagerPageController extends  Controller implements Initializable{
    @FXML
    private JFXTextField currentRate;
    @FXML
    private JFXTextField savingsRate;
    @FXML
    private JFXTextField fixedDepositRate;
    @FXML
    private JFXTextField islamicRate;
    @FXML
    private JFXTextField creditcardRate;

    @FXML
    private JFXTextField currentHour;
    @FXML
    private JFXTextField savingsHour;
    @FXML
    private JFXTextField fixedDepositHour;
    @FXML
    private JFXTextField islamicHour;
    @FXML
    private JFXTextField creditcardHour;

    @FXML
    private JFXTextField currentMaximumWithdraw;
    @FXML
    private JFXTextField savingsMaximumWithdraw;
    @FXML
    private JFXTextField fixedDepositMaximumWithdraw;
    @FXML
    private JFXTextField islamicMaximumWithdraw;
    @FXML
    private JFXTextField creditcardMaximumWithdraw;

    @FXML
    private JFXTextField currentMinimumAmount;
    @FXML
    private JFXTextField savingsMinimumAmount;
    @FXML
    private JFXTextField fixedDepositMinimumAmount;
    @FXML
    private JFXTextField islamicMinimumAmount;
    @FXML
    private JFXTextField creditcardMinimumAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        init();

        try {
            Account account = new Account();
            account.setInterest(new Interest());
            account.setType("Current");
            dbcontroller.getInstance().SetProperties(account);
            currentRate.setText(String.valueOf(account.getInterest().getRate()));
            currentHour.setText(String.valueOf(account.getInterest().getRate_hour()));
            currentMaximumWithdraw.setText(String.valueOf(account.getMax_withdraw_amount()));
            currentMinimumAmount.setText(String.valueOf(account.getMinimum_amount_for_account_creation()));

            account.setType("Savings");
            dbcontroller.getInstance().SetProperties(account);
            savingsRate.setText(String.valueOf(account.getInterest().getRate()));
            savingsHour.setText(String.valueOf(account.getInterest().getRate_hour()));
            savingsMaximumWithdraw.setText(String.valueOf(account.getMax_withdraw_amount()));
            savingsMinimumAmount.setText(String.valueOf(account.getMinimum_amount_for_account_creation()));

            account.setType("FixedDeposit");
            dbcontroller.getInstance().SetProperties(account);
            fixedDepositRate.setText(String.valueOf(account.getInterest().getRate()));
            fixedDepositHour.setText(String.valueOf(account.getInterest().getRate_hour()));
            fixedDepositMaximumWithdraw.setText(String.valueOf(account.getMax_withdraw_amount()));
            fixedDepositMinimumAmount.setText(String.valueOf(account.getMinimum_amount_for_account_creation()));

            account.setType("Islamic");
            dbcontroller.getInstance().SetProperties(account);
            islamicRate.setText(String.valueOf(account.getInterest().getRate()));
            islamicHour.setText(String.valueOf(account.getInterest().getRate_hour()));
            islamicMaximumWithdraw.setText(String.valueOf(account.getMax_withdraw_amount()));
            islamicMinimumAmount.setText(String.valueOf(account.getMinimum_amount_for_account_creation()));

            account.setType("CreditCard");
            dbcontroller.getInstance().SetProperties(account);
            creditcardRate.setText(String.valueOf(account.getInterest().getRate()));
            creditcardHour.setText(String.valueOf(account.getInterest().getRate_hour()));
            creditcardMaximumWithdraw.setText(String.valueOf(account.getMax_withdraw_amount()));
            creditcardMinimumAmount.setText(String.valueOf(account.getMinimum_amount_for_account_creation()));
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void updateButtonClick(ActionEvent e)
    {
        if(!temp(currentRate.getText(),currentHour.getText(),currentMaximumWithdraw.getText(),currentMinimumAmount.getText()))
        {
            return ;
        }
        if(!(temp(savingsRate.getText(),savingsHour.getText(),savingsMaximumWithdraw.getText(),savingsMinimumAmount.getText())))
        {
            return ;
        }
        if(!(temp(fixedDepositRate.getText(),fixedDepositHour.getText(),fixedDepositMaximumWithdraw.getText(),fixedDepositMinimumAmount.getText())))
        {
            return ;
        }
        if(!(temp(islamicRate.getText(),islamicHour.getText(),islamicMaximumWithdraw.getText(),islamicMinimumAmount.getText())))
        {
            return ;
        }
        if(!(temp(creditcardRate.getText(),creditcardHour.getText(),creditcardMaximumWithdraw.getText(),creditcardMinimumAmount.getText())))
        {
            return ;
        }

        dbcontroller.getInstance().limitsManager(currentRate.getText(),currentHour.getText(),
                currentMaximumWithdraw.getText(),currentMinimumAmount.getText(),
                "UPDATE Account_Properties SET InterestRate = ?,InterestRateHour = ?,MaxWithdraw = ?,MinimumAmount = ?" +
                        " WHERE Type = ?","Current");

        dbcontroller.getInstance().limitsManager(savingsRate.getText(),savingsHour.getText(),
                savingsMaximumWithdraw.getText(),savingsMinimumAmount.getText(),
                "UPDATE Account_Properties SET InterestRate = ?,InterestRateHour = ?,MaxWithdraw = ?,MinimumAmount = ?" +
                        " WHERE Type = ?","Savings");

        dbcontroller.getInstance().limitsManager(fixedDepositRate.getText(),fixedDepositHour.getText(),
                fixedDepositMaximumWithdraw.getText(),fixedDepositMinimumAmount.getText(),
                "UPDATE Account_Properties SET InterestRate = ?,InterestRateHour = ?,MaxWithdraw = ?,MinimumAmount = ?" +
                        " WHERE Type = ?","FixedDeposit");

        dbcontroller.getInstance().limitsManager(islamicRate.getText(),islamicHour.getText(),
                islamicMaximumWithdraw.getText(),islamicMinimumAmount.getText(),
                "UPDATE Account_Properties SET InterestRate = ?,InterestRateHour = ?,MaxWithdraw = ?,MinimumAmount = ?" +
                        " WHERE Type = ?","Islamic");

        dbcontroller.getInstance().limitsManager(creditcardRate.getText(),creditcardHour.getText(),
                creditcardMaximumWithdraw.getText(),currentMinimumAmount.getText(),
                "UPDATE Account_Properties SET InterestRate = ?,InterestRateHour = ?,MaxWithdraw = ?,MinimumAmount = ?" +
                        " WHERE Type = ?","CreditCard");

        AlertGenerator alertGenerator = new AlertGenerator();
        alertGenerator.showInformationAlert("Limits Manager","Information updated successfully.");
    }

    private boolean temp(String rate,String hour,String maxWithdraw,String minAmount)
    {
        AlertGenerator alertGenerator = new AlertGenerator();
        String []str = new String[4];
        str[0] = rate;
        str[1] = hour;
        str[2] = maxWithdraw;
        str[3] = minAmount;

        for(int i =0;i<4;i++)
        {
            if(str[i].isEmpty())
            {
                alertGenerator.showErrorAlert("Limits Manager","Fill out all the fields.");
                return false;
            }
            try {
                double temp_number = Double.parseDouble(str[i]);
            }
            catch(NumberFormatException exception)
            {
                alertGenerator.showErrorAlert("Limits Manager","All the fields has to be numbers.");
                return false;
            }
        }

        return true;
    }
}
