package com.example.test.serviceImpl;

import com.example.test.bean.*;
import com.example.test.component.DocumentManager;
import com.example.test.component.OIDGenerator;
import com.example.test.mapper.*;
import com.example.test.service.ProjectService;
import com.example.test.util.ProjectUtil;
import com.example.test.util.ServiceUtil;
import com.example.test.util.SubTaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TaskFinishInfoMapper taskFinishInfoMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProFinishInfoMapper proFinishInfoMapper;
    @Autowired
    private SubTaskServiceImp subTaskServiceImp;
    @Override
    public String addSubTask(SubTaskBean taskBean, ArrayList<String> leadingPath, ArrayList<String> succeedingPath, boolean isChain) {
        String projectID=taskBean.getSubTaskInProjectId();
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean == null){
            return ServiceUtil.FAILURE+"未找到子任务所属的项目";
        }
        if(projectBean.getProjectState()!= ProjectUtil.PROJECT_STATE.NOT_ENABLED.ordinal()&&
        projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不支持新增子任务";
        }

        for (String s:leadingPath){
            if(subTaskMapper.getTaskInfoByProId(s)==null){
                return ServiceUtil.FAILURE+"数据库中未找到编号为"+s+"的前置任务";
            }
        }

        for (String s:succeedingPath){
            if(subTaskMapper.getTaskInfoByProId(s)==null){
                return ServiceUtil.FAILURE+"数据库中未找到编号为"+s+"的后继任务";
            }
        }

        taskBean.setSubTaskId(OIDGenerator.getInstance().createSubTaskID());
        taskBean.setSubTaskStartTime(new Date());
        taskBean.setSubTaskState(SubTaskUtil.TASK_STATE.UNDONE.ordinal());

        int result = subTaskMapper.insertSubTask(taskBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"向数据库插入新的子任务失败";
        }

        /**
         * 没写完需要taskNextBean的插入函数
         */

        DocumentManager documentManager = new DocumentManager();
        if(!documentManager.createSubTaskFolder(taskBean.getSubTaskInProjectId(),taskBean.getSubTaskId())){
            return ServiceUtil.FAILURE+"创建子任务文件夹失败";
        }

        if(isChain){
            for (String s:succeedingPath){
                String temp=restartSubTask(s,true);
                if(temp.contains(ServiceUtil.FAILURE)){
                    return ServiceUtil.FAILURE+"子任务"+s+"重启失败。"+"失败信息："+temp;
                }
            }
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String deleteSubTask(String SubTaskID) {
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }

        String projectID=subTaskBean.getSubTaskInProjectId();
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean == null){
            return ServiceUtil.FAILURE+"未找到子任务所属的项目";
        }
        if(projectBean.getProjectState()!= ProjectUtil.PROJECT_STATE.NOT_ENABLED.ordinal()&&
                projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不支持删除子任务";
        }

        /**
         * 没写完需要查询任务的负责人和外包人员
        if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.OUT_SOURCE.ordinal()){
            EmployeeBean applicant = employeeMapper.
            String applicantID =
            subTaskServiceImp.outSourcingRecovery()
        }**/
        /**
         * 没写完想要连锁删除
         */
        int result = subTaskMapper.deleteSubTask(SubTaskID);
        if(result!=1){
            return ServiceUtil.FAILURE+"从数据库中删除子任务失败";
        }

        DocumentManager documentManager = new DocumentManager();
        if(!documentManager.deleteSubTaskFolder(subTaskBean.getSubTaskInProjectId(),subTaskBean.getSubTaskId())){
            return ServiceUtil.FAILURE+"删除子任务文件夹失败";
        }
        return ServiceUtil.SUCCESS;
    }

    @Override
    public String modifySubTask(SubTaskBean taskBean) {
        String SubTaskID = taskBean.getSubTaskId();
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }

        String projectID=subTaskBean.getSubTaskInProjectId();
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean == null){
            return ServiceUtil.FAILURE+"未找到子任务所属的项目";
        }
        if(projectBean.getProjectState()!= ProjectUtil.PROJECT_STATE.NOT_ENABLED.ordinal()&&
                projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不支持修改子任务";
        }

        if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.TO_BE_CHECKED.ordinal()||
        subTaskBean.getSubTaskState()==SubTaskUtil.TASK_STATE.HAS_FINISH.ordinal()){
            return ServiceUtil.FAILURE+"编号为"+SubTaskID+"的任务处于不可修改的状态";
        }
        int result = subTaskMapper.updateSubTask(taskBean);
        if (result!=1){
            return ServiceUtil.FAILURE+"数据库中更新子任务失败";
        }
        return ServiceUtil.SUCCESS;
    }

    @Override
    public String restartSubTask(String SubTaskID, boolean isChain) {
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }

        String projectID=subTaskBean.getSubTaskInProjectId();
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean == null){
            return ServiceUtil.FAILURE+"未找到子任务所属的项目";
        }
        if(projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不支持重启子任务";
        }

        if(subTaskBean.getSubTaskState()==SubTaskUtil.TASK_STATE.HAS_FINISH.ordinal()){
            subTaskBean.setSubTaskState(SubTaskUtil.TASK_STATE.UNDONE.ordinal());
            int result=subTaskMapper.updateSubTask(subTaskBean);
            if(result!=1){
                return ServiceUtil.FAILURE+"数据库更新子任务状态失败";
            }
        }

        if(isChain){
            List<SubTaskBean> subTaskBeans= subTaskMapper.getNextTaskListByTaskId(SubTaskID);
            for (SubTaskBean s:subTaskBeans){
                String temp=restartSubTask(s.getSubTaskId(),true);
                if(temp.contains(ServiceUtil.FAILURE)){
                    return ServiceUtil.FAILURE+"子任务"+s.getSubTaskId()+"重启失败。"+"失败信息："+temp;
                }
            }
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String forceCompleteSubTask(String SubTaskID) {
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }

        String projectID=subTaskBean.getSubTaskInProjectId();
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean == null){
            return ServiceUtil.FAILURE+"未找到子任务所属的项目";
        }
        if(projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不支持强制完成子任务";
        }

        /**
         * 没写完需要查询任务的负责人和外包人员
         if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.OUT_SOURCE.ordinal()){
         EmployeeBean applicant = employeeMapper.
         String applicantID =
         subTaskServiceImp.outSourcingRecovery()
         }**/

        if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.TO_BE_CHECKED.ordinal()||
                subTaskBean.getSubTaskState()==SubTaskUtil.TASK_STATE.UNDONE.ordinal()){
                subTaskBean.setSubTaskState(SubTaskUtil.TASK_STATE.HAS_FINISH.ordinal());
                int result = subTaskMapper.updateSubTask(subTaskBean);
                if(result!=1){
                    return ServiceUtil.FAILURE+"数据库更新子任务状态失败";
                }
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String setSubTaskPerson(String SubTaskID, String empID) {
        SubTaskBean subTaskBean=subTaskMapper.getTaskInfoByProId(SubTaskID);
        if(subTaskBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+SubTaskID+"的子任务";
        }

        String projectID=subTaskBean.getSubTaskInProjectId();
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean == null){
            return ServiceUtil.FAILURE+"未找到子任务所属的项目";
        }
        if(projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不支持分配子任务人员";
        }

        EmployeeBean employeeBean=employeeMapper.getEmpInfoByEmpId(empID);
        if(employeeBean==null){
            return ServiceUtil.FAILURE+"未找到标号为"+empID+"的员工";
        }

        if (!employeeBean.hasSkill(subTaskBean.getSubTaskSkillType())) {
            return ServiceUtil.FAILURE + "该员工没有这个任务所需的技能";
        }

        if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.TO_BE_CHECKED.ordinal()||
                subTaskBean.getSubTaskState()==SubTaskUtil.TASK_STATE.HAS_FINISH.ordinal()){
            return ServiceUtil.FAILURE + "当前子任务不能更改人员";
        }

        /**
         * 没写完需要查询任务的负责人和外包人员
         if(subTaskBean.getSubTaskState()== SubTaskUtil.TASK_STATE.OUT_SOURCE.ordinal()){
         EmployeeBean applicant = employeeMapper.
         String applicantID =
         subTaskServiceImp.outSourcingRecovery()
         }**/

        /**
         * 没写完需要查询人物的负责人员
         */

        TaskFinishInfoBean taskFinishInfoBean = new TaskFinishInfoBean();
        taskFinishInfoBean.setEmpId(employeeBean.getEmpId());
        taskFinishInfoBean.setSubTaskId(subTaskBean.getSubTaskId());
        taskFinishInfoBean.setProjectId(subTaskBean.getSubTaskInProjectId());
        taskFinishInfoBean.setDoType(SubTaskUtil.DO_TYPE.DO_BY_SELF.ordinal());

        int result = taskFinishInfoMapper.insertTaskFinishInfo(taskFinishInfoBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库写入新的员工参与信息失败";
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String projectCompleteApply(String projectID) {
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+projectID+"的项目";
        }

        List<SubTaskBean> subTaskBeans=subTaskMapper.getProjectSubTask(projectBean.getProjectId());
        for (SubTaskBean taskBean:subTaskBeans){
            if(taskBean.getSubTaskState()!=SubTaskUtil.TASK_STATE.HAS_FINISH.ordinal()){
                return ServiceUtil.FAILURE+"编号为"+taskBean.getSubTaskId()+"的子任务处于未完成状态";
            }
        }

        projectBean.setProjectState(ProjectUtil.PROJECT_STATE.TO_BE_CHECKED.ordinal());

        int result = projectMapper.updateProject(projectBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库更新项目状态失败";
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String createProject(ProjectBean projectBean) {

        projectBean.setProjectId(OIDGenerator.getInstance().createProjectID());
        projectBean.setProjectState(ProjectUtil.PROJECT_STATE.NOT_ENABLED.ordinal());

        int result = projectMapper.insertProject(projectBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库插入项目信息失败";
        }

        DocumentManager documentManager = new DocumentManager();
        if(!documentManager.createProjectFolder(projectBean.getProjectId())){
            return ServiceUtil.FAILURE+"创建项目文件夹文件夹失败";
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String enableProject(String projectID) {
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+projectID+"的项目";
        }

        if(projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_ENABLED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不能启动项目";
        }

        projectBean.setProjectStartTime(new Date());
        projectBean.setProjectState(ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal());

        int result = projectMapper.updateProject(projectBean);
        if (result!=1){
            return ServiceUtil.FAILURE+"数据库更新项目数据失败";
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String restartProject(String projectID) {
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+projectID+"的项目";
        }

        if(projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.TO_BE_CHECKED.ordinal()&&
                projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.HAS_FINISHED.ordinal()&&
                projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.FAIL.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不能重启子任务";
        }

        projectBean.setProjectState(ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal());

        int result = projectMapper.updateProject(projectBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库更新项目数据失败";
        }

        return ServiceUtil.FAILURE;
    }

    @Override
    public String abandonProject(String projectID) {
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+projectID+"的项目";
        }

        if(projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_ENABLED.ordinal()&&
                projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不能废弃子任务";
        }

        projectBean.setProjectState(ProjectUtil.PROJECT_STATE.FAIL.ordinal());

        int result = projectMapper.updateProject(projectBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库更新项目数据失败";
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String completeProject(String projectID) {

        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean==null){
            return ServiceUtil.FAILURE+"未找到编号为"+projectID+"的项目";
        }

        if(projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.TO_BE_CHECKED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不能完成子任务";
        }

        projectBean.setProjectState(ProjectUtil.PROJECT_STATE.HAS_FINISHED.ordinal());

        int result = projectMapper.updateProject(projectBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库更新项目数据失败";
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String modifyProject(ProjectBean projectBean) {
        String projectID=projectBean.getProjectId();
        ProjectBean pb = projectMapper.getProInfoByProId(projectID);
        if(pb==null){
            return ServiceUtil.FAILURE+"未找到编号为"+ projectBean +"的项目";
        }

        if(pb.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_ENABLED.ordinal()&&
                pb.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不能修改子任务";
        }

        int result = projectMapper.updateProject(projectBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库更新项目数据失败";
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String setProjectManager(String projectID, String empID) {
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean == null){
            return ServiceUtil.FAILURE+"未找到编号为"+projectID+"的项目";
        }

        if(projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_ENABLED.ordinal()&&
                projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不能设置管理员";
        }

        EmployeeBean employeeBean = employeeMapper.getEmpInfoByEmpId(empID);
        if(employeeBean == null){
            return ServiceUtil.FAILURE+"未找到编号为"+empID+"的员工";
        }

        ProFinishInfoBean p1 = new ProFinishInfoBean();
        p1.setEmpId(empID);
        p1.setProjectId(projectID);
        p1.setEmpPosition(ProjectUtil.EMP_POSITION.NORMAL_EMP.ordinal());

        int result = proFinishInfoMapper.insertProjectInfo(p1);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库项目参与信息插入失败";
        }

        if(projectBean.getProManagerId()!=null){
            /**
             * 没写完需要删除项目参与信息的函数
             */
        }

        ProFinishInfoBean p2 = new ProFinishInfoBean();
        p1.setEmpId(empID);
        p1.setProjectId(projectID);
        p1.setEmpPosition(ProjectUtil.EMP_POSITION.MANAGER.ordinal());

        result = proFinishInfoMapper.insertProjectInfo(p2);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库项目参与信息插入失败";
        }

        projectBean.setProManagerId(empID);

        result = projectMapper.updateProject(projectBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库项目信息更新失败";
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    public String addProjectPerson(String projectID, List<String> empIDs) {
        ProjectBean projectBean = projectMapper.getProInfoByProId(projectID);
        if(projectBean == null){
            return ServiceUtil.FAILURE+"未找到编号为"+projectID+"的项目";
        }

        if(projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_ENABLED.ordinal()&&
                projectBean.getProjectState()!=ProjectUtil.PROJECT_STATE.NOT_FINISHED.ordinal()){
            return ServiceUtil.FAILURE+"当前项目状态不能新增成员";
        }

        for(String s:empIDs){
            EmployeeBean employeeBean = employeeMapper.getEmpInfoByEmpId(s);
            if(employeeBean == null){
                return ServiceUtil.FAILURE+"未找到编号为"+s+"的员工";
            }
        }

        for(String s:empIDs){
            ProFinishInfoBean p = new ProFinishInfoBean();
            p.setEmpId(s);
            p.setProjectId(projectID);
            p.setEmpPosition(ProjectUtil.EMP_POSITION.NORMAL_EMP.ordinal());

            int result = proFinishInfoMapper.insertProjectInfo(p);
            if(result!=1){
                return ServiceUtil.FAILURE+"数据库项目参与信息插入失败";
            }
        }

        return ServiceUtil.SUCCESS;
    }

    @Override
    //TODO 从项目中移除员工
    public String deleteProjectPerson(String projectID, String empID) {
        return null;
    }
}
