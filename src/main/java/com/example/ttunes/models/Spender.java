package com.example.ttunes.models;


public class Spender {
    private int customerId;
    private String customerFirstName;
    private String customerLastname;
    private double amountBilled;

    public Spender() {
    }

    public Spender(int customerId, String customerFirstName, String customerLastname, double amountBilled) {
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastname = customerLastname;
        this.amountBilled = amountBilled;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastname() {
        return customerLastname;
    }

    public void setCustomerLastname(String customerLastname) {
        this.customerLastname = customerLastname;
    }

    public double getAmountBilled() {
        return amountBilled;
    }

    public void setAmountBilled(double amountBilled) {
        this.amountBilled = amountBilled;
    }
}
