package controller;

import Employee.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.DepartmentService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/department/")


public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable("id") Integer departmentId) {
        List<Employee> employees = departmentService.getEmplSortedByDepartmnt(departmentId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}/salary/sum")
    public ResponseEntity<Double> getTotalSalaryByDepartment(@PathVariable("id") Integer departmentId) {
        Double totalSalary = departmentService.getEmployeeSalarySum(departmentId);
        return ResponseEntity.ok(totalSalary);
    }


    @GetMapping("/{id}/salary/max")
    public ResponseEntity<Double> getMaxSalaryByDepartment(@PathVariable("id") Integer departmentId) {
        Double maxSalary = departmentService.getEmplWithMaxSalary(departmentId);
        return ResponseEntity.ok(maxSalary);
    }


    @GetMapping("/{id}/salary/min")
    public ResponseEntity<Double> getMinSalaryByDepartment(@PathVariable("id") Integer departmentId) {
        Double minSalary = departmentService.getEmplWithMinSalary(departmentId);
        return ResponseEntity.ok(minSalary);
    }


    @GetMapping("/employees")
    public ResponseEntity<Map<Integer, List<Employee>>> getEmployeesGroupedByDepartment() {
        Map<Integer, List<Employee>> employeesByDepartment = departmentService.getAllEmployees();
        return ResponseEntity.ok(employeesByDepartment);
    }
}
