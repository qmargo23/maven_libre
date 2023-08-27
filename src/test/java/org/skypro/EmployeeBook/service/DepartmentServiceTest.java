package org.skypro.EmployeeBook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.EmployeeBook.data.Employee;
import org.skypro.EmployeeBook.exception.EmployeeNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.skypro.EmployeeBook.service.constants.EmployeeGenerator.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void shouldReturn_SuccessResult_WithMethod_MaxSalary() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee employeeWithMaxSalary = getPetrFirstDep();

        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );

        //Начало теста
        Employee actualEmployeeWithMaxSalary = departmentService.getEmployeeWithMaxSalary(depId);

        assertEquals(depId, actualEmployeeWithMaxSalary.getDepartmentId());
        assertEquals(employeeWithMaxSalary, actualEmployeeWithMaxSalary);
        assertTrue(petrFirstDep.getSalary() > ivanFirstDep.getSalary());

        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMaxSalary_with_EmployeeNotFoundException() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getIlyaSecondDep()
        ));
        String expectedMessage = "404 Сотрудник с максимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMaxSalary(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void shouldReturn_SuccessResult_WithMethod_MinSalary() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee employeeWithMinSalary = getIvanFirstDep();

        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );

        //Начало теста
        Employee actualEmployeeWithMinSalary = departmentService.getEmployeeWithMinSalary(depId);

        assertEquals(depId, actualEmployeeWithMinSalary.getDepartmentId());
        assertEquals(employeeWithMinSalary, actualEmployeeWithMinSalary);
        assertTrue(petrFirstDep.getSalary() > ivanFirstDep.getSalary());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMinSalary_with_EmployeeNotFoundException() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getIlyaSecondDep()
        ));
        String expectedMessage = "404 Сотрудник с минимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMinSalary(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartment_success() {
        //Подготовка входных данных
        Integer depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ivanaFirstDep = getIvanaFirstDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ivanaFirstDep)
        );
        List<Employee> expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(petrFirstDep);
        expectedEmployeeList.add(ivanFirstDep);
        expectedEmployeeList.add(ivanaFirstDep);


        //Начало теста
        List<Employee> actualEmployeeList = departmentService.getEmployeesByDepartment(depId);
        assertEquals(expectedEmployeeList, actualEmployeeList);
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartments_success() {
        //Подготовка входных данных
//        Integer depId = null;

        //Подготовка ожидаемого результата
        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();
        Employee ivanaFirstDep = getIvanaFirstDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep, ivanaFirstDep)
        );
        Map<Integer, List<Employee>> expectedEmployeeMap = new HashMap<>();
        expectedEmployeeMap.put(FIRST_DEPARTMENT_ID, List.of(petrFirstDep, ivanFirstDep, ivanaFirstDep));
        expectedEmployeeMap.put(SECOND_DEPARTMENT_ID, Collections.singletonList(ilyaSecondDep));

        //Начало теста
        Map<Integer, List<Employee>> actualEmployeeMap = departmentService.getAllEmployeesByDepartments();
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
        verify(employeeService).getAll();
    }

    @Test
    void shouldReturn_SuccessResult_WithMethod_DepartmentSalarySum() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;
        //Подготовка ожидаемого результата
        Double employeeWithDepartmentSalarySum = PETR_SALARY + IVAN_SALARY;

        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep)
        );

        //Начало теста
        Double actualEmployeeWithDepartmentSalarySum = departmentService.getEmployeeDepartmentSalarySum(depId);
        assertEquals(employeeWithDepartmentSalarySum, actualEmployeeWithDepartmentSalarySum);
        verify(employeeService).getAll();
    }

}