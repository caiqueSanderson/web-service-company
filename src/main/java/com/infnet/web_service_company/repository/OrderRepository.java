package com.infnet.web_service_company.repository;

import com.infnet.web_service_company.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
