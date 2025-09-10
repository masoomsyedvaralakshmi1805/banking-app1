package com.vmworld.constants;

/**
 * Utility class containing constant values used across the banking application.<br>
 * Defines limits for maximum customers, transactions and minimum balance.<br>
 * This centralizes configuration for limits and business rules
 */
public class Constants {

    /**
     * Maximum number of customers that can be registered in the system
     */
    public static final int CUSTOMERS = 20;
    /**
     * Maximum number of transactions per account
     */
    public static final int TRANSACTIONS = 20;
    /**
     * Minimum balance that must be maintained in an account<br>
     * Used to enforce business rule during withdrawals and account opening deposit
     */
    public static final double MIN_BALANCE = 500.00;

}
