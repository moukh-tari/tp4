package net.youssfi.customerdataservice.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import net.youssfi.customerdataservice.dto.CustomerRequest;
import net.youssfi.customerdataservice.entities.Customer;
import net.youssfi.customerdataservice.mapper.CustomerMappper;
import net.youssfi.customerdataservice.repository.CustomerReposotory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService(serviceName = "CustomerWS")

public class CustomerSoapService {
    private CustomerReposotory customerReposotory;
    private CustomerMappper customerMappper;
    @WebMethod
    public List<Customer> customerList(){
        return  customerReposotory.findAll();
    }
    @WebMethod
    public Customer customerById(@WebParam(name = "id")Long id){
        return customerReposotory.findById(id).get();
    }
    @WebMethod
    public Customer saveCustomer(@WebParam(name = "customer") CustomerRequest customerRequest){
        Customer customer = customerMappper.from(customerRequest);
        return customerReposotory.save(customer);

    }

}
