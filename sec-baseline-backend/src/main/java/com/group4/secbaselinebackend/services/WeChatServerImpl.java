package com.group4.secbaselinebackend.services;

import com.alibaba.fastjson.JSONObject;
import com.group4.secbaselinebackend.models.AlertInfo;
import com.group4.secbaselinebackend.redis.RedisUtil;
import com.group4.secbaselinebackend.valueObjects.WeChatBean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author gxw
 * @creat 2021-07-24 15:09
 */
@Service
public class WeChatServerImpl implements WeChatServer {

    @Resource
    WeChatBean weChatBean;

    @Resource
    RestTemplate restTemplate;

    @Resource
    RedisUtil redisUtil;


//      redis中存放access_token的key值

    private final static String ACCESS_TOKEN_KEY = "accessToken";


//      redis中存放获取失败的错误信息的key值

    private final static String ERROR_KEY = "accessToken";

//创建微信的access_token
    @Scheduled(initialDelay = 10000,fixedRate = 6600000)
    @Override
    public void creatAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String path = "?grant_type={grant_type}&appid={appid}&secret={secret}";
        Map<String, String> params = new HashMap<>(3);
        params.put("grant_type", "client_credential");
        params.put("appid", weChatBean.getAppId());
        params.put("secret", weChatBean.getSecret());

        ResponseEntity<String> forObject = restTemplate.getForEntity(url + path, String.class, params);
        System.out.println(forObject.getBody());

        JSONObject jsonObject = JSONObject.parseObject(forObject.getBody());

        String accessToken = jsonObject.getString("access_token");

        System.out.println(accessToken);
        // accessToken获取成功
        if (accessToken != null) {
            //有效期设置1小时55分钟
            redisUtil.set(ACCESS_TOKEN_KEY, accessToken, 115, TimeUnit.MINUTES);
        } else {
            redisUtil.set(ERROR_KEY, forObject.getBody());
        }
    }

//    获取微信access_token
    @Override
    public String getAccessToken() {
        //大概率不会运行 除非redis连接失败或者蹦了
        if (!redisUtil.hasKey(ACCESS_TOKEN_KEY)) {
            throw new RuntimeException("获取失败，请稍后重试");
        }
        return (String) redisUtil.get(ACCESS_TOKEN_KEY);
    }

//    微信公众号告警接口
    public void sendTemplateMessage(AlertInfo alertInfo){
        String value = alertInfo.wrapInfo();
        String currentTime = alertInfo.toDateString(alertInfo.getTs());
        String at = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at;
        String data = "{\n" +
                "           \"touser\":\"oXFGm5pcNgoXccAXpj5Hn4NAFioU\",\n" +
                "           \"template_id\":\"MxgUYZxVtI70F7djmk0eOLpQUvJ0YZ2lGWczxKzrMfU\",\n" +
                "           \"url\":\"http://weixin.qq.com/download\",            \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"有新的告警信息！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"time\":{\n" +
                "                       \"value\":\""+currentTime+"\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"result\": {\n" +
                "                       \"value\":\""+value+"\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "}";
//        解决中文乱码问题
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String result = restTemplate.postForObject(url, data, String.class);
        System.out.println(result);

    }

}

