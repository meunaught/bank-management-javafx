package com.projectyobank.models;

import com.projectyobank.Main;
import com.projectyobank.database.dbcontroller;

public class Interest {
    private double rate;
    private double rate_hour;

    public void setRate(double rate){this.rate = rate;}
    public double getRate(){return this.rate;}

    public void setRate_hour(double rate_hour){this.rate_hour = rate_hour;}
    public double getRate_hour(){return this.rate_hour;}

    public double simple_interest(double hours,Account account)
    {
        return account.getBalance() + (account.getMain_balance()*rate* Math.floor(hours))/100.0;
    }

    public double compound_interest(double hours,Account account)
    {
        return account.getBalance()*Math.pow((1+(rate/100.0)),Math.floor(hours));
    }
}
