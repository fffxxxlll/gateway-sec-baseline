package com.group4.secbaselinebackend.controllers;

import com.group4.secbaselinebackend.models.*;
import com.group4.secbaselinebackend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gxw
 * @creat 2021-07-20 18:18
 */

@Controller
public class WebController {
    @Autowired
    private ReqTimeService reqTimeService;

    @Autowired
    private ResTimeService resTimeService;

    @Autowired
    private SendSizeService sendSizeService;

    @Autowired
    private CntHttpCodeService httpCodeService;
    @Autowired
    private CntUserAgentService cntUserAgentService;

//    处理平均请求时间图表
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/initreq")
    public List<AvgRequestTime> initReqData(){
        List<AvgRequestTime> initData = new ArrayList<AvgRequestTime>();
//        ReqTimeService reqTimeService = new ReqTimeService();   要采用注入的方式创建实例
        for(int i = 1;i<=30;i++){
            initData.add(reqTimeService.selectOne(i));
        }
        return initData;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/reqgetinfo")
    public AvgRequestTime getReqInfo(@RequestParam("id") String id){
        int temp = Integer.parseInt(id);
        return reqTimeService.selectOne(temp);
    }
//    @CrossOrigin
//    @RequestMapping("/reqview")
//    public String html1(){
//        return "reqview";
//    }

//    处理平均响应时间表
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/initres")
    public List<AvgResponseTime> initResData(){
        List<AvgResponseTime> initResData = new ArrayList<AvgResponseTime>() ;
        for(int i = 1;i<=30;i++){
            initResData.add(resTimeService.selectOne(i));
        }
        return initResData;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/resgetinfo")
    public AvgResponseTime getResInfo(@RequestParam("id") String id){
        int temp = Integer.parseInt(id);
        return resTimeService.selectOne(temp);
    }
//    @CrossOrigin
//    @RequestMapping("/resview")
//    public String html2(){
//        return "resview";
//    }


    //    处理平均发送数据量表
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/initsen")
    public List<AvgSendSize> initSenData(){
        List<AvgSendSize> initSenData = new ArrayList<AvgSendSize>() ;
        for(int i = 1;i<=30;i++){
            initSenData.add(sendSizeService.selectOne(i));
        }
        return initSenData;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/sengetinfo")
    public AvgSendSize getSenInfo(@RequestParam("id") String id){
        int temp = Integer.parseInt(id);
        return sendSizeService.selectOne(temp);
    }

    @ResponseBody
    @RequestMapping("/getcodeinfo")
    public CntHttpCode getCodeInfo(@RequestParam("id") String id){
        int temp = Integer.parseInt(id);
        return httpCodeService.selectOne(temp);
    }

    @ResponseBody
    @RequestMapping("/getagentinfo")
    public CntUserAgent getAgentInfo(@RequestParam("id") String id){
        int temp = Integer.parseInt(id);
        return cntUserAgentService.selectOne(temp);
    }

}
