package com.projectyobank.models;

import com.projectyobank.database.dbcontroller;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.Date;

public class Account {
    private String type;
    private long number;
    private Time time;
    private Transaction transaction;
    private double balance;
    private double main_balance;
    private double current_withdraw_amount;
    private double max_withdraw_amount;
    private Interest interest;
    private String Status;
    private double minimum_amount_for_account_creation;

    public Account()
    {
        this.interest = new Interest();
        this.transaction = new Transaction();
    }

    public Account(String type,long number,long time,double balance,double main_balance,double withdraw_amount,String Status)
    {
        this.type = type;
        this.number = number;
        this.time = new Time(time);
        this.balance = balance;
        this.main_balance = main_balance;
        this.current_withdraw_amount = withdraw_amount;
        this.transaction = new Transaction();
        this.interest = new Interest();
        this.Status = Status;
    }



    public void setType(String type){this.type = type;}
    public String getType(){return  this.type;}

    public void setNumber(long number){this.number = number;}
    public long getNumber(){return this.number;}

    public void setTime(long time){this.time = new Time(time);}
    public Time getTime(){return this.time;}

    public void setTransaction(Transaction transaction){this.transaction = transaction;}
    public Transaction getTransaction(){return this.transaction;}

    public void setBalance(double balance){this.balance = balance;}
    public double getBalance(){return  this.balance;}

    public void setMain_balance(double balance){this.main_balance = balance;}
    public double getMain_balance(){return  this.main_balance;}

    public void setCurrent_withdraw_amount(double amount){this.current_withdraw_amount = amount;}
    public double getCurrent_withdraw_amount(){return this.current_withdraw_amount;}

    public void setMax_withdraw_amount(double amount){this.max_withdraw_amount = amount;}
    public double getMax_withdraw_amount(){return this.max_withdraw_amount;}

    public void setMinimum_amount_for_account_creation(double amount) {this.minimum_amount_for_account_creation = amount;}
    public double getMinimum_amount_for_account_creation(){return this.minimum_amount_for_account_creation;}

    public void setInterest(Interest interest){this.interest = interest;}
    public Interest getInterest(){return this.interest;}

    public void setStatus(String Status) {this.Status = Status;}
    public String getStatus(){return this.Status;}


    public void createBalance(){
        double hours;
        Date date = new Date();
        hours = (date.getTime() - this.time.getTime())/(1000*60*60*1.0);
        dbcontroller.getInstance().SetProperties(this);
        hours /=this.interest.getRate_hour();
        if(hours>=1)
        {
            if(this.type.compareToIgnoreCase("Current") == 0)
            {
                this.setBalance(this.interest.simple_interest(hours,this));
            }
            else if(this.type.compareToIgnoreCase("FixedDeposit") == 0)
            {
                if(this.Status.compareToIgnoreCase("Unmatured") == 0)
                {
                    this.setStatus("Matured");
                    this.setBalance(this.interest.compound_interest(1,this));
                }
            }
            else
            {
                this.setBalance(this.interest.compound_interest(hours,this));
            }
            long temp = (long)(hours-Math.floor(hours))*60*60*1000;
            this.time.setTime(date.getTime()-temp);
            dbcontroller.getInstance().Update_Account(this);
        }
    }
}
