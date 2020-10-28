package com.example.test.service;

import com.example.test.bean.TaskFileListBean;

import java.util.List;

public interface TaskFileService {
    //查询
    List<String> getFileList(String taskId);

    //添加
    int addTaskFileList(TaskFileListBean taskFileListBean);

}
