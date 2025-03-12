package com.rks.springbootpractice.repository;

import com.rks.springbootpractice.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findByName() {
        Employee employee = employeeRepository.findByName("Rahul Singh");
        Assertions.assertThat(employee.getName()).isEqualTo("Rahul Singh");
    }
}