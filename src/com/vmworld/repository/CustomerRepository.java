package com.vmworld.repository;

import com.vmworld.entity.Customer;
import com.vmworld.constants.Constants;
import static com.vmworld.repository.BankingDatabase.CUSTOMERS_LIST;

public class CustomerRepository {

    private final boolean exists;
    private int customerId;
    private long accountNumber;

    public CustomerRepository() {
        exists = true;
        customerId = 100;
        accountNumber = 10000000L;
    }

    private boolean existsMobileNumber(long mobileNumber) {
        for(int i=0; i<BankingDatabase.customerIndex; i++) {
            if(CUSTOMERS_LIST[i].getMobileNumber() == mobileNumber) {
                return exists;
            }
        }
        return !exists;
    }

    private int generateUniqueCustomerId() {return ++customerId;}
    private long generateUniqueAccountNumber() {return ++accountNumber;}
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
