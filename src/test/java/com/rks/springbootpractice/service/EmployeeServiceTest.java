package com.rks.springbootpractice.service;

import com.rks.springbootpractice.entity.Employee;
import com.rks.springbootpractice.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    //Create mock object of employeeRepository
    @Mock
    private EmployeeRepository employeeRepository;

    //Create EmployeeService class actual object and inject the mock dependency(i,e - employeeRepository)
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void getEmployeeById() {
        var employee = buildEmployee();
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employee));
        var emp = employeeService.getEmployeeById(Mockito.anyLong());

        Assertions.assertThat(employee.getId()).isEqualTo(emp.getId());
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void deleteEmployeeById() {
        Mockito.doNothing().when(employeeRepository).deleteById(Mockito.anyLong());
        employeeService.deleteEmployeeById(Mockito.anyLong());
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    private Employee buildEmployee() {
        var employee = new Employee();
        employee.setId(2L);
        employee.setName("Test");
        employee.setDepartment("Test");
        employee.setAddress("Test");
        return employee;
    }

}