package com.projectyobank.models;

abstract public class User {
    private String username;
    public void setUsername(String name)
    {
        this.username = name;
    }
    public String getUsername()
    {
        return username;
    }

}
