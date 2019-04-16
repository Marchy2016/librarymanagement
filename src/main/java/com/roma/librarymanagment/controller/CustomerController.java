package com.roma.librarymanagment.controller;

import com.roma.librarymanagment.config.BookProsConfig;
import com.roma.librarymanagment.model.Author;
import com.roma.librarymanagment.model.Customer;
import com.roma.librarymanagment.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    private CustomerService customerService;
    private BookProsConfig bookProsConfig;
    private Customer customer;

    public CustomerController(CustomerService customerService, BookProsConfig bookProsConfig) {
        this.customerService = customerService;
        this.bookProsConfig = bookProsConfig;
    }

    @RequestMapping(path = "/customers", method = RequestMethod.GET)
    public String listAuthors(Model model){
        model.addAttribute("customers",customerService.findAllCustomers());
        return "customers.html";
    }
    @RequestMapping(path = "/savecustomers", method = RequestMethod.GET)
    private String saveCustomer(Model model){

        model.addAttribute("customer",new Customer());
        return "addCustomer";
    }

    @RequestMapping(path = "/savecustomers", method = RequestMethod.POST)
    private String saveCustomer(Model model,@ModelAttribute Customer customer){

        final Customer customer_ = customerService.addCustomer(customer.getFirstName(),customer.getLastName(),customer.getEmail());
        model.addAttribute("customer",customer_);
        return "customer";
    }

    @RequestMapping(path = "/updatecustomer", method = RequestMethod.POST)
    private String updateCustomer(@ModelAttribute Customer custmer){
        if(custmer != null){
            customer = customerService.updateCustomer(custmer.getId(),custmer.getFirstName(),custmer.getLastName(),custmer.getEmail());
        }
        return "customer";
    }


    @RequestMapping(path = "/findcustomerbyid/{id}", method = RequestMethod.GET)
    private String findCustomerById(Model model,@PathVariable Long id){
        customer = customerService.findCustomerById(id);
        if(customer != null){
            model.addAttribute("customer", customer);
        }
        return "updateCustomer";
    }

    @RequestMapping(path = "/deletecustomer/{id}", method = RequestMethod.GET)
    private String deleteCustomer(Model model,@PathVariable Long id){
        customerService.deleteCustomer(id);
        return bookProsConfig.getDisplayCustomers();
    }


}
