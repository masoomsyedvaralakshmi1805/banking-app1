package com.vmworld.entity;

public class Transaction {

    public Transaction() {}

    private String transactionId;
    private String timestamp;
    private TransactionType type;
    private double amount;

    public void setTransactionId(String transactionId) {this.transactionId = transactionId;}
    public void setTimestamp(String timestamp) {this.timestamp = timestamp;}
    public void setType(TransactionType type) {this.type = type;}
    public void setAmount(double amount) {this.amount = amount;}

    public String getTransactionId() {return this.transactionId;}
    public String getTimestamp() {return this.timestamp;}
    public TransactionType getType() {return this.type;}
    public double getAmount() {return this.amount;}

    public Transaction(String transactionId, String timestamp, TransactionType type, double amount){
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + this.transactionId + ", Timestamp: " + this.timestamp + ", Type: " + this.type
                + ", Amount: " + this.amount;
    }

}
