package com.projectyobank.models;

public class Manager extends Banker{

    public Manager(String username,String password,String designation)
    {
        super(username,password,designation);
    }

    @Override
    public boolean add_Customer() {
        return true;
    }

    @Override
    public boolean edit_Customer() {
        return true;
    }

    @Override
    public boolean delete_Customer() {
        return true;
    }

    @Override
    public boolean add_Employee() {
        return true;
    }

    @Override
    public boolean edit_Employee() {
        return true;
    }

    @Override
    public boolean delete_Employee() {
        return true;
    }

    @Override
    public boolean limitsManger() {
        return true;
    }

    @Override
    public boolean makeTransaction() {
        return true;
    }

}
