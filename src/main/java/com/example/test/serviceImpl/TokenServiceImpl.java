package com.example.test.serviceImpl;

import com.example.test.bean.EmployeeBean;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl {

    public String getToken(EmployeeBean user) {
//        Date start = new Date();
////        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
////        Date end = new Date(currentTime);
////        String token = "";
////
////        token = JWT.create().withAudience(user.getEmpId()).withIssuedAt(start).withExpiresAt(end)
////                .sign(Algorithm.HMAC256(user.getEmpPassword()));
////        return token;
        return null;
    }
}