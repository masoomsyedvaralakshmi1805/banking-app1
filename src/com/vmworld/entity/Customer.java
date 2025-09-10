package com.vmworld.entity;

/**
 * Model class representing a banking customer.<br>
 * Contains personal details and is linked to a single bank account.
 *
 * @see Account
 */
public class Customer {

    /**
     * Default constructor initializes the customer with a new account.
     */
    public Customer() {
        account = new Account();
    }

    /**
     * Unique ID assigned to the customer upon registration.
     */
    private int customerId;
    /**
     * Full name of the customer (must be at least 3 characters long)
     */
    private String customerName;
    /**
     * 10 digit mobile number of the customer.
     */
    private long mobileNumber;
    /**
     * Bank account associated with the customer.
     */
    private Account account;

    /**
     * Sets the customer ID.
     * @param customerId the ID to set
     */
    public void setCustomerId (int customerId) {this.customerId = customerId;}

    /**
     * Sets the customer's name with validation<br>
     * Name must not be null and must have at least 3 non-whitespace characters.
     * @param customerName the name to set
     * @throws RuntimeException if name is invalid
     */
    public void setCustomerName(String customerName) throws RuntimeException {
        if(customerName == null || customerName.trim().length()<3) {
            throw new RuntimeException("Invalid customer name");
        }
        this.customerName = customerName;
    }

    /**
     * Sets the mobile number with validation.<br>
     * Must be a valid 10-digit Inidan mobile number.
     * @param mobileNumber the mobile number to set
     * @throws RuntimeException if number is invalid
     */
    public void setMobileNumber(long mobileNumber) throws RuntimeException {
        if(mobileNumber<6000000000L || mobileNumber>9999999999L) {
            throw new RuntimeException("Invalid mobile number");
        }
        this.mobileNumber = mobileNumber;
    }

    /**
     * Sets the associated account.
     * @param account the account to link
     */
    public void setAccount(Account account) {this.account = account;}

    /**
     * Gets the customer ID.
     * @return the unique customer ID
     */
    public int getCustomerId() {return this.customerId;}

    /**
     * Gets the customer's name
     * @return the customer name
     */
    public String getCustomerName() {return this.customerName;}

    /**
     * Gets the mobile number
     * @return the 10-digit mobile number.
     */
    public long getMobileNumber() {return this.mobileNumber;}

    /**
     * Gets the associated account.
     * @return the customer's account
     */
    public Account getAccount() {return this.account;}

    /**
     * Parameterized constructor for creating a new Customer with predefined values/attributes.
     * @param customerId the ID to set
     * @param customerName the name to set
     * @param mobileNumber the mobile number to set
     * @param account the account to link
     */
    public Customer(int customerId, String customerName, long mobileNumber, Account account) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.account = account;
    }

    /**
     * Returns a string representation of the customer.
     * @return formatted string with customer and account details
     */
    @Override
    public String toString() {
        return "Customer ID: " + this.customerId + ", Name: " + this.customerName
                + ", Mobile Number: " + this.mobileNumber + "\nAccount Details:\n" + this.account;
    }

}
