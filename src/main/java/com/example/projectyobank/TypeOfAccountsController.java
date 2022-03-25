package com.example.projectyobank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static com.example.projectyobank.Users.accountHolderObj;
import static com.example.projectyobank.Users.userobj;

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
            switchToScene("Scene2.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println("Cannot go back to Scene2.fxml");
        }
    }

    public void Get_Current_Account(ActionEvent e)
    {
        accountHolderObj = new CurrentAccount();
        accountHolderObj.setUsername(userobj.getUsername());
        accountHolderObj.setPassword(userobj.getPassword());
        accountHolderObj.setAccountType("Current");
        GoToAskAccountNumberFxml(e);
    }

    public void Get_Savings_Account(ActionEvent e)
    {
        accountHolderObj = new SavingsAccount();
        accountHolderObj.setUsername(userobj.getUsername());
        accountHolderObj.setPassword(userobj.getPassword());
        accountHolderObj.setAccountType("Savings");
        GoToAskAccountNumberFxml(e);
    }

    public void Get_Islamic_Account(ActionEvent e)
    {
        accountHolderObj = new IslamicAccount();
        accountHolderObj.setUsername(userobj.getUsername());
        accountHolderObj.setPassword(userobj.getPassword());
        accountHolderObj.setAccountType("Islamic");
        GoToAskAccountNumberFxml(e);
    }

    public void Get_CreditCard_Account(ActionEvent e)
    {
        accountHolderObj = new CreditCardAccount();
        accountHolderObj.setUsername(userobj.getUsername());
        accountHolderObj.setPassword(userobj.getPassword());
        accountHolderObj.setAccountType("CreditCard");
        GoToAskAccountNumberFxml(e);
    }

    public void Get_FixedDeposit_Account(ActionEvent e)
    {
        accountHolderObj = new FIxedDepositAccount();
        accountHolderObj.setUsername(userobj.getUsername());
        accountHolderObj.setPassword(userobj.getPassword());
        accountHolderObj.setAccountType("FixedDeposit");
        GoToAskAccountNumberFxml(e);
    }

    public void GoToAskAccountNumberFxml(ActionEvent e)
    {
        try{
            switchToScene("EnterAccountNumber.fxml",e);
        }
        catch(IOException exception)
        {
            System.out.println("Cannot go to EnterAccountNumber.fxml file");
        }
    }
}
