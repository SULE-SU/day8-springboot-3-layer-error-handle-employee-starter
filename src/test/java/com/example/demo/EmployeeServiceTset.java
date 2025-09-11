package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.exception.InvalidEmployeeException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.IEmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTset {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private IEmployeeRepository employeeRepository;

    @Test
    void should_throw_exception_when_create_a_employee() {
        Employee employee = new Employee(null, "Tom", 20, "MALE", 20000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
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

    //Deleting an employee simply sets the employee's active status to false.
    @Test
    void should_sets_the_employee_active_status_to_false_when_delete_employee() {
        Employee employee = new Employee(1, "Mike", 20, "MALE", 10000.0);
        assertTrue(employee.getStatus());
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1);
        verify(employeeRepository).save(employee);

    }

    //When updating an employee, you need to verify whether theemployee is active or not,if he/she has already left the company,you can't update him/her.
    @Test
    void should_throw_exception_when_update_employee_of_status_false() {
        Employee employee = new Employee(1, "Mike", 20, "MALE", 10000.0, false);
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        assertThrows(InvalidEmployeeException.class, () -> employeeService.updateEmployee(1,employee));
    }

}
