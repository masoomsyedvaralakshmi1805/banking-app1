package com.vmworld.entity;

public class Customer {

    public Customer() {
        account = new Account();
    }

    private int customerId;
    private String customerName;
    private long mobileNumber;
    private Account account;

    public void setCustomerId (int customerId) {this.customerId = customerId;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}
    public void setMobileNumber(long mobileNumber) {
        if(mobileNumber<6000000000L || mobileNumber>9999999999L) {
            throw new RuntimeException("Invalid mobile number");
        }
        this.mobileNumber = mobileNumber;
    }
    public void setAccount(Account account) {this.account = account;}

    public int getCustomerId() {return this.customerId;}
    public String getCustomerName() {return this.customerName;}
    public long getMobileNumber() {return this.mobileNumber;}
    public Account getAccount() {return this.account;}

    public Customer(int customerId, String customerName, long mobileNumber, Account account) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer ID: " + this.customerId + ", Name: " + this.customerName
                + ", Mobile Number: " + this.mobileNumber + "\nAccount Details:\n" + this.account;
    }

}
