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

      //  firstName=StringUtils.capitalize(firstName.toLowerCase());
     //   lastName=StringUtils.capitalize((lastName.toLowerCase()));

        Employee employee = new Employee(firstName, lastName, 1, 1000);
        var key = makeKey(firstName, lastName);

        if (employees.size() >= maxPerson) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        } else {
            employees.put(key, employee);
            return employee;
        }
    }


    public Employee remove(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        var removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }
        return removed;
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
