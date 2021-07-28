package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.ResTimeMapper;
import com.group4.secbaselinebackend.models.AlertInfo;
import com.group4.secbaselinebackend.models.AvgRequestTime;
import com.group4.secbaselinebackend.models.AvgResponseTime;
import com.group4.secbaselinebackend.utils.PropertiesReader;
import com.group4.secbaselinebackend.valueObjects.AlertDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResTimeService {

    @Autowired
    private ResTimeMapper resTimeMapper;

    @Autowired
    private AlertInfoService alertInfoService;

    private Integer alertCnt = 0;

    public AvgResponseTime selectOne(Integer id){
        AvgResponseTime avgResponseTime = resTimeMapper.selectById(id);
        doAlert(avgResponseTime);
        return avgResponseTime;
    }

    public void doAlert(AvgResponseTime avgResponseTime) {
        Integer flag = this.judgeAlert(avgResponseTime);
        if(flag > 0){
            alertInfoService.insertAlert(createAlertInfo(flag, avgResponseTime));
        }
    }

    private Integer judgeAlert(AvgResponseTime avgResponseTime) {
        Float avgResTime = Float.valueOf((String) PropertiesReader.getValueByKey("avg.restime")) ;
        Float top100ResTime = Float.valueOf((String) PropertiesReader.getValueByKey("top100.restime")) ;
        Float avgTop100ResTime = Float.valueOf((String) PropertiesReader.getValueByKey("avg.top100.restime")) ;
        Float avgResTime1 = avgResponseTime.getAvgResTime();
        if(avgResTime1 > avgResTime){
            this.alertCnt++;
        } else{
            this.alertCnt = 0;
        }

        if(avgResTime1 > avgTop100ResTime)
            return 3;
        if(avgResTime1 > top100ResTime && this.alertCnt >=10)
            return 3;
        if(avgResTime1 > top100ResTime)
            return 2;
        if(this.alertCnt >= 10)
            return 1;
        return 0;
    }

    private AlertInfo createAlertInfo(Integer flag, AvgResponseTime avgResponseTime) {
        AlertInfo alertInfo = new AlertInfo();
        alertInfo.setAlertType("响应延迟");
        alertInfo.setAlertTypeId(2);
        alertInfo.setLevel(flag);
        alertInfo.setIsDone(0);
        alertInfo.setTs(avgResponseTime.getTs());
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
