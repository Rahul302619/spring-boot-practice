package com.rks.springbootpractice.service;

import com.rks.springbootpractice.dto.EmployeeDto;
import com.rks.springbootpractice.entity.Employee;
import com.rks.springbootpractice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public Employee getEmployeeById(Long id) {
        logInfoMessage("getEmployeeById invoked");
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found exception"));
    }

    //@Cacheable(key= , cacheNames = "get-employee")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(EmployeeDto employeeDto) {
        getEmployeeById(employeeDto.getId());
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public void logInfoMessage(String message) {
        log.info("********** " + message + " ************");
    }
}
