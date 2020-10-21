package com.example.test.mapper;

import com.example.test.bean.NotifyInfoBean;

public interface NotifyInfoMapper {
    //根据接受者Id查询信息
    public NotifyInfoBean getNotifyInfoByReceiverID(String receiverId);

    //添加新的信息
    public int insertNotifyInfo(NotifyInfoBean notifyInfoBean);
}
