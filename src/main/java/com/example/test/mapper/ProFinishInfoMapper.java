package com.example.test.mapper;

import com.example.test.bean.ProFinishInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProFinishInfoMapper {
     /*查看项目参与信息*/
     ProFinishInfoBean getProjectInfo(@Param("projectId") String projectId, @Param("empId") String empId);

     int insertProjectInfo(ProFinishInfoBean proFinishInfoBean);

     int deleteProjectInfoManager(@Param("projectId") String projectId, @Param("empId") String empId);

     int deleteProjectInfoEmp(@Param("projectId") String projectId, @Param("empId") String empId);
}
