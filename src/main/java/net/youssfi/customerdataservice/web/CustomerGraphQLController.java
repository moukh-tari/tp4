package net.youssfi.customerdataservice.web;

import lombok.AllArgsConstructor;
import net.youssfi.customerdataservice.dto.CustomerRequest;
import net.youssfi.customerdataservice.entities.Customer;
import net.youssfi.customerdataservice.mapper.CustomerMappper;
import net.youssfi.customerdataservice.repository.CustomerReposotory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerGraphQLController {
    private CustomerReposotory customerReposotory;
    private CustomerMappper customerMappper;
    @QueryMapping
    public List<Customer> allCustomers(){
        return customerReposotory.findAll();
    }
    @QueryMapping
    public Customer customerById(@Argument Long id){
        Customer customer = customerReposotory.findById(id).orElse(null);
        if(customer==null) throw new RuntimeException(String.format("Customer %s not found ",id));
        return customer;
    }
    @MutationMapping
    public Customer saveCustomer(@Argument CustomerRequest customer){
        Customer c = customerMappper.from(customer);
        return customerReposotory.save(c);

    }

}
