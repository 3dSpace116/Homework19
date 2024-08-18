package com.example.HW19.service;

import Employee.Employee;
import exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.*;
import service.EmployeeService;

import java.util.Collection;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @BeforeEach
    void init() {
        employeeService.add("yazz", "zdorovennyy");
        employeeService.add("zdorovennyy", "yazzzz");
    }

    @AfterEach
    void clear() {
        employeeService = new EmployeeService();
    }

    @Test
    void findAll() {
        Collection<Employee> actual = employeeService.getAll();
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    void find() {
        Employee actual = employeeService.find("yazz", "zdorovennyy");
        Assertions.assertEquals("yazz", actual.getFirstname());
        Assertions.assertEquals("zdorovennyy", actual.getLastname());
    }

    @Test
    void ifNotFound() {
        Assertions.assertThrows(EmployeeNotFoundException.class, ()-> employeeService.find("sergey", "sergeev"));
    }
    @Test
    void remove(){
        int size=employeeService.getAll().size();
        employeeService.remove("yazz", "zdorovennyy");
        Assertions.assertEquals(size-1,employeeService.getAll().size());
    }
}

