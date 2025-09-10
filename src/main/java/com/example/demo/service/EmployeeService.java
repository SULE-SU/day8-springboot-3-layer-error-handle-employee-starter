package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.InvalidEmployeeException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(String gender, Integer page, Integer size) {
        return employeeRepository.getEmployees(gender, page, size);
    }

    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        if(employee.getAge() ==  null){
            throw new InvalidEmployeeException("employee age is null!");
        }
        if(employee.getAge() > 65 || employee.getAge() < 18){
            throw new InvalidEmployeeException("employee age gender than 65 or less than 18!");
        }
        if(employee.getAge() >30 && employee.getSalary() < 20000.0){
            throw new InvalidEmployeeException("employee needs to hard！");
        }
        return employeeRepository.createEmployee(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Employee employee = employeeRepository.getEmployeeById(id);

        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        if(updatedEmployee.getStatus() ==  false){
            throw new InvalidEmployeeException("employee status is false!");
        }
        return employeeRepository.updateEmployee(id, updatedEmployee);
    }

    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }

        employeeRepository.deleteEmployee(id);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAllEmployees();
    }
}