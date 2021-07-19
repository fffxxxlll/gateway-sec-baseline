package com.group4.secbaselinebackend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.group4.secbaselinebackend.mapper.AdminMapper;
import com.group4.secbaselinebackend.mapper.ResTimeMapper;
import com.group4.secbaselinebackend.models.Admin;
import com.group4.secbaselinebackend.services.ReqTimeService;
import com.group4.secbaselinebackend.services.ResTimeService;
import com.group4.secbaselinebackend.services.SendSizeService;
import com.group4.secbaselinebackend.utils.json.JsonUtils;
import org.apache.el.parser.AstMinus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SecBaselineBackendApplicationTests {


    @Autowired
    private ReqTimeService reqTimeService;
    @Autowired
    private ResTimeService resTimeService;
    @Autowired
    private SendSizeService sendSizeService;

    @Test
    void contextLoads() {

    }

    @Test
    void testReqTime(){
//        reqTimeService.selectAll();
//        resTimeService.selectAll();
        sendSizeService.selectAll();
    }

    @Test
    void testJackson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        Admin admin = new Admin();
        admin.setId(1);
        admin.setUserName("admin");
        admin.setPwd("admin");
        String s = JsonUtils.objectToJsonString(admin);
        System.out.println(s);
    }

    @Autowired
    AdminMapper adminMapper;

    @Test
    public void testAdmin() {
        Admin admin = new Admin();
        admin.setUserName("admin");
        admin.setPwd("admin");
        Admin admin1 = adminMapper.selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUserName, admin.getUserName()));

    }
}
