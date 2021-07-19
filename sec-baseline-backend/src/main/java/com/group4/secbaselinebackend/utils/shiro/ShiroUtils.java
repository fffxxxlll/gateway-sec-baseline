package com.group4.secbaselinebackend.utils.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author feng xl
 * @date 2021/7/17 0017 20:16
 */
public class ShiroUtils {

    public static void main(String[] args) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
        subject.login(token);
        Session session = subject.getSession();

    }
}
