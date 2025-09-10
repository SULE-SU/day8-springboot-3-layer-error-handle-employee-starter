package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.exception.InvalidEmployeeException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTset {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void should_throw_exception_when_create_a_employee() {
        Employee employee = new Employee(null, "Tom", 20, "MALE", 20000.0);
        when(employeeRepository.createEmployee(any(Employee.class))).thenReturn(employee);
        Employee employeeRsult = employeeService.createEmployee(employee);
        assertEquals(employeeRsult.getAge(),employee.getAge());
    }

    @Test
    void should_throw_exception_when_update_employee_of_gender_than_65_or_less_than_18() {
        Employee employee = new Employee(null, "Tom", 16, "MALE", 20000.0);
        assertThrows(InvalidEmployeeException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    void should_throw_exception_when__employee_of_gender_than_35_with_salary_less_than_20000() {
        Employee employee = new Employee(null, "John", 35, "MALE", 10000.0);
        assertThrows(InvalidEmployeeException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    void should_return_active_status_when_create_employee() {
        Employee employee = new Employee(null, "Tom", 35, "MALE", 10000.0);
        assertTrue(employee.getStatus());
    }




}
