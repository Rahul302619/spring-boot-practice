package com.rks.springbootpractice.controller;

import com.rks.springbootpractice.entity.Employee;
import com.rks.springbootpractice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

/*
* It will initialize the mock object and also initialize InjectMock object by injecting the mock object into it
* */
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getEmployee() throws Exception {
        Mockito.doNothing().when(service).logInfoMessage(Mockito.anyString());
        Mockito.when(service.getEmployeeById(Mockito.anyLong())).thenReturn(buildEmployee());
        mockMvc.perform(MockMvcRequestBuilders.get("/authenticate/employees").param("id", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L));
        Mockito.verify(service, Mockito.times(1)).logInfoMessage(Mockito.anyString());
    }

    @Test
    void getEmployees() throws Exception {
        Mockito.doNothing().when(service).logInfoMessage(Mockito.anyString());
        Mockito.when(service.getAllEmployees()).thenReturn(List.of(buildEmployee()));
        mockMvc.perform(MockMvcRequestBuilders.get("/authenticate/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2L));
        Mockito.verify(service, Mockito.times(1)).logInfoMessage(Mockito.anyString());
    }

    @Test
    void deleteEmployee() throws Exception {
        Mockito.doNothing().when(service).deleteEmployeeById(Mockito.anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete("/authenticate/employees/2"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        Mockito.verify(service, Mockito.times(1)).deleteEmployeeById(Mockito.anyLong());
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