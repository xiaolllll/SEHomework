package com.example.test.service;

import com.example.test.bean.NotifyInfoBean;

public interface NotifyService {
    String addNotify(String receiverID,String senderID,String info,int notifyType);//新增信息
    String handleNotify(String notifyInfoID);//处理通知
    String deleteHasRead(String empID);//删除已读通知
}
