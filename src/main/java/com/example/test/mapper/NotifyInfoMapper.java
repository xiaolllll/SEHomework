package com.example.test.mapper;

import com.example.test.bean.NotifyInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
 public interface NotifyInfoMapper {

    NotifyInfoBean selectNotifyInfoByNotifyId(int notifyId);
    //根据接受者Id查询信息
    List<NotifyInfoBean> getNotifyInfoByReceiverID(String receiverId);

     List<NotifyInfoBean> getNotifyInfoBySenderID(String empID);//查看员工通知信息

    //添加新的信息
     int insertNotifyInfo(NotifyInfoBean notifyInfoBean);

     int updateNotifyInfo(NotifyInfoBean notifyInfoBean);

     int deleteNotifyInfoAllByReceiverID(String receiverID);

     int deleteNotifyInfo(int notifyInfoId);
}
