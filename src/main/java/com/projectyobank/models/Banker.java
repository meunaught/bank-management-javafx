package com.projectyobank.models;

abstract public class Banker{
    private String password;
    private String designation;
    private String username;
    private Customer customer;

    public Banker(String username, String password, String designation) {
        this.username = username;
        this.password = password;
        this.designation = designation;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public  String getDesignation() {
        return this.designation;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

    public Customer getCustomer(){return this.customer;}
    public void setCustomer(Customer customer){this.customer = customer;}
    public void setCustomer(String username,String email,long phone,String address)
    {
        this.customer = new Customer(username,email,phone,address);
    }

    abstract public boolean add_Customer();
    abstract public boolean edit_Customer();
    abstract public boolean delete_Customer();
    abstract public boolean add_Employee();
    abstract public boolean edit_Employee();
    abstract public boolean delete_Employee();

}
