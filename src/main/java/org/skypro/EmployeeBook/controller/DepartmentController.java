package org.skypro.EmployeeBook.controller;

import org.skypro.EmployeeBook.data.Employee;
import org.skypro.EmployeeBook.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

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

    //localhost:8080/department/1/salary/max
    @GetMapping("/{departmentId}/salary/max")
    public String getEmployeeWithMaxSalary(@PathVariable Integer departmentId) {
        return "Сотрудник с Max зарплатой в отделе__" + departmentId + " : " + departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    //localhost:8080/department/2/salary/min
    @GetMapping("/{departmentId}/salary/min")
    public String getEmployeeWithMinSalary(@PathVariable Integer departmentId) {
        return "Сотрудник с Min зарплатой в отделе__" + departmentId + " : " + departmentService.getEmployeeWithMinSalary(departmentId);
    }

    ////localhost:8080/department/1/employees
    @GetMapping("/{departmentId}/employees")
    public String getEmployeesByDepartment(@PathVariable Integer departmentId) {
        return "Вывод списка сотрудников по отделУ:  " + departmentId + " : " + departmentService.getEmployeesByDepartment(departmentId);
    }

    //localhost:8080/department/employees
    @GetMapping("/employees")
    public String getAllEmployeesByDepartments() {
        return "Вывод списка сотрудников по отделАМ:  " + departmentService.getAllEmployeesByDepartments();
    }

}
