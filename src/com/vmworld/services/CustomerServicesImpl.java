package com.vmworld.services;

import com.vmworld.entity.Customer;
import com.vmworld.repository.CustomerRepository;

/**
 * Implementation of CustomerService interface.<br>
 * It acts as the service layer, orchestrating business logic by delegating to the repository.<br>
 * Delegates persistence tasks to CustomerRepository.
 */
public class CustomerServicesImpl implements CustomerServices {

    /**
     * Repository instance used to perform data operations.
     */
    private final CustomerRepository repository;

    /**
     * Initializes the service with a new repository instance.
     */
    public CustomerServicesImpl() {
        repository = new CustomerRepository();
    }

    /** {@inheritDoc} */
    @Override
    public Customer createCustomer(Customer customer) throws RuntimeException {
        return repository.createCustomer(customer);
    }

}
