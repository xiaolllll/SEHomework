package com.example.test.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author: Mr.Yang
 * @create: 2020-02-13 21:19
 **/

public class JwtUtils {
    //签名私钥
    private String key="userLogin";
    //签名失效时间
    private Long failureTime = 3600000L;

    /**
     * 设置认证token
     *
     * @param id      用户登录ID
     * @return
     */
    public String createJwt(String id) {

        //1、设置失效时间啊
        long now = System.currentTimeMillis();  //毫秒
        long exp = now + failureTime;

        //2、创建JwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setIssuedAt(new Date())
                //设置签名防止篡改
                .signWith(SignatureAlgorithm.HS256, key);

        //3、根据map设置claims
        jwtBuilder.setExpiration(new Date(exp));

        //4、创建token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims parseJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }

    public static String analysis(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            System.out.println("系统未捕捉到请求头信息");
        }
        Claims claims = new JwtUtils().parseJwt(authorization);
        String userId = claims.getId();
        return userId;
    }
}