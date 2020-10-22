package com.example.test.util;

public class SubTaskUtil {
    public enum DO_TYPE {
        DO_BY_SELF,
        OUT_SOURCE,
        OUT_SOURCE_END
    }

    public enum TASK_STATE {
        UNDONE,
        OUT_SOURCE,
        TO_BE_CHECKED,
        HAS_FINISH
    }

    public static int getTaskDoType(DO_TYPE do_type) {
        if (do_type.equals(DO_TYPE.DO_BY_SELF)) {
            return 0;
        } else if (do_type.equals(DO_TYPE.OUT_SOURCE)) {
            return 1;
        } else if (do_type.equals(DO_TYPE.OUT_SOURCE_END)) {
            return 2;
        }
        return 0;
    }
}
