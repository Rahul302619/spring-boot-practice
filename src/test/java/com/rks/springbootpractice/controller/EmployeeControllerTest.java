package com.rks.springbootpractice.controller;

import com.rks.springbootpractice.entity.Employee;
import com.rks.springbootpractice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    void setUp() {}

    @Test
    void getEmployee() {
        Employee employee = new Employee();
        List<Employee> emptyList = List.of();
        Mockito.when(service.getAllEmployees()).thenReturn(emptyList);
        Mockito.when(service.getEmployeeById(Mockito.anyLong())).thenReturn(employee);
        Mockito.doNothing().when(service).logInfoMessage(Mockito.anyString());
        List response = (List) controller.getEmployee("").getBody();
        Employee response1 = (Employee) controller.getEmployee("1").getBody();
        Mockito.verify(service, Mockito.times(2)).logInfoMessage(Mockito.anyString());
        assertThat(response).isEqualTo(emptyList);
        assertThat(response1).isEqualTo(employee);
    }

    @Test
    void deleteEmployee() {
        Mockito.doNothing().when(service).deleteEmployeeById(Mockito.anyLong());
        controller.deleteEmployee(1L);
        Mockito.verify(service, Mockito.times(1)).deleteEmployeeById(Mockito.anyLong());
    }
}