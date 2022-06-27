package com.projectyobank.models;

import com.projectyobank.database.dbcontroller;
import com.projectyobank.utils.AlertGenerator;
import javafx.scene.control.Alert;

public class Transaction {
    private String prevBal;
    private String currBal;
    private String Date;
    private String type;
    private String amount;

    public String getPrevBal() {
        return prevBal;
    }

    public String getCurrBal() {
        return currBal;
    }

    public String getDate() {
        return Date;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public void setPrevBal(String prevBal) {
        this.prevBal = prevBal;
    }

    public void setCurrBal(String currBal) {
        this.currBal = currBal;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Transaction() {

    }

    public Transaction(String prevBal, String currBal, String date, String type, String amount) {
        this.prevBal = prevBal;
        this.currBal = currBal;
        Date = date;
        this.type = type;
        this.amount = amount;
    }

    public boolean withdraw(double amount, Customer customer)
    {
        Account account = customer.getAccount();
        account.createBalance();
        AlertGenerator alertGenerator = new AlertGenerator();
        boolean indicator ;
        if(account.getType().compareToIgnoreCase("FixedDeposit") == 0 && account.getStatus().equals("Unmatured"))
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
           indicator =  alertGenerator.showConfirmationAlert("Withdraw Money","Tk." + amount + " will be debited from your account " +
                    account.getNumber() + " and your balance will be " + (account.getBalance()-amount) + ".Do you" +
                    "confirm that transaction?");
        }
        if(indicator)
        {
            double previousBalance = account.getBalance();
            account.setBalance(account.getBalance()-amount);
            if(account.getMain_balance()> account.getBalance())
            {
                account.setMain_balance(account.getBalance());
            }
            account.setCurrent_withdraw_amount(amount+account.getCurrent_withdraw_amount());
            dbcontroller.getInstance().Update_Account(account);
            dbcontroller.getInstance().addTransaction(previousBalance,amount,"Debited",customer);
        }
        return indicator;
    }

    public void deposit(double amount,Customer customer)
    {
        Account account = customer.getAccount();
        account.createBalance();
        AlertGenerator alertGenerator = new AlertGenerator();
        if(alertGenerator.showConfirmationAlert("Deposit Money","Tk." + amount + " will be credited " +
                "to your account "+ account.getNumber() + " and your balance will be " + (account.getBalance()+amount) +
                ".Do you confirm that transaction?"))
        {
            if(account.getType().compareToIgnoreCase("FixedDeposit") == 0 && account.getStatus().equals("Unmatured"))
            {
                alertGenerator.showErrorAlert("Deposit Money","Your Fixed Deposit is not matured yet!!!");
                return ;
            }
            double previousBalance  = account.getBalance();
            account.setBalance(account.getBalance()+amount);
            account.setMain_balance(account.getMain_balance()+amount);
            account.setStatus("Unmatured");
            dbcontroller.getInstance().Update_Account(account);
            dbcontroller.getInstance().addTransaction(previousBalance,amount,"Credited",customer);
        }
    }

    public void TransferMoney(double amount,Customer payer,Customer receiver)
    {
        AlertGenerator alertGenerator = new AlertGenerator();
        if(payer.getAccount().getType().compareToIgnoreCase("FixedDeposit") == 0)
        {
            alertGenerator.showErrorAlert("Transfer Money","You can't transfer money from your fixed deposit!!!");
            return ;
        }
        else if(receiver.getAccount().getType().compareToIgnoreCase("FixedDeposit") == 0)
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


}

