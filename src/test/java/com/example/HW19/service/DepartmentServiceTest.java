package com.example.HW19.service;

import Employee.Employee;
import exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.DepartmentService;
import service.EmployeeService;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {
        when(employeeService.getAll()).thenReturn(List.of(
                new Employee("yazz", "zdorovennyy", 1, 1000),
                new Employee("vazz", "zdorovennyh", 2, 2000),
                new Employee("tazz", "zdorovennyj", 2, 4000),
                new Employee("gazz", "zdorovennyk", 1, 5000)
        ));
    }

    @Test
    void sum() {
        double actual = departmentService.getEmployeeSalarySum(1);
        Assertions.assertEquals(6000, actual);
    }

    @Test
    void min() {
        double actual = departmentService.getEmplWithMinSalary(1);
        Assertions.assertEquals(1000, actual);
    }

    @Test
    void min_negative() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmplWithMinSalary(3));
    }

    @Test
    void max() {
        double actual = departmentService.getEmplWithMaxSalary(1);
        Assertions.assertEquals(5000, actual);
    }

    @Test
    void max_negative() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmplWithMaxSalary(3));
    }

    @Test
    void filter() {
        List<Employee> actual = departmentService.getEmplSortedByDepartmnt(1);
        Assertions.assertEquals(2, actual);
        actual.forEach(employee -> Assertions.assertEquals(1, employee.getDepartment()));
    }
    @Test
    void filter_negative(){
        List<Employee>actual=departmentService.getEmplSortedByDepartmnt(3);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void groupped() {
        Map<Integer, List<Employee>> actual = departmentService.getAllEmployees();
        Assertions.assertEquals(2, actual.keySet().size());
        for (Map.Entry<Integer, List<Employee>> entry : actual.entrySet()) {
            Integer department = entry.getKey();
            List<Employee> employees = entry.getValue();
            employees.forEach(employee -> Assertions.assertEquals(department, employee.getDepartment()));
        }


    }
}
