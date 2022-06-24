package com.projectyobank.models;

import com.projectyobank.database.dbcontroller;

import java.sql.Time;
import java.util.Date;

public class Account {
    private String type;
    private long number;
    private Date date_of_creation;
    private Time time;
    private Transaction transaction;
    private double balance;
    private double main_balance;
    private double current_withdraw_amount;
    private double max_withdraw_amount;
    private Interest interest;
    private String Status;

    public Account()
    {

    }

    public Account(String type,long number,long time,double balance,double main_balance,double withdraw_amount,String Status)
    {
        this.type = type;
        this.number = number;
        this.time = new Time(time);
        this.balance = balance;
        this.main_balance = main_balance;
        this.current_withdraw_amount =withdraw_amount;
        this.transaction = new Transaction();
        this.interest = new Interest();
        this.Status = Status;
    }

    public void setType(String type){this.type = type;}
    public String getType(){return  this.type;}

    public void setNumber(long number){this.number = number;}
    public long getNumber(){return this.number;}

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

    public void setCurrent_withdraw_amount(double amount){this.current_withdraw_amount = amount;}
    public double getCurrent_withdraw_amount(){return this.current_withdraw_amount;}

    public void setMax_withdraw_amount(double amount){this.max_withdraw_amount = amount;}
    public double getMax_withdraw_amount(){return this.max_withdraw_amount;}

    public void setInterest(Interest interest){this.interest = interest;}
    public Interest getInterest(){return this.interest;}

    public void setStatus(String Status) {this.Status = Status;}
    public String getStatus(){return this.Status;}


    public void createBalance(Account account){
        Time time;
        Date date;
        double hours;
        //Account account;
        //account = dbcontroller.getInstance().getCustomer().getAccount();
        time = account.getTime();
        date = new Date();
        hours = (date.getTime() - time.getTime())/(1000*60*60*1.0);
        dbcontroller.getInstance().SetProperties();
        hours /=account.getInterest().getRate_hour();
        System.out.println(hours);
        if(hours>=1)
        {
            if(account.getType().equals("Current"))
            {
                account.setBalance(account.getInterest().simple_interest(hours,account));
            }
            else if(account.getType().equals("FixedDeposit"))
            {
                if(account.getStatus().equals("Unmatured"))
                {
                    account.setStatus("Matured");
                    account.setBalance(account.getInterest().compound_interest(1,account));
                }
            }
            else
            {
                account.setBalance(account.getInterest().compound_interest(hours,account));
            }
            long temp = (long)(hours-Math.floor(hours))*60*60*1000;
            account.getTime().setTime(date.getTime()-temp);
            dbcontroller.getInstance().Update_Account(account);
        }
    }
}
