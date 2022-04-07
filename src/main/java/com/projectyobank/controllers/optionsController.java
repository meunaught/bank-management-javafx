package com.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static com.projectyobank.AccountHolders.type_Of_Functionality;

public class optionsController extends  Controller{
    @FXML
    private Button LogoutButton;
    @FXML
    private Button BalanceButton;
    @FXML
    private Button WithdrawButton;
    @FXML
    private Button DepositButton;
    @FXML
    private Button FundTransferButton;
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

    public void ChooseFunctionality(ActionEvent e)
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

    public void ChooseBalance(ActionEvent e)
    {
        type_Of_Functionality = "Check Balance";
        ChooseFunctionality(e);
    }

    public void ChooseWithDraw(ActionEvent e)
    {
        type_Of_Functionality = "Withdraw";
        ChooseFunctionality(e);
    }

    public void ChooseDeposit(ActionEvent e)
    {
        type_Of_Functionality = "Deposit";
        ChooseFunctionality(e);
    }

    public void ChooseFundTransfer(ActionEvent e)
    {
        type_Of_Functionality = "FundTransfer";
        ChooseFunctionality(e);
    }
}

