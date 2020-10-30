package com.example.test.mapper;
import com.example.test.bean.TaskContributionBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper

public interface TaskContributionMapper {
    int insertTaskContribution(TaskContributionBean taskContributionBean);
}
