package com.vmworld.services;

import com.vmworld.entity.Customer;

/**
 * Service interface defining customer management operations.<br>
 * Promotes loose coupling and abstraction.
 */
public interface CustomerServices {
    /**
     * Registers a new customer in the system.
     * @param customer the customer to create
     * @return the created customer with assigned IDs
     * @throws RuntimeException if an error occurred during customer creation.
     */
    Customer createCustomer(Customer customer) throws RuntimeException;
}
