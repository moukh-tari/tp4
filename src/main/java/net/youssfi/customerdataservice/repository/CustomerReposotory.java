package net.youssfi.customerdataservice.repository;

import net.youssfi.customerdataservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReposotory extends JpaRepository<Customer, Long> {



}
