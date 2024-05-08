package com.rks.springbootpractice.controller;

import com.rks.springbootpractice.dto.EmployeeDto;
import com.rks.springbootpractice.entity.Employee;
import com.rks.springbootpractice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("authenticate/employee")
@AllArgsConstructor
@Transactional(Transactional.TxType.REQUIRED)
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity getEmployee(@RequestParam(defaultValue = "") String id) {
        employeeService.logInfoMessage("getEmployee api invoked");
        return !StringUtils.isNumeric(id) ?
                status(OK).body(employeeService.getAllEmployees()) :
                status(OK).body(employeeService.getEmployeeById(Long.valueOf(id.trim())));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Employee saveEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.saveEmployee(employeeDto);
    }

    @PutMapping
    @ResponseStatus(OK)
    public Employee updateEmployee(@RequestParam(defaultValue = "") String id,
                                   @RequestBody EmployeeDto employeeDto) {

        //In case of update it is a good practice to pass id in the url
        return employeeService.updateEmployee(employeeDto);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployeeById(id);
    }
}
