package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.CntUserAgentMapper;
import com.group4.secbaselinebackend.models.AlertInfo;
import com.group4.secbaselinebackend.models.CntUserAgent;
import com.group4.secbaselinebackend.utils.PropertiesUtil;
import com.group4.secbaselinebackend.valueObjects.AlertDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author feng xl
 * @date 2021/7/27 0027 17:07
 */

@Service
public class CntUserAgentService {


    private Integer alertCnt = 0;

    @Autowired
    CntUserAgentMapper cntUserAgentMapper;

    @Autowired
    AlertInfoService alertInfoService;

    public CntUserAgent selectOne(Integer id) {
        CntUserAgent cntUserAgent = cntUserAgentMapper.selectById(id);
        doAlert(cntUserAgent);
        return cntUserAgent;
    }

    public void doAlert(CntUserAgent cntUserAgent) {
        Integer flag = this.judgeAlert(cntUserAgent);
        if(flag > 0){
            alertInfoService.insertAlert(createAlertInfo(flag, cntUserAgent));
        }
    }

    private Integer judgeAlert(CntUserAgent cntUserAgent) {
        Integer avgRobotNum = Integer.parseInt((String) PropertiesUtil.getValueByKey("avg.robotnum.ps"));
        Integer top20RobotNum = Integer.parseInt((String) PropertiesUtil.getValueByKey("top20.robotnum.ps"));
        Integer avgTop20RobotNum = Integer.parseInt((String) PropertiesUtil.getValueByKey("avg.top20.robotnum.ps"));
        Integer avgRobotNum1 = Integer.parseInt(cntUserAgent.getRobotNum() + "");
        if(avgRobotNum1 > avgRobotNum){
            this.alertCnt++;
        } else{
            this.alertCnt = 0;
        }

        if(avgRobotNum1 > avgTop20RobotNum)
            return 3;
        if(avgRobotNum1 > top20RobotNum && this.alertCnt >= 10)
            return 3;
        if(avgRobotNum1 > top20RobotNum)
            return 2;
        if(this.alertCnt >= 10)
            return 1;
        return 0;
    }

    private AlertInfo createAlertInfo(Integer flag, CntUserAgent cntUserAgent) {
        AlertInfo alertInfo = new AlertInfo();
        alertInfo.setAlertType("脚本数量过多");
        alertInfo.setAlertTypeId(4);
        alertInfo.setLevel(flag);
        alertInfo.setIsDone(0);
        alertInfo.setTs(cntUserAgent.getTs());
        switch (flag){
            case 1 : alertInfo.setAlertDesc(AlertDesc.LAST_MANY_ROBOT.getCode());break;
            case 2 : alertInfo.setAlertDesc(AlertDesc.MIDDLE_NUM_ROBOT.getCode());break;
            case 3 : alertInfo.setAlertDesc(
                    AlertDesc.LAST_MANY_ROBOT.getCode() + ";" + AlertDesc.MASSIVE_NUM_ROBOT.getCode()
                    );break;
            default : break;
        }
        return alertInfo;
    }

}
