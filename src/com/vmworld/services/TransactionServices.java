package com.vmworld.services;

import com.vmworld.entity.Transaction;
import com.vmworld.entity.TransactionType;

/**
 * Service interface defining transaction operations.<br>
 * Abstracts business logic for deposit, withdrawals and queries.
 */
public interface TransactionServices {
    /**
     * Performs a deposit or withdrawal transaction
     * @param type the transaction type
     * @param accountNumber the target account number
     * @param amount the transaction amount
     * @throws RuntimeException if operation fails
     */
    void registerTransaction(TransactionType type, long accountNumber, double amount) throws RuntimeException;

    /**
     * Gets the current balance of the requested account
     * @param accountNumber the account to check
     * @return the current balance
     * @throws RuntimeException if account not found
     */
    double getAccountBalance(long accountNumber) throws RuntimeException;

    /**
     * Retrieves the last 5 transactions for mini statement.<br>
     * Returns most recent transactions in chronological order.
     * @param accountNumber the account to query
     * @return array of up to 5 recent transactions
     * @throws RuntimeException if account not found
     */
    Transaction[] getLast5Transactions(long accountNumber) throws RuntimeException;
}
