package com.vmworld.entity;

/**
 * Enum representing the type of transaction performed on a bank account.<br>
 * Helps in categorizing and tracking financial activities.<br>
 * Prevents invalid transaction types from being used.
 */
public enum TransactionType {
    /**
     * Indicates money withdrawn from the account
     */
    WITHDRAW,
    /**
     * Indicated money deposited into the account
     */
    DEPOSIT
}
