package org.skypro.EmployeeBook.controller;

import org.skypro.EmployeeBook.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @ExceptionHandler({HttpStatusCodeException.class})
    public String handlerException(Exception e) {
        return "Code: " + e.getMessage();
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAll() {
        return "Список сотрудников:   " + employeeService.getAll().toString();
    }

    @GetMapping("/add")
    public String add(@RequestParam String firstName,
                      @RequestParam String lastName,
                      @RequestParam int departmentId,
                      @RequestParam double salary) {
        return "Добавлен сотрудник:   " + employeeService.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/find")
    public String find(@RequestParam String firstName,
                       @RequestParam String lastName,
                       @RequestParam int departmentId,
                       @RequestParam double salary
    ) {
        return "Найден сотрудник:   " + employeeService.find(firstName, lastName, departmentId, salary);
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam int departmentId,
                         @RequestParam double salary
    ) {
        return "Удален сотрудник:   " + employeeService.remove(firstName, lastName, salary, departmentId);
    }

}
