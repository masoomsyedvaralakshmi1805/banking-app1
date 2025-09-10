package com.vmworld.controller;

import com.vmworld.entity.Customer;
import com.vmworld.entity.Transaction;
import com.vmworld.entity.TransactionType;
import com.vmworld.services.CustomerServices;
import com.vmworld.services.CustomerServicesImpl;
import com.vmworld.services.TransactionServices;
import com.vmworld.services.TransactionServicesImpl;

import java.util.Scanner;

/**
 * The main controller for the VM Savings Bank console application.<br>
 * It manages user interaction via console.<br>
 * Handles input, validations, menu-driven options and delegates logic to services.
 */
public class BankingController {

    /**
     * Scanner for reading user input from console.
     */
    private final Scanner scanner;
    /**
     * Service instance for customer operations
     */
    private final CustomerServices customerService;
    /**
     * Service instance for transaction operations
     */
    private final TransactionServices transactionService;

    /**
     * Initializes controller with scanner and service instance.
     */
    public BankingController() {
        this.scanner = new Scanner(System.in);
        this.customerService = new CustomerServicesImpl();
        this.transactionService = new TransactionServicesImpl();
    }

    /**
     * Prompts user for customer name and sets it with validation.<br>
     * Retries on invalid input.
     * @param customer the customer object to update
     */
    private void setValidCustomerName(Customer customer) {
        while(true) {
            try {
                System.out.print("Enter your name: ");
                customer.setCustomerName(scanner.nextLine());
                break;
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
                scanner.nextLine();
            }
        }
    }

    /**
     * Prompts user for mobile number and sets it with validation.<br>
     * Retries on invalid format.
     * @param customer the customer object to update
     */
    private void setValidMobileNumber(Customer customer) {
        while(true) {
            try {
                System.out.print("Enter your mobile number: ");
                customer.setMobileNumber(scanner.nextLong());
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                scanner.nextLine();
            }
        }
    }

    /**
     * Prompts user for initial deposit and sets it with validation.<br>
     * Ensures minimum balance requirement is met.
     * @param customer the customer object to update
     */
    private void setValidBalance(Customer customer) {
        while(true) {
            try {
                System.out.print("Enter the depositing balance (min 500 INR): ");
                customer.getAccount().setBalance(scanner.nextDouble());
                return;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                scanner.nextLine();
            }
        }
    }

    /**
     * Guides user through account creation process.<br>
     * Collects name, mobile and initial deposit and delegates to the customer service layer.<br>
     * Displays success or error message.
     */
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

    /**
     * Prompts user for account number with validation.<br>
     * Ensures positive number input
     * @return the valid account number
     */
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

    /**
     * Prompts user for transaction amount with validation.<br>
     * Ensures positive amount
     * @return the valid transaction amount
     */
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

    /**
     * Handles deposit or withdrawal transaction workflow.<br>
     * Uses shared input methods for account and amount, and delegates to the transaction service layer.
     * @param type the type of transaction to perform.
     */
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

    /**
     * Displays the current balance of an account.<br>
     * Prompts for account number and shows balance.
     */
    private void displayBalance() {
        try {
            long accountNumber = setValidAccount();
            double balance = transactionService.getAccountBalance(accountNumber);
            System.out.println("Account balance: " + balance);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            scanner.nextLine();
        }
    }

    /**
     * Displays the last 5 transactions (mini statement).<br>
     * Shows most recent transactions in reverse chronological order.
     */
    private void miniStatement() {
        try {
            long accountNumber = setValidAccount();
            Transaction[] transactions = transactionService.getLast5Transactions(accountNumber);
            for(int size=transactions.length-1; size>=0; size--) {
                System.out.println(transactions[size]);
            }
            System.out.println("--- Last 5 transactions printed ---");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            scanner.nextLine();
        }
    }

    /**
     * Starts the main banking application loop.<br>
     * Displays menu and processes user choices until exit.<br>
     * Handles exceptions gracefully and continues execution.
     */
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
                System.out.println(ex.getMessage());
                scanner.nextLine();
            }
        } while(true);
        scanner.close();
        System.out.println("\nThanks for visiting!");
    }

}
