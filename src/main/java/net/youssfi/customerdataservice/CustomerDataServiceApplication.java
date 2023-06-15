package net.youssfi.customerdataservice;

import net.youssfi.customerdataservice.entities.Customer;
import net.youssfi.customerdataservice.repository.CustomerReposotory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerDataServiceApplication.class, args);}

        @Bean
        CommandLineRunner start(CustomerReposotory customerRepository){
            return Args ->{

                customerRepository.save(Customer.builder().name("Hassan") .email("hassan@gmailcom").build());
                customerRepository.save(Customer.builder().name("Ahmed") .email("ahmed@gmailcom") .build());
                customerRepository.save(Customer.builder().name("Momo") .email("momo@gmailcom") .build());
            };




        }


}
