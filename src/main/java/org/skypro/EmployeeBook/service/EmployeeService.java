package org.skypro.EmployeeBook.service;

import org.apache.commons.lang3.StringUtils;
import org.skypro.EmployeeBook.data.Employee;
import org.skypro.EmployeeBook.exception.BadRequestException;
import org.skypro.EmployeeBook.exception.EmployeeAlreadyAddedException;
import org.skypro.EmployeeBook.exception.EmployeeNotFoundException;
import org.skypro.EmployeeBook.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    private final static int MAX_SIZE = 7;//максимальное кол сотрудников

    //выводим всех сотрудников
    //localhost:8082/store/employee
    public List<Employee> getAll() {
        return employees;
    }
    //добавляем новых сотрудников
    //localhost:8082/store/employee/add?firstName=Ivan&lastName=Ivanov&departmentId=1&salary=100
    public Employee add(String firstName, String lastName, double salary, int departmentId) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }
        validateIsAlpha(firstName, lastName);
        Employee newEmployee = new Employee(firstName, lastName, salary, departmentId);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employees.add(newEmployee);
        return newEmployee;
    }

    private void validateIsAlpha(String firstName, String lastName) {
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new BadRequestException("Имя и фамилия должны содержать только буквы");
        }
    }
    //найти сотрудника
    //localhost:8082/store/employee/find?firstName=Ivan&lastName=Ivanov&departmentId=1&salary=100

    public Employee find(String firstName, String lastName, int departmentId, double salary) {
        Employee employeeForFound = new Employee(firstName, lastName, salary, departmentId);
        for (Employee e : employees) {
            if (e.equals(employeeForFound)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет");
    }
    //удалить сотрудника
    //localhost:8082/store/employee/remove?firstName=Ivan&lastName=Ivanov&departmentId=1&salary=100
    public Employee remove(String firstName, String lastName, double salary, int departmentId) {
        Employee employeeForRemove = new Employee(firstName, lastName, salary, departmentId);

        boolean removeResult = employees.remove(employeeForRemove);
        if (removeResult) {
            return employeeForRemove;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не удален - не был найден в базе");
        }
    }

}
