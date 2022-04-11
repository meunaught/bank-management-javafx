package com.projectyobank.models;

import com.projectyobank.database.dbcontroller;

import java.sql.Time;
import java.util.Date;

public class IslamicAccount extends Account {

    public IslamicAccount()
    {

    }

    public IslamicAccount(String type,long number,long time,double balance,double main_balance,double withdraw_amount)
    {
        setType(type);
        setNumber(number);
        setTime(time);
        setBalance(balance);
        setMain_balance(main_balance);
        setWithdraw_amount(withdraw_amount);
    }

    public void createBalance()
    {
        Time time;
        Date date;
        double hours;
        Account account;
        account = dbcontroller.getInstance().getCustomer().getAccount();
        time  = account.getTime();
        date = new Date();
        hours = (date.getTime() - time.getTime())/(1000*60*60*1.0);
        hours /=2;
        System.out.println("hours = " + hours);
        if(hours>=1)
        {
            account.setBalance(account.getBalance()+ (account.getBalance()*Math.round(hours)*account.getInterest_rate())/100.0);
            account.getTime().setTime(date.getTime());
            dbcontroller.getInstance().Update_Account(account);
        }
    }
}
