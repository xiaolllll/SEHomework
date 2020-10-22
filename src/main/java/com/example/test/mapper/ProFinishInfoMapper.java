package com.example.test.mapper;

import com.example.test.bean.ProFinishInfoBean;

public interface ProFinishInfoMapper {
     /*查看项目参与信息*/
     public ProFinishInfoBean getProjectInfo(String projectID, String empId);

     public int insertProjectInfo(ProFinishInfoBean proFinishInfoBean);
}
