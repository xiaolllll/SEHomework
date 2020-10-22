package com.example.test.mapper;

import com.example.test.bean.NotifyInfoBean;

import java.util.List;

public interface NotifyInfoMapper {

    public NotifyInfoBean selectNotifyInfoByNotifyId(int notifyId);
    //根据接受者Id查询信息
    public NotifyInfoBean getNotifyInfoByReceiverID(String receiverId);

    public List<NotifyInfoBean> getNotifyInfoBySenderID(String empID);//查看员工通知信息

    //添加新的信息
    public int insertNotifyInfo(NotifyInfoBean notifyInfoBean);

    public int updateNotifyInfo(NotifyInfoBean notifyInfoBean);

    public int deleteNotifyInfoAllByReceiverID(String receiverID);
}
