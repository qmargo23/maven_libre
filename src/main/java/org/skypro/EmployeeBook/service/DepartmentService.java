package org.skypro.EmployeeBook.service;

import org.skypro.EmployeeBook.data.Employee;
import org.skypro.EmployeeBook.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
//localhost:8082/store/departments/max-salary?departmentId=1
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {

        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной зарплатой не найден"));
    }
    //localhost:8082/store/departments/min-salary?departmentId=1
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с минимальной зарплатой не найден"));
    }


    public List<Employee> getEmployeesByDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(toList());
    }
    public Map<Integer, List<Employee>> getAllEmployeesByDepartments() {
        return employeeService.getAll().stream()
                .collect(groupingBy(Employee::getDepartmentId, toList()));
    }


}
