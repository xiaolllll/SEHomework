package com.example.test.controller;

import com.example.test.bean.EmployeeBean;
import com.example.test.bean.NotifyInfoBean;
import com.example.test.service.DataQueryService;
import com.example.test.service.NotifyService;
import com.example.test.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class NotifyController {
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private DataQueryService dataQueryService;
    @RequestMapping("/deleteNotify")
    @ResponseBody
    public JSONResult deleteNotify( @RequestBody NotifyInfoBean notifyInfoBean) {
        String result=notifyService.deleteNotify(notifyInfoBean.getNotifyInfoId());
        if(result.contains(ServiceUtil.SUCCESS)){
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }

    @RequestMapping("/getEmpNotify")
    @ResponseBody
    public JSONResult getEmpNotify( @RequestBody EmployeeBean employeeBean) {
        List<NotifyInfoBean> list=dataQueryService.getEmpNotifyInfo(employeeBean.getEmpId());
        if(list==null){
            return JSONResult.errorMessage("员工不存在");
        }else {
            return JSONResult.ok(list);
        }
    }
}
