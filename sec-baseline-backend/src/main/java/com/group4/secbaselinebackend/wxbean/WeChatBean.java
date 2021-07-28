package com.group4.secbaselinebackend.wxbean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author gxw
 * @creat 2021-07-24 13:58
 */
@Data
@Component
//@PropertySource(value = "classpath:application.yaml")
//@ConfigurationProperties(prefix = "wechat")
public class WeChatBean {

    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;
}
