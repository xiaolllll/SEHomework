package com.example.test.serviceImpl;

import com.example.test.bean.TaskFileListBean;
import com.example.test.mapper.TaskFileListMapper;
import com.example.test.service.SubTaskService;
import com.example.test.service.TaskFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskFileServiceImpl implements TaskFileService {

    @Autowired
    TaskFileListMapper taskFileListMapper;

    @Override
    public List<TaskFileListBean> getFileList(String taskId) {
        return taskFileListMapper.getFileList(taskId);
    }

    @Override
    public int addTaskFileList(TaskFileListBean taskFileListBean) {
        return taskFileListMapper.addTaskFileList(taskFileListBean);
    }

    @Override
    public int deleteTaskFileList(TaskFileListBean taskFileListBean) {
        return taskFileListMapper.deleteTaskFileList(taskFileListBean);
    }


}
