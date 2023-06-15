package net.youssfi.customerdataservice.web;


import lombok.AllArgsConstructor;
import net.youssfi.customerdataservice.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.youssfi.customerdataservice.repository.CustomerReposotory;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    private CustomerReposotory customerReposotory;
    @GetMapping("/customers")
    public List<Customer> customerList(){
        return customerReposotory.findAll();
    }
    @GetMapping("/customers/{id}")
    public Customer customerById(@PathVariable Long id){
        return customerReposotory.findById(id).get();
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer){
        customerReposotory.save(customer);
        return customer;
    }

}
