package com.projectyobank.models;

public class Banker extends User{
    private String password;
    private String designation;

    public Banker() {

    }

    public Banker(String username, String password, String designation) {
        this.setUsername(username);
        this.password = password;
        this.designation = designation;
    }

    public void setPassword(String pass){this.password = pass;}
    public String getPassword()
    {
        return password;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public  String getDesignation() {
        return this.designation;
    }
}
