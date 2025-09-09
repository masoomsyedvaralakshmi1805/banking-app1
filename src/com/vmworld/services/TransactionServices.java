package com.vmworld.services;

import com.vmworld.entity.Transaction;
import com.vmworld.entity.TransactionType;

public interface TransactionServices {
    void registerTransaction(TransactionType type, long accountNumber, double amount) throws RuntimeException;
    double getAccountBalance(long accountNumber) throws RuntimeException;
    Transaction[] getAllTransactions(long accountNumber) throws RuntimeException;
}
