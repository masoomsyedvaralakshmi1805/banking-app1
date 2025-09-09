package com.vmworld.services;

import com.vmworld.entity.Customer;
import com.vmworld.repository.CustomerRepository;

public class CustomerServicesImpl implements CustomerServices {

    private final CustomerRepository repository;
    public CustomerServicesImpl() {
        repository = new CustomerRepository();
    }

    @Override
    public Customer createCustomer(Customer customer) throws RuntimeException {
        return repository.createCustomer(customer);
    }

}
