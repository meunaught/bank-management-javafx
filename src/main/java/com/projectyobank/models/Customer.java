package com.projectyobank.models;

import com.projectyobank.database.dbcontroller;

import java.util.ArrayList;

public class Customer{
    private String username;
    private String email;
    private String phone;
    private String address;
    private Account account;
    private ArrayList<Account> accountlist;

    public Customer()
    {

    }

    public Customer(String username,String email,String phone,String address)
    {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public void setEmail(String email){this.email = email;}
    public String getEmail(){return this.email;}

    public void setPhone(String phone){this.phone = phone ;}
    public String getPhone(){return this.phone;}

    public void setAddress(String address){this.address = address;}
    public String getAddress(){return this.address;}

    public void setAccount(String type,long number,long time,double balance,double main_Balance,double withdraw_amount,String Status){
        this.account = new Account(type,number,time,balance,main_Balance,withdraw_amount,Status);
    }

    public Account getAccount(){return this.account;}

}
