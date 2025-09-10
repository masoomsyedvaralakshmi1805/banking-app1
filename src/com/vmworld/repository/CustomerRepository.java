package com.vmworld.repository;

import com.vmworld.entity.Customer;
import com.vmworld.constants.Constants;
import static com.vmworld.repository.BankingDatabase.CUSTOMERS_LIST;

/**
 * Repository class responsible for managing customer data operations.<br>
 * Handles creation, ID generation and duplicate checks
 */
public class CustomerRepository {

    /**
     * A simple counter used to generate unique customer IDs.
     */
    private static int customerId;
    /**
     * A simple counter used to generate unique account numbers.
     */
    private static long accountNumber;

    /**
     * Initializes repository with starting IDs
     */
    static {
        customerId = 100;
        accountNumber = 10000000L;
    }

    /**
     * Checks if a mobile number is already registered.
     * @param mobileNumber the mobile number to check.
     * @return true is number exists, false otherwise.
     */
    private boolean existsMobileNumber(long mobileNumber) {
        for(int i=0; i<BankingDatabase.customerIndex; i++) {
            if(CUSTOMERS_LIST[i].getMobileNumber() == mobileNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a unique customer ID.
     * @return the next available customer ID.
     */
    private int generateUniqueCustomerId() {return ++customerId;}

    /**
     * Generates a unique account number.
     * @return the next available account number.
     */
    private long generateUniqueAccountNumber() {return ++accountNumber;}

    /**
     * Registers a new customer in the system.<br>
     * Validates mobile number uniqueness and storage limits.
     * @param customer the customer object to register.
     * @return the registered customer with assigned IDs.
     * @throws RuntimeException if mobile exists or limit reached.
     */
    public Customer createCustomer(Customer customer) throws RuntimeException {
        long mobileNumber = customer.getMobileNumber();
        if(existsMobileNumber(mobileNumber)){
            throw new RuntimeException("Mobile number already registered!");
        }
        if(BankingDatabase.customerIndex >= Constants.CUSTOMERS) {
            throw new RuntimeException("Reached customer registration creation limit");
        } else {
            customer.setCustomerId(generateUniqueCustomerId());
            customer.getAccount().setAccountNumber(generateUniqueAccountNumber());
            CUSTOMERS_LIST[BankingDatabase.customerIndex++] = customer;
            return customer;
        }
    }

}
