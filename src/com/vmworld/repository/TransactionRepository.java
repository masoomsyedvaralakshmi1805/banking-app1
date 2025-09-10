package com.vmworld.repository;

import com.vmworld.constants.Constants;
import com.vmworld.entity.Account;
import com.vmworld.entity.Customer;
import com.vmworld.entity.Transaction;
import com.vmworld.entity.TransactionType;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.vmworld.repository.BankingDatabase.CUSTOMERS_LIST;

/**
 * Repository class handling transaction-related operations.<br>
 * Manages deposits, withdrawals, balance checks and history.
 */
public class TransactionRepository {

    /**
     * A simple counter used to generate unique transaction IDs.
     */
    private static int transactionId;

    /**
     * Initializes transaction ID counter.
     */
    static {
        transactionId = 100;
    }

    /**
     * Finds the index of a customer by account number
     * @param accountNumber the account number to search
     * @return index if found, otherwise CUSTOMERS value
     */
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

    /**
     * Finds the first null position in a transaction array.
     * @param transactions the transaction array to scan
     * @return index of first null slot, or TRANSACTIONS if full
     */
    public int getFirstNullElement(Transaction[] transactions) {
        int start = 0;
        while(transactions[start++] != null) {
            if(start >= Constants.TRANSACTIONS) {
                return start;
            }
        }
        return --start;
    }

    /**
     * Generates a unique transaction ID.
     * @return the next available transaction ID.
     */
    private int generateUniqueTransactionId() {return ++transactionId;}

    /**
     * Registers a new financial transaction.<br>
     * This method handles the core business logic of updating the account balance and saving the transaction record.
     * @param type the transaction type
     * @param accountNumber the target account number
     * @param amount the transaction amount
     * @throws RuntimeException if balance is insufficient or limit reached
     */
    public void registerTransaction(TransactionType type, long accountNumber, double amount) throws RuntimeException {

        int index = getCustomerIndex(accountNumber);
        if(index >= BankingDatabase.customerIndex) {
            throw new RuntimeException("Invalid account number");
        } else {
            Customer customer = CUSTOMERS_LIST[index];
            Account account = customer.getAccount();
            double balance = account.getBalance();
            if(type == TransactionType.WITHDRAW && (balance-amount)<Constants.MIN_BALANCE) {
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
            transaction.setTimestamp(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
            transactions[transIndex] = transaction;
//            account.setTransactions(transactions);
            account.setBalance(balance);
//            customer.setAccount(account);
            CUSTOMERS_LIST[index] = customer;
        }
    }

    /**
     * Retrieves the current balance of an account
     * @param accountNumber the account to query
     * @return the current balance
     * @throws RuntimeException if account not found
     */
    public double getAccountBalance(long accountNumber) throws RuntimeException {
        int index = getCustomerIndex(accountNumber);
        if(index >= BankingDatabase.customerIndex) {
            throw new RuntimeException("Invalid account number");
        } else {
            return CUSTOMERS_LIST[index].getAccount().getBalance();
        }
    }

    /**
     * Retrieves all transactions for an account
     * @param accountNumber the account to query
     * @return array of all transactions
     * @throws RuntimeException if account not found
     */
    public Transaction[] getAllTransactions(long accountNumber) throws RuntimeException {
        int index = getCustomerIndex(accountNumber);
        if(index >= BankingDatabase.customerIndex) {
            throw new RuntimeException("Invalid account number");
        } else {
            return CUSTOMERS_LIST[index].getAccount().getTransactions();
        }
    }

}
