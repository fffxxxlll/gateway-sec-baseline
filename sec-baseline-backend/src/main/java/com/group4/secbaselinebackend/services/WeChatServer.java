package com.group4.secbaselinebackend.services;

/**
 * @author gxw
 * @creat 2021-07-24 15:17
 */
public interface WeChatServer {
//    创建token
    public void creatAccessToken();

//    获取token
    public String getAccessToken();

}
