package com.example.test.serviceImpl;

import com.example.test.bean.NotifyInfoBean;
import com.example.test.mapper.NotifyInfoMapper;
import com.example.test.service.NotifyService;
import com.example.test.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private NotifyInfoMapper notifyInfoMapper;
    @Override
    public String addNotify(String receiverID,String senderID,String info,int notifyType) {
        NotifyInfoBean notifyInfoBean = new NotifyInfoBean();

        notifyInfoBean.setSenderId(senderID);
        notifyInfoBean.setReceiverId(receiverID);
        notifyInfoBean.setInfoHasRead(false);
        notifyInfoBean.setNotifyType(notifyType);
        notifyInfoBean.setInfo(info);

        int result = notifyInfoMapper.insertNotifyInfo(notifyInfoBean);
        if(result!=1){
            return ServiceUtil.FAILURE+"数据库插入通知失败";
        }
        else {
            return ServiceUtil.SUCCESS;
        }
    }

    @Override
    public String handleNotify(String notifyInfoID) {
        //NotifyInfoBean=notifyInfoMapper
        /**
         * 没写完需要查询单个Notify
         */
    }

    @Override
    public String deleteHasRead(String empID) {
        /**
         * 没写完需要删除特定的empID对应的已读信息
         */
    }
}
