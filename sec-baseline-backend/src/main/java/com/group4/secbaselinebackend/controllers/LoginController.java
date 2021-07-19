package com.group4.secbaselinebackend.controllers;

import com.group4.secbaselinebackend.models.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author feng xl
 * @date 2021/7/17 0017 19:49
 */

@Controller
public class LoginController {


    @ResponseBody
    @GetMapping("/")
    public String visitHome() {

        return "home";
    }

    @ResponseBody
    @PostMapping("/login")
    public String doLogin(@RequestBody Admin admin){


        return admin.toString();
    }
}
