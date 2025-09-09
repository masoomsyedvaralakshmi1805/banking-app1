package com.vmworld.entity;

import com.vmworld.constants.Constants;

public class Account {

    public Account() {
        transactions = new Transaction[Constants.TRANSACTIONS];
    }

    private long accountNumber;
    private double balance;
    private Transaction[] transactions;

    public void setAccountNumber(long accountNumber) {this.accountNumber = accountNumber;}
    public void setBalance(double balance) throws RuntimeException {
        if(balance < Constants.MIN_BALANCE) {
            throw new RuntimeException("Minimum deposit amount is " + Constants.MIN_BALANCE);
        }
        this.balance = balance;
    }
    public void setTransactions(Transaction[] transactions) {this.transactions = transactions;}

    public long getAccountNumber() {return this.accountNumber;}
    public double getBalance() {return this.balance;}
    public Transaction[] getTransactions() {return this.transactions;}

    public Account(long accountNumber, double balance, Transaction[] transactions) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = transactions;
    }

    private String getAllTransactions() {
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<Constants.TRANSACTIONS && transactions[i]!=null; i++) {
            buffer.append(transactions[i]).append("\n");
        }
        return buffer.toString();
    }

    @Override
    public String toString() {
        return "Account number: " + this.accountNumber + ", Balance: " + this.balance + "\n" + getAllTransactions();
    }

}
