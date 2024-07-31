package service;

import Employee.Employee;
import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import exceptions.InvalidDataException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private static final int maxPerson = 10;
    private final Map<String, Employee> employees = new HashMap<>(maxPerson);

    public Employee add(String firstName, String lastName) {

        throwIfInvalidData(firstName, lastName);
        capitalizeName();

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

    private static void capitalizeName() {
        Employee.setFirstname(StringUtils.capitalize(Employee.getFirstname().toLowerCase());
        Employee.setLastname(StringUtils.capitalize(Employee.getLastname().toLowerCase());
    }

    public Employee remove(String firstName, String lastName) {
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

    private static void throwIfInvalidData(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new InvalidDataException();
        }
    }


}
