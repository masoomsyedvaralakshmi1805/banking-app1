package com.vmworld.entity;

import com.vmworld.constants.Constants;

/**
 * Model class representing a bank account linked to a customer<br>
 * Contains metadata like current balance, transaction history and account number.
 *
 * @see Transaction
 */
public class Account {

    /**
     * Default constructor for creating a new Account<br>
     * Initializes the account with a fixed-size transaction array.
     */
    public Account() {
        transactions = new Transaction[Constants.TRANSACTIONS];
    }

    /**
     * Unique account number assigned to the customer
     */
    private long accountNumber;
    /**
     * Current balance in the account.
     */
    private double balance;
    /**
     * Array storing transactions performed on the account
     */
    private Transaction[] transactions;

    /**
     * Sets the account number
     * @param accountNumber the account number to set
     */
    public void setAccountNumber(long accountNumber) {this.accountNumber = accountNumber;}

    /**
     * Sets the initial balance of the account after performing a validation check
     * @param balance the balance to set
     * @throws RuntimeException if customer tries to open an account less than minimum balance
     */
    public void setBalance(double balance) throws RuntimeException {
        if(balance < Constants.MIN_BALANCE) {
            throw new RuntimeException("Minimum deposit amount is " + Constants.MIN_BALANCE);
        }
        this.balance = balance;
    }

    /**
     * Sets the transaction array.
     * @param transactions the transaction array to set
     */
    public void setTransactions(Transaction[] transactions) {this.transactions = transactions;}

    /**
     * Gets the account number
     * @return the unique account number
     */
    public long getAccountNumber() {return this.accountNumber;}

    /**
     * Gets the current balance.
     * @return the account balance.
     */
    public double getBalance() {return this.balance;}

    /**
     * Gets the array of transactions associated with the account.
     * @return the transaction array
     */
    public Transaction[] getTransactions() {return this.transactions;}

    /**
     * Parameterized constructor for creating a new Account with predefined values/attributes.
     * @param accountNumber the account number to set
     * @param balance the balance to set
     * @param transactions transactions the transaction array to set
     */
    public Account(long accountNumber, double balance, Transaction[] transactions) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = transactions;
    }

    /**
     * Generates a formatted string of all transactions in the account.
     * @return a string containing all transaction details
     */
    private String getAllTransactions() {
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<Constants.TRANSACTIONS && transactions[i]!=null; i++) {
            buffer.append(transactions[i]).append("\n");
        }
        return buffer.toString();
    }

    /**
     * Returns a string representing of the account.
     * @return  formatted string with account number, balance and transactions
     */
    @Override
    public String toString() {
        return "Account number: " + this.accountNumber + ", Balance: " + this.balance + "\n" + getAllTransactions();
    }

}
