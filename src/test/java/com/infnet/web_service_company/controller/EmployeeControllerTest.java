package com.infnet.web_service_company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.web_service_company.model.Employee;
import com.infnet.web_service_company.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    EmployeeRepository repo;
    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void clean() {
        repo.deleteAll();
    }

    @Test
    void createAndGetEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("João");
        employee.setPosition("Dev");
        employee.setEmail("joao@example.com");
        employee.setSalary(3000.0);

        var createResult = mvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        mvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("João"));
    }

    @Test
    void updateNotFound() throws Exception {
        Employee employee = new Employee();
        employee.setName("Teste");
        employee.setPosition("X");
        employee.setSalary(0.0);

        mvc.perform(put("/api/employees/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(employee)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEmployee() throws Exception {
        Employee e = repo.save(new Employee(null, "Fulana", "RH", "fulana@example.com", 2500.0));
        mvc.perform(delete("/api/employees/" + e.getId()))
                .andExpect(status().isNoContent());
        mvc.perform(get("/api/employees/" + e.getId()))
                .andExpect(status().isNotFound());
    }
}
