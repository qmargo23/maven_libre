package org.skypro.EmployeeBook.service;

import org.apache.commons.lang3.StringUtils;
import org.skypro.EmployeeBook.data.Employee;
import org.skypro.EmployeeBook.exception.BadRequestException;
import org.skypro.EmployeeBook.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private final static int MAX_SIZE = 3;//максимальное кол сотрудников

    public Employee add(String firstName, String lastName, double salary, int department) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }
        validateIsAlpha(firstName, lastName);
        Employee newEmployee = new Employee(firstName, lastName, salary, department);

        employees.add(newEmployee);
        return newEmployee;
    }

    private void validateIsAlpha(String firstName, String lastName) {
        if (!(StringUtils.isAlpha(firstName) || StringUtils.isAlpha(lastName))) {
            throw new BadRequestException("Имя и фамилия должны содержать только буквы");
        }
    }

    public List<Employee> getAll() {
        return employees;
    }
}
