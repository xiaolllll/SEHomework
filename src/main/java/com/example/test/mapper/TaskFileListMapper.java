package com.example.test.mapper;

import com.example.test.bean.TaskFileListBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TaskFileListMapper {
    //查询
     List<TaskFileListBean> getFileList(String taskId);
     List<TaskFileListBean> getFileListByEmpIdTaskId(@Param("empId") String empId,
                                                     @Param("taskId") String taskId);

    //添加
     int addTaskFileList(TaskFileListBean taskFileListBean);

     //删除
     int deleteTaskFileList(TaskFileListBean taskFileListBean);
}
