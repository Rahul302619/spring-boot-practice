package com.rks.springbootpractice.service;

import com.rks.springbootpractice.dto.EmployeeDto;
import com.rks.springbootpractice.entity.Employee;
import com.rks.springbootpractice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public Employee getEmployeeById(Long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found exception"));
    }

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
}
