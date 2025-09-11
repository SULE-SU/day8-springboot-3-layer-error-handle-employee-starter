package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.InvalidEmployeeException;
import com.example.demo.repository.IEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(String gender, Integer page, Integer size) {
        if(gender == null){
            if(page == null || size == null){
                return employeeRepository.findAll();
            }else {
                Pageable pageable = PageRequest.of(page-1, size);
                return employeeRepository.findAll(pageable).toList();
            }
        }else{
            if(page == null || size == null){
                return employeeRepository.findEmlpoyeesByGender(gender);
            }else {
                Pageable pageable = PageRequest.of(page-1, size);
                return employeeRepository.findEmlpoyeesByGender(gender,pageable);
            }
        }
    }

    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        return employee.get();
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
        employee.setStatus(true);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Employee employee = getEmployeeById(id);
        if(!employee.getStatus()){
            throw new InvalidEmployeeException("employee status is false!");
        }
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        employee.setGender(updatedEmployee.getGender());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setStatus(true);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        employee.get().setStatus(false);
        employeeRepository.save(employee.get());
    }

}