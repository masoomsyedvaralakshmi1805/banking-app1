package com.vmworld.repository;

import com.vmworld.constants.Constants;
import com.vmworld.entity.Account;
import com.vmworld.entity.Customer;
import com.vmworld.entity.Transaction;
import com.vmworld.entity.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.vmworld.repository.BankingDatabase.CUSTOMERS_LIST;

public class TransactionRepository {

    private int transactionId;
    public TransactionRepository() {
        transactionId = 100;
    }

    private int getCustomerIndex(long accountNumber) {
        int index = 0;
        while(index<Constants.CUSTOMERS && CUSTOMERS_LIST[index]!=null) {
            if(CUSTOMERS_LIST[index].getAccount().getAccountNumber() == accountNumber) {
                return index;
            }
            index++;
        }
        return index;
    }

    public int getFirstNullElement(Transaction[] transactions) throws RuntimeException {
        int start = 0;
        while(transactions[start++] != null) {
            if(start >= Constants.TRANSACTIONS) {
                return start;
            }
        }
        return --start;
    }

    private int generateUniqueTransactionId() {return ++transactionId;}
    public void registerTransaction(TransactionType type, long accountNumber, double amount) throws RuntimeException {

        int index = getCustomerIndex(accountNumber);
        if(index >= BankingDatabase.customerIndex) {
            throw new RuntimeException("Invalid account number");
        } else {
            Customer customer = CUSTOMERS_LIST[index];
            Account account = customer.getAccount();
            double balance = account.getBalance();
            if(type == TransactionType.WITHDRAW && balance-amount<=Constants.MIN_BALANCE) {
                throw new RuntimeException("Insufficient balance!");
            }
            if(type == TransactionType.DEPOSIT) {
                balance += amount;
            } else {
                balance -= amount;
            }
            Transaction[] transactions = account.getTransactions();
            int transIndex = getFirstNullElement(transactions);
            if(transIndex >= Constants.TRANSACTIONS) {
                throw new RuntimeException("Reached transaction limit");
            }
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setTransactionId("TXN-" + generateUniqueTransactionId());
            transaction.setType(type);
            transaction.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            transactions[transIndex] = transaction;
            account.setTransactions(transactions);
            account.setBalance(balance);
            customer.setAccount(account);
            CUSTOMERS_LIST[index] = customer;
        }
    }

    public double getAccountBalance(long accountNumber) throws RuntimeException {
        int index = getCustomerIndex(accountNumber);
        if(index >= BankingDatabase.customerIndex) {
            throw new RuntimeException("Invalid account number");
        } else {
            return CUSTOMERS_LIST[index].getAccount().getBalance();
        }
    }

    public Transaction[] getAllTransactions(long accountNumber) throws RuntimeException {
        int index = getCustomerIndex(accountNumber);
        if(index >= BankingDatabase.customerIndex) {
            throw new RuntimeException("Invalid account number");
        } else {
            return CUSTOMERS_LIST[index].getAccount().getTransactions();
        }
    }

}
