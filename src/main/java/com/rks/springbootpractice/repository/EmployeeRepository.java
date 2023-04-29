package com.rks.springbootpractice.repository;

import com.rks.springbootpractice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
