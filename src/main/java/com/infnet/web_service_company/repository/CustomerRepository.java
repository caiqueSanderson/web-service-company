package com.infnet.web_service_company.repository;

import com.infnet.web_service_company.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
