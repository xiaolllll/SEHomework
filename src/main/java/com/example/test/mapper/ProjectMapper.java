package com.example.test.mapper;

import com.example.test.bean.ProjectBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ProjectMapper {
    //根据项目ID查询项目信息
    ProjectBean getProInfoByProId(String proId);
    //根据项目名查询项目信息
    ProjectBean getProInfoByProName(String proName);
    //根据项目参与者查询项目信息
    List<ProjectBean> getProInfoByProManagerId(String proEmpId);
    //查看所有项目信息
    List<ProjectBean> getProInfoAll();
    //查询一个员工正在进行的项目
    List<ProjectBean> getProInfoByEmpIdDoing(String empId);
    //查询一个员工已完成的项目信息
    List<ProjectBean> getProInfoByEmpHasDone(String empId);
    List<ProjectBean> getEmpProject(String empID);//查看一个员工参加的所有项目信息
    //插入数据
    int insertProject(ProjectBean projectBean);
    //更新数据
    int updateProject(ProjectBean projectBean);
    //删除项目
    int deleteProject(String proId);
    //根据项目ID查询文件路径
    String getProFilePath(String proId);
}
