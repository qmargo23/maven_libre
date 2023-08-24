package org.skypro.EmployeeBook.controller;


import org.skypro.EmployeeBook.data.Employee;
import org.skypro.EmployeeBook.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode();
    }

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/max-salary")
    public String getEmployeeWithMaxSalary(@RequestParam Integer departmentId) {
        return "Сотрудник с Max зарплатой в отделе__" + departmentId + " : " + departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public String getEmployeeWithMinSalary(@RequestParam Integer departmentId) {
        return "Сотрудник с Min зарплатой в отделе__" + departmentId + " : " + departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("/all")
    public String getEmployeesByDepartment(@RequestParam(required = false) Integer departmentId) {
        return "Вывод списка сотрудников по отделам:  " + departmentService.getEmployeesByDepartment(departmentId);
    }
    //localhost:8082/store/departments/all?departmentId=1       вывод по 1 отделу
    //localhost:8082/store/departments/all                      вывод по всем отделам
}
