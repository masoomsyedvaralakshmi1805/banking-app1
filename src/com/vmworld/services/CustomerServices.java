package com.vmworld.services;

import com.vmworld.entity.Customer;

public interface CustomerServices {
    Customer createCustomer(Customer customer) throws RuntimeException;
}
