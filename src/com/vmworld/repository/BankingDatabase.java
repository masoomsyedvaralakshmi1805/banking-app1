package com.vmworld.repository;

import com.vmworld.constants.Constants;
import com.vmworld.entity.Customer;

public class BankingDatabase {
    public static final Customer[] CUSTOMERS_LIST = new Customer[Constants.CUSTOMERS];
    public static int customerIndex;
    public BankingDatabase() {
        BankingDatabase.customerIndex = 0;
    }
}
