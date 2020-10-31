package com.example.test.service;

import com.example.test.bean.TaskFileListBean;

import java.util.List;

public interface TaskFileService {
    //查询
    List<TaskFileListBean> getFileList(String taskId);

    List<TaskFileListBean> getFileListByEmpIdTaskId(String empId, String taskId);

    //添加
    int addTaskFileList(TaskFileListBean taskFileListBean);

    //
    int deleteTaskFileList(TaskFileListBean taskFileListBean);
}
