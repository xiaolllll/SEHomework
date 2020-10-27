package com.example.test.controller;

import com.example.test.Jwt.JwtUtils;
import com.example.test.bean.EmployeeBean;
import com.example.test.bean.UserBean;
import com.example.test.service.LoginService;
import com.example.test.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.security.util.PendingException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    //将Service注入Web层
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public String show(){
        return "login";
    }

    @RequestMapping("/loginIn")
    @ResponseBody
    public JSONResult login(String name,String password){
        EmployeeBean employeeBean=loginService.loginIn(name,password);
        if(employeeBean == null){
            return JSONResult.errorMessage("用户名或密码错误");
        }
        else {
            String token = new JwtUtils().createJwt(employeeBean.getEmpId());
            return JSONResult.build(500,token,employeeBean);
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public JSONResult profile(HttpServletRequest request) throws PendingException, Exception {

        /**
         * 从请求头信息中获取token数据
         *   1.获取请求头信息：名称=Authorization(前后端约定)
         *   2.替换Bearer+空格
         *   3.解析token
         *   4.获取claims
         */
        //1.获取请求头信息：名称=Authorization(前后端约定)
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
//            throw new PendingException(ResCode.UNAUTHENTICATED);
            //系统未捕捉到请求头信息
            System.out.println("系统未捕捉到请求头信息");
        }
        //2.替换Bearer+空格
        String token = authorization.replace("Bearer ", "");

        //3.解析token
        Claims claims = new JwtUtils().parseJwt(token);
        //4.获取claims
        String userId = claims.getId();

        //  String userId = "U01";
        return null;
    }
}