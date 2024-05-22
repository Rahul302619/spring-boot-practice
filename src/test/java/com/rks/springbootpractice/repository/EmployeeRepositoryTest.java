package com.rks.springbootpractice.repository;

import com.rks.springbootpractice.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findByName() {
        employeeRepository.save(buildEmployee());
        Employee employee = employeeRepository.findByName("Test");
        Assertions.assertThat(employee.getName()).isEqualTo("Test");
    }

    private Employee buildEmployee() {
        var employee = new Employee();
        employee.setName("Test");
        employee.setDepartment("Test");
        employee.setAddress("Test");
        return employee;
    }
}