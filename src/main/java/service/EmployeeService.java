package service;

import employeepack.Employee;
import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private static final int maxPerson = 10;
    private final Map<String, Employee> employees = new HashMap<>(maxPerson);

    public void add(String firstName, String lastName) {
        if (employees.size() >= maxPerson) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        var key = makeKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();

        } else {
            employees.put(key, employee);
        }

    }

    public void remove(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        var removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee find(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        var employee = employees.get(key);
        if (employee != null) {
            return employee;
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private static String makeKey(String firstName, String lastName) {
        return (firstName + "_" + lastName).toLowerCase();
    }


}
