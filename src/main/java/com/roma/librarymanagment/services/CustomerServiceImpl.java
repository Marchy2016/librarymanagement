package com.roma.librarymanagment.services;

import com.roma.librarymanagment.repositories.CustomerRepository;
import com.roma.librarymanagment.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private Customer customer;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(String firstName, String lastName,String email){
        if((!"".equals(firstName) && firstName != null) && (!"".equals(lastName) && lastName != null)
                && (!"".equals(email) && email != null)) {
            customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
        }

        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Long id, String firstName, String lastName,String email){
        customer = findCustomerById(id);
        if(customer != null) {
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customerRepository.save(customer);
        }
        return customer;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id).isPresent() ? customerRepository.findById(id).get() : null;
    }
    public Customer findCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }
    public void deleteCustomer(Long id) {
        customer = findCustomerById(id);
        customerRepository.delete(customer);
    }
}
