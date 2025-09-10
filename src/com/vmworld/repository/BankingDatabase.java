package com.vmworld.repository;

import com.vmworld.constants.Constants;
import com.vmworld.entity.Customer;

/**
 * Simulates an in-memory database that holds all customer records for the banking application.<br>
 * Uses static array to simulate persistent storage.
 */
public class BankingDatabase {
    /**
     * Fixed-size array storing all registered customers.<br>
     * This is the central data repository for the application, shared across all classes via static import
     */
    public static final Customer[] CUSTOMERS_LIST = new Customer[Constants.CUSTOMERS];
    /**
     * Index tracker indicating the next available position in CUSTOMER_LIST.<br>
     * Also represents the current number of registered customers.
     */
    public static int customerIndex;

    /**
     * Initializes the database by resetting the customer index to zero
     */
    static {
        BankingDatabase.customerIndex = 0;
    }
}
