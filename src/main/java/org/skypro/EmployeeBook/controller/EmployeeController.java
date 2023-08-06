package org.skypro.EmployeeBook.controller;

import org.skypro.EmployeeBook.data.Employee;
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
            return employeeService.getAll().toString();//выводим всех сотрудников
        }
        @GetMapping("/add")
        public Employee add(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam Integer department,
                            @RequestParam double salary) {
            return employeeService.add( firstName, lastName, salary, department);
        }
    }
