package com.example.projectyobank;

public interface UserMethods {

    public double currentAccount_IR = 2;
    public double savingsAccount_IR = 5;
    public double fixedDeposit_IR = 100;
    public double creditCard_IR = 0.5;

    public void withdraw(int amount);
    public void deposit();
    public void askForLoan();
    public void checkBalance();
    public void getStatement();
    public void fundTransfer();
    public void createBalance(int Year,int Month,int Day,int Hour,int Minute,int Second);
}
