package com.example.test.mapper;

import com.example.test.bean.ProjectBean;
import com.example.test.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    ProjectBean getProInfoByProId(String proId);

}