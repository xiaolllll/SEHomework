package com.example.test.service;

import com.example.test.bean.NotifyInfoBean;

public interface NotifyService {
    boolean addNotify(NotifyInfoBean notifyInfoBean);//新增信息
    boolean handleNotify(String notifyInfoID);//处理通知
    boolean deleteHasRead(String empID);//删除已读通知
}
