package com.vmworld.entity;

/**
 * Model class representing a single financial transaction in the banking system.<br>
 * Contains metadata like ID, timestamp, type and amount.
 *
 * @see TransactionType
 */
public class Transaction {

    /**
     * Default constructor for creating a new Transaction.
     */
    public Transaction() {}

    /**
     * Unique identifier for the transaction in the format "TXN-ID"
     */
    private String transactionId;
    /**
     * Timestamp of when the transaction was performed.<br>
     * Formatted as "dd-MM-yyyy HH:mm:ss".
     */
    private String timestamp;
    /**
     * Type of transaction: either WITHDRAW or DEPOSIT
     */
    private TransactionType type;
    /**
     * Monetary amount involved in the transaction.
     */
    private double amount;

    /**
     * Sets the transaction ID.
     * @param transactionId the unique ID to set.
     */
    public void setTransactionId(String transactionId) {this.transactionId = transactionId;}

    /**
     * Sets the timestamp of the transaction.
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {this.timestamp = timestamp;}

    /**
     * Sets the type of transaction.
     * @param type the transaction type to set
     */
    public void setType(TransactionType type) {this.type = type;}

    /**
     * Sets the transaction amount.
     * @param amount the amount to set.
     */
    public void setAmount(double amount) {this.amount = amount;}

    /**
     * Gets the transaction ID.
     * @return the unique transaction ID.
     */
    public String getTransactionId() {return this.transactionId;}

    /**
     * Gets the timestamp of the transaction.
     * @return the formatted timestamp string
     */
    public String getTimestamp() {return this.timestamp;}

    /**
     * Gets the type of transaction.
     * @return the transaction type (WITHDRAW or DEPOSIT)
     */
    public TransactionType getType() {return this.type;}

    /**
     * Gets the transaction amount.
     * @return the amount involved in the transaction
     */
    public double getAmount() {return this.amount;}

    /**
     * Parameterized constructor for creating a new Transaction with predefined values/attributes.
     * @param transactionId transactionId the unique ID to set.
     * @param timestamp the timestamp to set
     * @param type the transaction type to set
     * @param amount the amount to set.
     */
    public Transaction(String transactionId, String timestamp, TransactionType type, double amount){
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
    }

    /**
     * Returns a string representation of the transaction.
     * @return formatted string with transaction details
     */
    @Override
    public String toString() {
        return "Transaction ID: " + this.transactionId + ", Timestamp: " + this.timestamp + ", Type: " + this.type
                + ", Amount: " + this.amount;
    }

}
