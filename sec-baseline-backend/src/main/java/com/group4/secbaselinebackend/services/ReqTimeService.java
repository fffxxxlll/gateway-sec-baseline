package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.ReqTimeMapper;
import com.group4.secbaselinebackend.models.AlertInfo;
import com.group4.secbaselinebackend.models.AvgRequestTime;
import com.group4.secbaselinebackend.utils.PropertiesReader;
import com.group4.secbaselinebackend.valueObjects.AlertDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReqTimeService {


    private Integer alertCnt = 0;

    @Autowired
    ReqTimeMapper reqTimeMapper;

    @Autowired
    AlertInfoService alertInfoService;

    public AvgRequestTime selectOne(Integer id){
        AvgRequestTime avgRequestTime = reqTimeMapper.selectById(id);
        doAlert(avgRequestTime);
        return avgRequestTime;
    }



    public void doAlert(AvgRequestTime avgRequestTime) {
        Integer flag = this.judgeAlert(avgRequestTime);
        if(flag > 0){
            alertInfoService.insertAlert(createAlertInfo(flag));
        }
    }

    private Integer judgeAlert(AvgRequestTime avgRequestTime) {
        Float avgReqTime = Float.valueOf((String) PropertiesReader.getValueByKey("avg.reqtime")) ;
        Float top100ReqTime = Float.valueOf((String) PropertiesReader.getValueByKey("top100.reqtime")) ;
        Float avgTop100ReqTime = Float.valueOf((String) PropertiesReader.getValueByKey("avg.top100.reqtime")) ;
        Float avgReqTime1 = avgRequestTime.getAvgReqTime();
        if(avgReqTime1 > avgReqTime){
            this.alertCnt++;
        } else{
            this.alertCnt = 0;
        }

        if(avgReqTime1 > avgTop100ReqTime)
            return 3;
        if(avgReqTime1 > top100ReqTime && this.alertCnt >=10)
            return 3;
        if(avgReqTime1 > top100ReqTime)
            return 2;
        if(this.alertCnt >= 10)
            return 1;
        return 0;
    }

    private AlertInfo createAlertInfo(Integer flag) {
        AlertInfo alertInfo = new AlertInfo();
        alertInfo.setAlertType("请求延迟");
        alertInfo.setAlertTypeId(1);
        alertInfo.setLevel(flag);
        alertInfo.setIsDone(0);
        switch (flag){
            case 1 : alertInfo.setAlertDesc(AlertDesc.LAST_LATENCY.getCode());break;
            case 2 : alertInfo.setAlertDesc(AlertDesc.MIDDLE_LATENCY.getCode());break;
            case 3 : alertInfo.setAlertDesc(
                    AlertDesc.LAST_LATENCY.getCode() + ";" + AlertDesc.HIGH_LATENCY.getCode()
                    );break;
            default : break;
        }
        return alertInfo;
    }
}
