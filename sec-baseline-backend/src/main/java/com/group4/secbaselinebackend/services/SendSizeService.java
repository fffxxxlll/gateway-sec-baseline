package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.SendSizeMapper;
import com.group4.secbaselinebackend.models.AlertInfo;
import com.group4.secbaselinebackend.models.AvgSendSize;
import com.group4.secbaselinebackend.utils.PropertiesUtil;
import com.group4.secbaselinebackend.valueObjects.AlertDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class SendSizeService {

    private Integer alertCnt = 0;

    @Autowired
    SendSizeMapper sendSizeMapper;


    @Autowired
    AlertInfoService alertInfoService;
//    public void selectAll() {
//        List<AvgSendSize> avgSendSizes = sendSizeMapper.selectList(null);
//        avgSendSizes.forEach(System.out::println);
//    }

    public AvgSendSize selectOne(Integer id) {
        AvgSendSize avgSendSize = sendSizeMapper.selectById(id);
        doAlert(avgSendSize);
        return avgSendSize;
    }


    public void doAlert(AvgSendSize avgSendSize) {
        Integer flag = this.judgeAlert(avgSendSize);
        if(flag > 0){
            alertInfoService.insertAlert(createAlertInfo(flag, avgSendSize));
        }
    }

    private Integer judgeAlert(AvgSendSize avgSendSize) {
        Integer avgSize = Integer.parseInt((String) PropertiesUtil.getValueByKey("avg.sendsize"));
        Integer top20SendSize = Integer.parseInt((String) PropertiesUtil.getValueByKey("top20.sendsize"));
        Integer avgTop20SendSize = Integer.parseInt((String) PropertiesUtil.getValueByKey("avg.top20.sendsize"));
        Integer avgSendSize1 = Integer.parseInt(avgSendSize.getAvgSendSize() + "");
        if(avgSendSize1 > avgSize){
            this.alertCnt++;
        } else{
            this.alertCnt = 0;
        }

        if(avgSendSize1 > avgTop20SendSize)
            return 3;
        if(avgSendSize1 > top20SendSize && this.alertCnt >=10)
            return 3;
        if(avgSendSize1 > top20SendSize)
            return 2;
        if(this.alertCnt >= 10)
            return 1;
        return 0;
    }

    private AlertInfo createAlertInfo(Integer flag, AvgSendSize avgSendSize) {
        AlertInfo alertInfo = new AlertInfo();
        alertInfo.setAlertType("大量数据流");
        alertInfo.setAlertTypeId(3);
        alertInfo.setLevel(flag);
        alertInfo.setIsDone(0);
        alertInfo.setTs(avgSendSize.getTs());
        switch (flag){
            case 1 : alertInfo.setAlertDesc(AlertDesc.LAST_OVERSIZE_DATA_FLOW.getCode());break;
            case 2 : alertInfo.setAlertDesc(AlertDesc.MIDDLE_DATA_FLOW.getCode());break;
            case 3 : alertInfo.setAlertDesc(
                    AlertDesc.LAST_OVERSIZE_DATA_FLOW.getCode() + ";" + AlertDesc.MASSIVE_DATA_FLOW.getCode()
            );break;
            default : break;
        }
        return alertInfo;
    }
}
