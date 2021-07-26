package com.group4.secbaselinebackend.infrastructure.usercontext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.secbaselinebackend.models.Admin;
import com.group4.secbaselinebackend.utils.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/9/1.
 */

@Component(value = "tokenIdentityContext")
public class TokenIdentityContext implements IdentityContext {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private JwtHelper jwtHelper;
    @Value("${token.key}")
    private String key;

    //获取当前token的payload
    @Override
    public Object getIdentity() {
        isAuthenticated();
        Object object;
        String token = httpServletRequest.getHeader("Jwt-Token");
        if (token == null) {
            token = httpServletRequest.getParameter("Jwt_Token");
        }
        try {
            object = new ObjectMapper().readValue(jwtHelper.convertTokenToJsonNode(token).path("identity").traverse(), Admin.class);
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
        return object;
    }

    //认证当前请求的token
    @Override
    public boolean isAuthenticated() {
        String token = httpServletRequest.getHeader("Jwt-Token");
        if (token == null) {
            token = httpServletRequest.getParameter("Jwt_Token");
        }
        jwtHelper.parseJWT(token);
        return true;
    }

    @Override
    public String getAuthKey() {
        return key;
    }


}
