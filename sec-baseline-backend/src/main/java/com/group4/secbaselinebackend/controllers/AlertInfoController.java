package com.group4.secbaselinebackend.controllers;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group4.secbaselinebackend.models.AlertInfo;
import com.group4.secbaselinebackend.services.AlertInfoService;
import com.group4.secbaselinebackend.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

/**
 * @author feng xl
 * @date 2021/7/28 0028 15:15
 */

@RestController
public class AlertInfoController {

    @Autowired
    AlertInfoService alertInfoService;

    @GetMapping(value = "/alerthistory")
    public Page<AlertInfo> showHistoryPage(@RequestParam Integer page) {
        return alertInfoService.selectByPage(page);
    }

    @PostMapping(value = "/modifyconfig")
    public Object modifyConf(@RequestBody JSONObject json) {
//        return alertInfoService.modifyConfig(key, value);
        alertInfoService.modifyConfig(json.getString("key"), json.getString("value"));
        return PropertiesUtil.getValueByKey(json.getString("key"));
    }

    @GetMapping(value = "/listproperties")
    public Properties listProperties() {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        return propertiesUtil.listProperties();
    }

    @GetMapping(value = "/getpagebytype")
    public Page<AlertInfo> getPageByType(@RequestParam Integer type, @RequestParam Integer page) {
        return alertInfoService.getPageByType(type, page);
    }


}
