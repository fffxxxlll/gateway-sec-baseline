package com.group4.secbaselinebackend.controllers;

import com.group4.secbaselinebackend.models.Admin;
import com.group4.secbaselinebackend.services.AdminService;
import com.group4.secbaselinebackend.utils.jwt.JwtHelper;
import com.group4.secbaselinebackend.valueObjects.ErrorMessage;
import com.group4.secbaselinebackend.valueObjects.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;



@Controller
public class LoginController {


    @Autowired
    AdminService adminService;

    @Autowired
    private JwtHelper jwtHelper;
    @Value("${token.issuer}")
    private String issuer;
    @Value("${token.expired_in}")
    private int expiredIn;


    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody Admin admin) throws Exception {
        Integer res = adminService.judgeLogin(admin);
        switch (res){
            case 0 : return ResponseEntity.ok(new Message(ErrorMessage.ACCOUNT_NOT_EXIST.getMessage()));
            case 2 : return ResponseEntity.ok(new Message(ErrorMessage.ERROR_LOGIN__NAME_OR_PASSWORD.getMessage()));
            default: break;
        }
        return ResponseEntity.ok(jwtHelper.getToken(issuer, expiredIn, admin));
    }

    @ResponseBody
    @PostMapping("/success")
    public ResponseEntity<?> enterHome(HttpServletRequest request) {
        if("null".equals(request.getHeader("Jwt-Token")))
            return ResponseEntity.ok(new Message(ErrorMessage.INVALID_TOKEN.getMessage()));
        if(!adminService.loginCheck())
            return ResponseEntity.ok(new Message(ErrorMessage.INVALID_TOKEN.getMessage()));
        return ResponseEntity.ok(new Message("success"));
    }
}
