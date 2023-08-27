package org.skypro.EmployeeBook.controller;

import org.skypro.EmployeeBook.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;


@RestController
@RequestMapping()
public class DepartmentController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode();
    }

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //localhost:8080/department/1/salary/sum
    @GetMapping("/{departmentId}/salary/sum")
    public String getEmployeeDepartmentSalarySum(@PathVariable Integer departmentId) {
        return "Общая сумма зарплат в отделе № " + departmentId + " : " + departmentService.getEmployeeDepartmentSalarySum(departmentId);
    }
    //localhost:8080/department/1/salary/max
    @GetMapping("/{departmentId}/salary/max")
    public String getEmployeeWithMaxSalary(@PathVariable Integer departmentId) {
        return "Сотрудник с Max зарплатой в отделе № " + departmentId + " : " + departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    //localhost:8080/department/2/salary/min
    @GetMapping("/{departmentId}/salary/min")
    public String getEmployeeWithMinSalary(@PathVariable Integer departmentId) {
        return "Сотрудник с Min зарплатой в отделе № " + departmentId + " : " + departmentService.getEmployeeWithMinSalary(departmentId);
    }

    ////localhost:8080/department/1/employees
    @GetMapping("/{departmentId}/employees")
    public String getEmployeesByDepartment(@PathVariable Integer departmentId) {
        return "Вывод списка сотрудников по отделу № " + departmentId + " : " + departmentService.getEmployeesByDepartment(departmentId);
    }

    //localhost:8080/department/employees
    @GetMapping("/employees")
    public String getAllEmployeesByDepartments() {
        return "Вывод списка сотрудников по отделам:  " + departmentService.getAllEmployeesByDepartments();
    }

}
