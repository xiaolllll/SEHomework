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

    /**
     * todo
     * @param notifyInfoBean
     * @return
     */
    //修改已有的信息
    public int updateNotifyInfo(NotifyInfoBean notifyInfoBean);

    /**
     * to do
     */
    //删除删除特定的empID对应的已读信息
    public int deleteNotifyInfoByReceiverID(String receiverId);
}
