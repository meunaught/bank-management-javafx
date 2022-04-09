package com.projectyobank.controllers;

import com.projectyobank.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static com.projectyobank.Users.accountHolderObj;
import static com.projectyobank.Users.userobj;

public class TypeOfAccountsController extends Controller{

    @FXML
    private Button CurrentAccount;
    @FXML
    private Button SavingsAccount;
    @FXML
    private Button IslamicAccount;
    @FXML
    private Button FixedDeposit;
    @FXML
    private Button CreditCard;
    @FXML
    private Button GoBack;

    public void PreviousPage(ActionEvent e)
    {
        try{
            switchToScene("options.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println("Cannot go back to options.fxml");
        }
    }

    public void Setup_Account(String accountType)
    {
        if(accountType.equals("Current"))
        {
            accountHolderObj = new CurrentAccount();
        }
        else if(accountType.equals("Savings"))
        {
            accountHolderObj = new SavingsAccount();
        }
        else if(accountType.equals("Islamic"))
        {
            accountHolderObj = new IslamicAccount();
        }
        else if(accountType.equals("FixedDeposit"))
        {
            accountHolderObj = new FIxedDepositAccount();
        }
        else if(accountType.equals("CreditCard"))
        {
            accountHolderObj = new CreditCardAccount();
        }
        accountHolderObj.setUsername(userobj.getUsername());
        accountHolderObj.setPassword(userobj.getPassword());
        accountHolderObj.setAccountType(accountType);
    }

    public void Get_Current_Account(ActionEvent e)
    {
        Setup_Account("Current");
        giveFilename("EnterAccountNumber.fxml",e);
    }

    public void Get_Savings_Account(ActionEvent e)
    {
        Setup_Account("Savings");
        giveFilename("EnterAccountNumber.fxml",e);
    }

    public void Get_Islamic_Account(ActionEvent e)
    {
        Setup_Account("Islamic");
        giveFilename("EnterAccountNumber.fxml",e);
    }

    public void Get_CreditCard_Account(ActionEvent e)
    {
        Setup_Account("CreditCard");
        giveFilename("EnterAccountNumber.fxml",e);
    }

    public void Get_FixedDeposit(ActionEvent e)
    {
        Setup_Account("FixedDeposit");
        giveFilename("EnterAccountNumber.fxml",e);
    }
}
