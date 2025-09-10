package com.vmworld.services;

import com.vmworld.entity.Transaction;
import com.vmworld.entity.TransactionType;
import com.vmworld.repository.TransactionRepository;

/**
 * Implementation of TransactionService interface.<br>
 * It orchestrates transaction-related business logic and handles the display logic for the mini-statement.<br>
 * Delegates transaction logic to TransactionRepository
 */
public class TransactionServicesImpl implements TransactionServices {

    /**
     * Repository instance used to perform transaction operations.
     */
    private final TransactionRepository repository;

    /**
     * Initializes the service with a new repository instance.
     */
    public TransactionServicesImpl() {
        repository = new TransactionRepository();
    }

    /** {@inheritDoc} */
    @Override
    public void registerTransaction(TransactionType type, long accountNumber, double amount) throws RuntimeException {
        repository.registerTransaction(type, accountNumber, amount);
    }

    /** {@inheritDoc} */
    @Override
    public double getAccountBalance(long accountNumber) throws RuntimeException {
        return repository.getAccountBalance(accountNumber);
    }

    /** {@inheritDoc} */
    @Override
    public Transaction[] getLast5Transactions(long accountNumber) throws RuntimeException {
        Transaction[] transactions = repository.getAllTransactions(accountNumber);
        int index = repository.getFirstNullElement(transactions);
        if(index == 0){
            throw new RuntimeException("No transaction history found");
        }
        int length = Math.min(index, 5);
        Transaction[] last5Transactions = new Transaction[length];
        while(index>0){
            last5Transactions[--length] = transactions[--index];
        }
        return last5Transactions;
    }
}
