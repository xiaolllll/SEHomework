package com.example.test.util;

public class ProjectUtil {
    public enum PROJECT_STATE{
        NOT_ENABLED,
        NOT_FINISHED,
        TO_BE_CHECKED,
        HAS_FINISHED,
        FAIL
    }
    public enum EMP_POSITION {
        MANAGER,
        NORMAL_EMP,
        OUT_SOURCE_EMP
    }

    public static int getTaskDoType(EMP_POSITION emp_position) {
        if (emp_position.equals(EMP_POSITION.MANAGER)) {
            return 0;
        } else if (emp_position.equals(EMP_POSITION.NORMAL_EMP)) {
            return 1;
        } else if (emp_position.equals(EMP_POSITION.OUT_SOURCE_EMP)) {
            return 2;
        }
        return 0;
    }
}
