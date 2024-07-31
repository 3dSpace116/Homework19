package controller;

import Employee.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public Employee getMax(int department) {
        return departmentService.getEmplWithMaxSalary(department);
    }

    @GetMapping("min-salary")
    public Employee getMin(int department) {
        return departmentService.getEmplWithMinSalary(department);
    }

    @GetMapping(value = "/all", params = "/department")
    public List<Employee> getAll(Integer department) {
        return departmentService.getEmployee(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getEmplSortedByDepartmnt();
    }
}
