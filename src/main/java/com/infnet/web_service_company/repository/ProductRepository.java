package com.infnet.web_service_company.repository;

import com.infnet.web_service_company.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
