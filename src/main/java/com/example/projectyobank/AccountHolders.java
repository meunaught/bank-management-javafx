package com.example.projectyobank;

public class AccountHolders extends Users implements UserMethods{

    private double Balance;
    private String accountType;

    public void setBalance(double balance)
    {
        this.Balance = balance;
    }
    public double getBalance()
    {
        return this.Balance;
    }
    public void setAccountType(String type)
    {
        this.accountType = type;
    }
    public String getAccountType()
    {
        return this.accountType;
    }

    @Override
    public void withdraw() {

    }

    @Override
    public void deposit() {

    }

    @Override
    public void askForLoan() {

    }

    @Override
    public void checkBalance() {

    }

    @Override
    public void getStatement() {

    }

    @Override
    public void fundTransfer() {

    }
    @Override
    public void createBalance()
    {

    }
}
