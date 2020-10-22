package com.example.test.mapper;

import com.example.test.bean.ProFinishInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProFinishInfoMapper {
     /*查看项目参与信息*/
     ProFinishInfoBean getProjectInfo(String projectID, String empId);

     int insertProjectInfo(ProFinishInfoBean proFinishInfoBean);
}
