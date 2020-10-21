package com.example.test.mapper;

import com.example.test.bean.TaskFileListBean;

import java.util.List;

public interface TaskFileListMapper {
    //查询
    public List<String> getFileList(String taskId);

    //添加
    public int addTaskFileList(TaskFileListBean taskFileListBean);
}
