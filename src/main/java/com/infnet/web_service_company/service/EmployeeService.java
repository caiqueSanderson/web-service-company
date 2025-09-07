package com.infnet.web_service_company.service;

import com.infnet.web_service_company.exception.ResourceNotFoundException;
import com.infnet.web_service_company.model.Employee;
import com.infnet.web_service_company.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo){
        this.repo = repo;
    }

    public List<Employee> findAll(){
        return repo.findAll();
    }

    public Employee findById(Long id){
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", id));
    }

    public Employee create(Employee employee){
        employee.setId(null);
        return repo.save(employee);
    }

    public Employee update(Long id, Employee updated){
        Employee existing = findById(id);
        existing.setName(updated.getName());
        existing.setPosition(updated.getPosition());
        existing.setEmail(updated.getEmail());
        existing.setSalary(updated.getSalary());
        return repo.save(existing);
    }

    public void delete(Long id){
        Employee existing = findById(id);
        repo.delete(existing);
    }
}
