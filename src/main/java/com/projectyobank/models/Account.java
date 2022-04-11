package com.projectyobank.models;

import java.sql.Time;
import java.util.Date;

abstract public class Account {
    private String type;
    private long number;
    private double interest_rate;
    private Date date_of_creation;
    private Time time;
    private Transaction transaction;
    private double balance;
    private double main_balance;
    private double withdraw_amount;

    public Account()
    {

    }

    public Account(String type,long number,long time,double balance,double main_balance,double withdraw_amount)
    {
        this.type = type;
        this.number = number;
        this.time = new Time(time);
        this.balance = balance;
        this.main_balance = main_balance;
        this.withdraw_amount =withdraw_amount;
        this.transaction = new Transaction();
    }

    public void setType(String type){this.type = type;}
    public String getType(){return  this.type;}

    public void setNumber(long number){this.number = number;}
    public long getNumber(){return this.number;}

    public void setInterest_rate(double interest_rate){this.interest_rate = interest_rate;}
    public double getInterest_rate(){return 5;}

    public void setDate_of_creation(Date date){this.date_of_creation = date;}
    public Date getDate_of_creation(){return this.date_of_creation;}

    public void setTime(long time){this.time = new Time(time);}
    public Time getTime(){return this.time;}

    public void setTransaction(Transaction transaction){this.transaction = transaction;}
    public Transaction getTransaction(){return this.transaction;}

    public void setBalance(double balance){this.balance = balance;}
    public double getBalance(){return  this.balance;}

    public void setMain_balance(double balance){this.main_balance = balance;}
    public double getMain_balance(){return  this.main_balance;}

    public void setWithdraw_amount(double amount){this.withdraw_amount = amount;}
    public double getWithdraw_amount(){return this.withdraw_amount;}

    abstract public void createBalance();
}
