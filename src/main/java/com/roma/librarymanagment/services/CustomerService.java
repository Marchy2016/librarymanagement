package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Customer;
import java.util.List;

public interface CustomerService {
    Customer addCustomer(String name, String surname, String email);
    Customer updateCustomer(Long id, String firstName, String lastName,String email);
    List<Customer> findAllCustomers();
    Customer findCustomerById(Long id);
    void deleteCustomer(Long id);
    public Customer findCustomerByEmail(String email);

}
