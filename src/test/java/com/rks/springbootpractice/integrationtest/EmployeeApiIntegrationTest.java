package com.rks.springbootpractice.integrationtest;


import com.rks.springbootpractice.entity.Employee;
import com.rks.springbootpractice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        employee = employeeRepository.save(buildEmployee());
    }

    @Test
    @WithMockUser(username = "test")
    void getEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authenticate/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test"));
    }

    private Employee buildEmployee() {
        var employee = new Employee();
        employee.setName("Test");
        employee.setDepartment("Test");
        employee.setAddress("Test");
        return employee;
    }
}
