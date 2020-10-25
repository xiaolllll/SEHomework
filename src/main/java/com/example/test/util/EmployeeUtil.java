package com.example.test.util;

public class EmployeeUtil {
    public enum EMP_TYPE {
        MANAGER,
        EMPLOYEE
    }

    public static int getTaskDoType(EMP_TYPE emp_type) {
        if (emp_type.equals(EMP_TYPE.MANAGER)) {
            return 0;
        } else if (emp_type.equals(EMP_TYPE.EMPLOYEE)) {
            return 1;
        }
        return 0;
    }
}
