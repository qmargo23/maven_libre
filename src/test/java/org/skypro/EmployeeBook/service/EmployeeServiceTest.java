package org.skypro.EmployeeBook.service;

import org.junit.jupiter.api.Test;
import org.skypro.EmployeeBook.data.Employee;
import org.skypro.EmployeeBook.exception.EmployeeAlreadyAddedException;
import org.skypro.EmployeeBook.exception.EmployeeNotFoundException;
import org.skypro.EmployeeBook.exception.EmployeeNotFoundForRemoveException;
import org.skypro.EmployeeBook.exception.EmployeeStorageIsFullException;

import static org.junit.jupiter.api.Assertions.*;
import static org.skypro.EmployeeBook.service.constants.EmployeeGenerator.*;

class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void shouldReturn_SuccessResult_WithMethod_add() {
        //Подготовка входных данных
        String firstName = "Ivan";
        String lastName = "Ivanov";
        double salary = 790.0;
        int depId = 1;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getIvanFirstDep();

        //Начало теста
        Employee addedEmployee = employeeService.add(
                firstName,
                lastName,
                salary,
                depId);
        assertEquals(expectedEmployee, addedEmployee);
    }

    @Test
    void shouldThrow_StorageIsFullException_WithEmployee_add() {
        //Подготовка входных данных
        String firstName = PETR_FIRST_NAME;
        String lastName = PETR_LAST_NAME;
        double salary = PETR_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        employeeService.add(IVAN_FIRST_NAME, IVAN_LAST_NAME, IVAN_SALARY, depId);
        employeeService.add(IVANA_FIRST_NAME, IVANA_LAST_NAME, IVANA_SALARY, depId);
        employeeService.add(ILYA_FIRST_NAME, ILYA_LAST_NAME, ILYA_SALARY, depId);
        String expectedMessage = "400 Массив сотрудников переполнен";

        //Начало теста EmployeeStorageIsFullException
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.add(firstName, lastName, salary, depId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrow_AlreadyAddedException_WithEmployee_add() {
        //Подготовка входных данных
        String firstName = PETR_FIRST_NAME;
        String lastName = PETR_LAST_NAME;
        double salary = PETR_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        employeeService.add(PETR_FIRST_NAME, PETR_LAST_NAME, PETR_SALARY, depId);
        String expectedMessage = "400 Такой сотрудник уже есть";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> employeeService.add(firstName, lastName, salary, depId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrow_EmployeeNotFoundException_WithEmployee_find() {
        //Подготовка входных данных
        String firstName = IVANA_FIRST_NAME;
        String lastName = IVANA_LAST_NAME;
        double salary = IVANA_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        String expectedMessage = "404 Такого сотрудника нет";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.find(firstName, lastName, depId, salary)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrow_EmployeeNotFoundForRemoveException_WithEmployee_remove() {
        //Подготовка входных данных
        String firstName = PETR_FIRST_NAME;
        String lastName = PETR_LAST_NAME;
        double salary = PETR_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        String expectedMessage = "404 Сотрудник не удален - не был найден в базе";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundForRemoveException.class,
                () -> employeeService.remove(firstName, lastName, salary, depId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturn_SuccessResult_WithMethod_remove() {
        //Подготовка входных данных
        String firstName = "Ivan";
        String lastName = "Ivanov";
        double salary = 790.0;
        int depId = 1;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getIvanFirstDep();
        employeeService.add(firstName, lastName, salary, depId);

        //Начало теста
        Employee removedEmployee = employeeService.remove(
                firstName,
                lastName,
                salary,
                depId);
        assertEquals(expectedEmployee, removedEmployee);
    }

}