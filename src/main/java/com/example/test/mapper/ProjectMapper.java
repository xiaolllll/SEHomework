package com.example.test.mapper;

import com.example.test.bean.ProjectBean;

import java.util.List;

public interface ProjectMapper {
    //根据项目ID查询项目信息
    public ProjectBean getProInfoByProId(String proId);
    //根据项目名查询项目信息
    public ProjectBean getProInfoByProName(String proName);
    //根据项目参与者查询项目信息
    public List<ProjectBean> getProInfoByProManagerId(String proEmpId);
    //查看所有项目信息
    public List<ProjectBean> getProInfoAll();
    //查询一个员工正在进行的项目
    public List<ProjectBean> getProInfoByEmpIdDoing(String empId);
    //查询一个员工已完成的项目信息
    public List<ProjectBean> getProInfoByEmpHasDone(String empId);
    public List<ProjectBean> getEmpProject(String empID);//查看一个员工参加的所有项目信息
    //插入数据
    public int insertProject(ProjectBean projectBean);
    //更新数据
    public int updateProject(ProjectBean projectBean);
    //删除项目
    public int deleteProject(String proId);
    //根据项目ID查询文件路径
    public String getProFilePath(String proId);
}
