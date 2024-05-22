package com.rks.springbootpractice.service;

import com.rks.springbootpractice.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class StaticEmployeeService {

    public static Employee buildEmployee() {
        var employee = new Employee();
        employee.setId(2L);
        employee.setName("Test");
        employee.setDepartment("Test");
        employee.setAddress("Test");
        return employee;
    }

    public String printAndGetEmployee() {
        String employeeName = StaticEmployeeUtil.getEmployeeName();
        log.info(employeeName);
        return employeeName;
    }
}
