package service;

import employeepack.Employee;
import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private static final int maxPerson = 10;
    private final List<Employee> employees = new ArrayList<>(maxPerson);

    public void add(String firstName, String lastName) {
        if (employees.size() >= maxPerson) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();

        } else {
            employees.add(employee);
        }

    }

    public void remove(String firstName, String lastName) {
        var it = employees.iterator();
        while (it.hasNext()) {
            var employee = it.next();
            if (employee.getFirstname().equals(firstName) && employee.getLastname().equals(lastName)) {
                it.remove();
                return;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstname().equals(firstName) && employee.getLastname().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees);
    }


}
