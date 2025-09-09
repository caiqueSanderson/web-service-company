package com.infnet.web_service_company.repository;

import com.infnet.web_service_company.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
