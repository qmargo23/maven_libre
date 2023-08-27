package org.skypro.EmployeeBook.service.constants;

import org.skypro.EmployeeBook.data.Employee;

public class EmployeeGenerator {

    public static final String IVAN_FIRST_NAME = "Ivan";
    public static final String IVAN_LAST_NAME = "Ivanov";
    public static final double IVAN_SALARY = 790.0;

    public static final String IVANA_FIRST_NAME = "Ivana";
    public static final String IVANA_LAST_NAME = "Ivanova";
    public static final double IVANA_SALARY = 780.0;

    public static final String PETR_FIRST_NAME = "Petr";
    public static final String PETR_LAST_NAME = "Petrov";
    public static final double PETR_SALARY = 819.20;

    public static final String ILYA_FIRST_NAME = "Ilya";
    public static final String ILYA_LAST_NAME = "Ilyn";
    public static final double ILYA_SALARY = 777.01;

    public static final int FIRST_DEPARTMENT_ID = 1;
    public static final int SECOND_DEPARTMENT_ID = 2;

    public static Employee getIvanFirstDep() {
        return new Employee(IVAN_FIRST_NAME, IVAN_LAST_NAME,  IVAN_SALARY,FIRST_DEPARTMENT_ID);
    }
    public static Employee getIvanaFirstDep() {
        return new Employee(IVANA_FIRST_NAME, IVANA_LAST_NAME,  IVANA_SALARY,FIRST_DEPARTMENT_ID);
    }

    public static Employee getPetrFirstDep() {
        return new Employee(PETR_FIRST_NAME, PETR_LAST_NAME, PETR_SALARY, FIRST_DEPARTMENT_ID);
    }

    public static Employee getIlyaSecondDep() {
        return new Employee(ILYA_FIRST_NAME, ILYA_LAST_NAME, ILYA_SALARY, SECOND_DEPARTMENT_ID);
    }
}
