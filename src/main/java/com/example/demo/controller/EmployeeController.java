package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.dto.mapper.EmployeeMpper;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMpper employeeMpper;

    public EmployeeController(EmployeeService employeeService, EmployeeMpper employeeMpper) {
        this.employeeService = employeeService;
        this.employeeMpper = employeeMpper;
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees(@RequestParam(required = false) String gender, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return employeeService.getEmployees(gender, page, size);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(@RequestBody @Validated EmployeeRequest employeeRequest) {
        Employee employee = employeeMpper.toEntity(employeeRequest);
        return employeeService.createEmployee(employee);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse updateEmployee(@PathVariable int id, @RequestBody @Validated EmployeeRequest employeeRequest) {
        Employee updatedEmployee = employeeMpper.toEntity(employeeRequest);
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

}
