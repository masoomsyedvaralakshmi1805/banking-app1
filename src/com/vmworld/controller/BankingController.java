package com.vmworld.controller;

import com.vmworld.entity.Customer;
import com.vmworld.entity.Transaction;
import com.vmworld.entity.TransactionType;
import com.vmworld.services.CustomerServices;
import com.vmworld.services.CustomerServicesImpl;
import com.vmworld.services.TransactionServices;
import com.vmworld.services.TransactionServicesImpl;

import java.util.Scanner;

public class BankingController {

    private final Scanner scanner;
    private final CustomerServices customerService;
    private final TransactionServices transactionService;

    public BankingController() {
        this.scanner = new Scanner(System.in);
        this.customerService = new CustomerServicesImpl();
        this.transactionService = new TransactionServicesImpl();
    }

    private void setValidCustomerName(Customer customer) {
        System.out.print("Enter your name: ");
        customer.setCustomerName(scanner.nextLine());
    }
    private void setValidMobileNumber(Customer customer) {
        long mobileNumber;
        while(true) {
            try {
                System.out.print("Enter your mobile number: ");
                mobileNumber = scanner.nextLong();
                customer.setMobileNumber(mobileNumber);
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                scanner.nextLine();
            }
        }
    }
    private void setValidBalance(Customer customer) {
        double balance;
        while(true) {
            try {
                System.out.print("Enter the depositing balance (min 500 INR): ");
                balance = scanner.nextDouble();
                customer.getAccount().setBalance(balance);
                return;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                scanner.nextLine();
            }
        }
    }
    private void createAccount() {
        Customer customer = new Customer();
        setValidCustomerName(customer);
        setValidMobileNumber(customer);
        setValidBalance(customer);
        try {
            customer = this.customerService.createCustomer(customer);
            System.out.println("Account created successfully!");
            System.out.println("Created account: " + customer);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            scanner.nextLine();
        }
    }

    private long setValidAccount() {
        while(true) {
            System.out.print("Enter bank account: ");
            long accountNumber = scanner.nextLong();
            if(accountNumber<=0) {
                System.out.println("Invalid amount entered!");
            } else {
                return accountNumber;
            }
        }
    }
    private double setValidAmount() {
        while(true) {
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            if(amount<=0.0) {
                System.out.println("Invalid amount entered!");
            } else {
                return amount;
            }
        }
    }
    private void makeTransaction(TransactionType type) {
        try {
            long accountNumber = setValidAccount();
            double amount = setValidAmount();
            transactionService.registerTransaction(type, accountNumber, amount);
            System.out.println("Transaction successfully done!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            scanner.nextLine();
        }
    }
    private void displayBalance() {
        try {
            System.out.print("Enter bank account: ");
            long accountNumber = setValidAccount();
            double balance = transactionService.getAccountBalance(accountNumber);
            System.out.println("Account balance: " + balance);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            scanner.nextLine();
        }
    }
    private void miniStatement() {
        try {
            System.out.print("Enter bank account: ");
            long accountNumber = setValidAccount();
            Transaction[] transactions = transactionService.getAllTransactions(accountNumber);
            for(int size=transactions.length-1; size>=0; size--) {
                System.out.println(transactions[size]);
            }
            System.out.println("--- Last 5 transactions printed ---");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            scanner.nextLine();
        }
    }

    public void startBankingApp() {
        byte choice = 0;
        System.out.println("*** Welcome to VM Savings Bank! ***\n");
        do {
            try {
                System.out.println("1. Create an account\n2. Withdraw amount\n3. Deposit amount" +
                        "\n4. Display Balance\n5. View last 5 transactions\n6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextByte();
                scanner.nextLine();
                if(choice == 6) {
                    break;
                } else if(choice == 1) {
                    createAccount();
                } else if(choice == 2) {
                    makeTransaction(TransactionType.WITHDRAW);
                } else if(choice == 3) {
                    makeTransaction(TransactionType.DEPOSIT);
                } else if(choice == 4) {
                    displayBalance();
                } else if(choice == 5) {
                    miniStatement();
                } else {
                    System.out.println("Invalid choice!");
                }
            } catch (Exception ex) {
                System.out.println(ex);
                scanner.nextLine();
            }
        } while(true);
        scanner.close();
        System.out.println("\nThanks for visiting!");
    }

}
