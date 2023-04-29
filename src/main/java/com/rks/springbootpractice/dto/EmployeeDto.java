package com.rks.springbootpractice.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String department;
    private String address;
}
