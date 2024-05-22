package com.rks.springbootpractice.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StaticEmployeeServiceTest {

    @InjectMocks
    private StaticEmployeeService staticEmployeeService;

    @Test
    void buildEmployee() {
        var employee = StaticEmployeeService.buildEmployee();
        Assertions.assertThat(employee.getId()).isEqualTo(2L);
    }

    @Test
    void printAndGetEmployee() {
        var mockedUtil = Mockito.mockStatic(StaticEmployeeUtil.class);
        mockedUtil.when(StaticEmployeeUtil::getEmployeeName).thenReturn("Test");
        String epmName = staticEmployeeService.printAndGetEmployee();

        Assertions.assertThat(epmName).isEqualTo("Test");
    }

}