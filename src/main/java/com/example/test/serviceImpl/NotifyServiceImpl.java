package com.example.test.serviceImpl;

import com.example.test.bean.NotifyInfoBean;
import com.example.test.service.NotifyService;

public class NotifyServiceImpl implements NotifyService {
    @Override
    public String addNotify(NotifyInfoBean notifyInfoBean) {
        return false;
    }

    @Override
    public String handleNotify(String notifyInfoID) {
        return false;
    }

    @Override
    public String deleteHasRead(String empID) {
        return false;
    }
}
