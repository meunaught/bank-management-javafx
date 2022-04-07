package com.example.projectyobank;

abstract public class Users {
    private String username;
    private String password;
    public static Users userobj;
    public static AccountHolders accountHolderObj;

    public Users()
    {

    }

    Users(String name,String pass)
    {
        this.username = name;
        this.password = pass;
    }

    public void setUsername(String name)
    {
        this.username = name;
    }

    public void setPassword(String pass)
    {
        this.password = pass;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
