package com.example.test.controller;

import com.example.test.Jwt.JwtUtils;
import com.example.test.bean.EmployeeBean;
import com.example.test.service.NotifyService;
import com.example.test.util.NotifyUtil;
import com.example.test.util.ServiceUtil;
import com.example.test.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin
public class NotifyController {
    @Autowired
    private NotifyService notifyService;
    @RequestMapping("/deleteNotify")
    @ResponseBody
    public JSONResult deleteNotify( @RequestBody int NotifyID) {
        String result=notifyService.deleteNotify(NotifyID);
        if(result.contains(ServiceUtil.SUCCESS)){
            return JSONResult.build(200,result,null);
        }else {
            System.out.println(result);
            return JSONResult.build(500,result,null);
        }
    }
}
