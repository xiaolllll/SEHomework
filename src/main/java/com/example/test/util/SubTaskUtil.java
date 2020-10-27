package com.example.test.util;

public class SubTaskUtil {
    public enum DO_TYPE {
        DO_BY_SELF,
        OUT_SOURCE,
        OUT_SOURCE_END
    }

    public enum TASK_STATE {
        NOT_ENABLED,//未启用
        UNDONE, //未完成
        OUT_SOURCE, //外包
        TO_BE_CHECKED, //待验收
        HAS_FINISH //已完成
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

    public static int getTaskState(TASK_STATE task_state) {
        if (task_state.equals(TASK_STATE.UNDONE)) {
            return 0;
        } else if (task_state.equals(TASK_STATE.OUT_SOURCE)) {
            return 1;
        } else if (task_state.equals(TASK_STATE.TO_BE_CHECKED)) {
            return 2;
        } else if (task_state.equals(TASK_STATE.HAS_FINISH)) {
            return 3;
        }
        return 0;
    }

}
