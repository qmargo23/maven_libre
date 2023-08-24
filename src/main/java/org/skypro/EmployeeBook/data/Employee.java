package org.skypro.EmployeeBook.data;

import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private  int departmentId;
    private  double salary;

    public Employee(String firstName, String lastName, double salary, int departmentId) {
        this.firstName = StringUtils.capitalize(firstName.toLowerCase());
        this.lastName = StringUtils.capitalize(lastName.toLowerCase());
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return
                "[" + lastName + ",  " + firstName + ",  " + salary + ",  " + departmentId + "]  ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return  Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}

