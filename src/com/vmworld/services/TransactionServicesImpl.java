package com.vmworld.services;

import com.vmworld.entity.Transaction;
import com.vmworld.entity.TransactionType;
import com.vmworld.repository.TransactionRepository;

public class TransactionServicesImpl implements TransactionServices {

    private final TransactionRepository repository;
    public TransactionServicesImpl() {
        repository = new TransactionRepository();
    }

    @Override
    public void registerTransaction(TransactionType type, long accountNumber, double amount) throws RuntimeException {
        repository.registerTransaction(type, accountNumber, amount);
    }

    @Override
    public double getAccountBalance(long accountNumber) throws RuntimeException {
        return repository.getAccountBalance(accountNumber);
    }

    @Override
    public Transaction[] getAllTransactions(long accountNumber) throws RuntimeException {
        Transaction[] transactions = repository.getAllTransactions(accountNumber);
        int index = repository.getFirstNullElement(transactions);
        if(index == 0){
            throw new RuntimeException("No transaction history found");
        }
        int length = index>5 ? 5 : index;
        Transaction[] last5Transactions = new Transaction[length];
        while(index>0){
            last5Transactions[--length] = transactions[--index];
        }
        return last5Transactions;
    }
}
