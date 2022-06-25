package com.projectyobank.models;

import com.projectyobank.database.dbcontroller;
import com.projectyobank.utils.AlertGenerator;
import javafx.scene.control.Alert;

public class Transaction {

    public boolean withdraw(double amount,Customer customer)
    {
        Account account = customer.getAccount();
        account.createBalance(account);
        //Account account = dbcontroller.getInstance().getCustomer().getAccount();
        AlertGenerator alertGenerator = new AlertGenerator();
        boolean indicator = false;
        if(account.getType().equals("FixedDeposit") && account.getStatus().equals("Unmatured"))
        {
            indicator = alertGenerator.showErrorAlert("Withdraw Money","Your Fixed Deposit in not matured yet!!!");
        }
        else if(account.getBalance()<amount)
        {
            indicator = alertGenerator.showErrorAlert("Withdraw Money","Your Account Doesn't Have Enough Balance!!!");
        }
        else if(account.getMax_withdraw_amount()<(account.getCurrent_withdraw_amount()+amount))
        {
           indicator =  alertGenerator.showErrorAlert("Withdraw Money","You Can't Withdraw more than " +
                    account.getMax_withdraw_amount() + " in " + account.getInterest().getRate_hour() +
            " hours from" + account.getType() + " account");
        }
        else
        {
           indicator =  alertGenerator.showConfirmationAlert("Withdraw Money","Tk." + amount + " will be debited from your account" +
                    account.getNumber() + " and your balance will be" + (account.getBalance()-amount) + ".Do you" +
                    "confirm that transaction?");
        }
        if(indicator)
        {
            double previousBalance = account.getBalance();
            account.setBalance(account.getBalance()-amount);
            if((account.getBalance()-account.getMain_balance())<amount)
            {
                account.setMain_balance(account.getMain_balance() - (amount -(account.getBalance()- account.getMain_balance())));
            }
            dbcontroller.getInstance().Update_Account(account);
            dbcontroller.getInstance().addTransaction(previousBalance,amount,"Withdraw",customer);
        }
        return indicator;
    }

    public void deposit(double amount,Customer customer)
    {
        Account account = customer.getAccount();
        //Account account = dbcontroller.getInstance().getCustomer().getAccount();
        account.createBalance(account);
        AlertGenerator alertGenerator = new AlertGenerator();
        if(alertGenerator.showConfirmationAlert("Deposit Money","Tk." + amount + " will be credited " +
                "to your account "+ account.getNumber() + " and your balance will be" + (account.getBalance()+amount) +
                ".Do you confirm that transaction?"))
        {
            if(account.getType().equals("FixedDeposit") && account.getStatus().equals("Unmatured"))
            {
                alertGenerator.showErrorAlert("Deposit Money","Your Fixed Deposit is not matured yet!!!");
                return ;
            }
            double previousBalance  = account.getBalance();
            account.setBalance(account.getBalance()+amount);
            account.setMain_balance(account.getMain_balance()+amount);
            account.setStatus("Unmatured");
            dbcontroller.getInstance().Update_Account(account);
            dbcontroller.getInstance().addTransaction(previousBalance,amount,"Deposit",customer);
        }
    }

    public void TransferMoney(double amount,long payer_ac_number,long receiver_ac_number)
    {
        Customer payer;
        Customer receiver;
        AlertGenerator alertGenerator = new AlertGenerator();
        if(dbcontroller.getInstance().Verify_Account(payer_ac_number))
        {
            payer = dbcontroller.getInstance().getCustomer();
            payer.setAccount(dbcontroller.getInstance().getCustomer().getAccount());
            if(dbcontroller.getInstance().Verify_Account(receiver_ac_number))
            {
                receiver = dbcontroller.getInstance().getCustomer();
                receiver.setAccount(dbcontroller.getInstance().getCustomer().getAccount());
                if(payer.getAccount().getType().equals("FixedDeposit"))
                {
                    alertGenerator.showErrorAlert("Transfer Money","You can't transfer money from your fixed deposit!!!");
                    return ;
                }
                else if(receiver.getAccount().getType().equals("FixedDeposit"))
                {
                    alertGenerator.showErrorAlert("Transfer Money","You can't transfer money to fixed deposit accounts!!!");
                    return ;
                }
                if(payer.getAccount().getTransaction().withdraw(amount,payer))
                {
                    receiver.getAccount().getTransaction().deposit(amount,receiver);
                }
                else
                {
                    alertGenerator.showErrorAlert("Transfer Money","Payer Don't have enough money in account!!!");
                }
            }
            else
            {
                alertGenerator.showErrorAlert("Transfer Money","Receiver Account Number is not valid!!!");
            }
        }
        else
        {
            alertGenerator.showErrorAlert("Transfer Money","Payer Account Number is not valid!!!");
        }
    }
}
