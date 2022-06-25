package com.projectyobank.models;

import com.projectyobank.utils.AlertGenerator;

public class Senior_Officer extends Banker{

    public Senior_Officer(String username,String password,String designation)
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
        AlertGenerator alertGenerator = new AlertGenerator();
        alertGenerator.accessDenied();
        return false;
    }

    @Override
    public boolean add_Employee() {
        AlertGenerator alertGenerator = new AlertGenerator();
        alertGenerator.accessDenied();
        return false;
    }

    @Override
    public boolean edit_Employee() {
        AlertGenerator alertGenerator = new AlertGenerator();
        alertGenerator.accessDenied();
        return false;
    }

    @Override
    public boolean delete_Employee() {
        AlertGenerator alertGenerator = new AlertGenerator();
        alertGenerator.accessDenied();
        return false;
    }
}
