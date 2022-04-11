package com.projectyobank.models;

import com.projectyobank.database.dbcontroller;

import java.util.ArrayList;

public class Customer extends User{
    private String email;
    private String phone;
    private String address;
    private Account account;
    private ArrayList<Account> accountlist;

    public Customer()
    {

    }

    public Customer(String Username,String email,String phone,String address)
    {
        this.setUsername(Username);
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

    public void setAccount(String type,long number,long time,double balance,double main_Balance,double withdraw_amount){
        if(type.equals("Current"))
        {
            System.out.println("Inside set account");
            this.account = new CurrentAccount(type,number,time,balance,main_Balance,withdraw_amount);
            System.out.println(dbcontroller.getInstance().getCustomer().getAccount().getBalance());

        }
        else if(type.equals("Savings"))
        {
            System.out.println("Yesssssssssssss");
            this.account = new SavingsAccount(type,number,time,balance,main_Balance,withdraw_amount);
            System.out.println(dbcontroller.getInstance().getCustomer().getAccount().getBalance());

        }
        else if(type.equals("Islamic"))
        {
            System.out.println("Yesssssssssssss");
            this.account = new IslamicAccount(type,number,time,balance,main_Balance,withdraw_amount);
            System.out.println(dbcontroller.getInstance().getCustomer().getAccount().getBalance());

        }
        else if(type.equals("FixedDeposit"))
        {
            System.out.println("Yesssssssssssss");
            this.account = new FixedDeposit(type,number,time,balance,main_Balance,withdraw_amount);
            System.out.println(dbcontroller.getInstance().getCustomer().getAccount().getBalance());

        }
        else if(type.equals("CreditCard"))
        {
            System.out.println("Yesssssssssssss");
            this.account = new CreditCard(type,number,time,balance,main_Balance,withdraw_amount);
            System.out.println(dbcontroller.getInstance().getCustomer().getAccount().getBalance());

        }
    }

    public Account getAccount(){return this.account;}

}
