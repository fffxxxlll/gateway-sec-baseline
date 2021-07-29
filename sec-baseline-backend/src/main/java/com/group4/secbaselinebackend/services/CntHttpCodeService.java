package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.CntHttpCodeMapper;
import com.group4.secbaselinebackend.models.AlertInfo;
import com.group4.secbaselinebackend.models.CntHttpCode;
import com.group4.secbaselinebackend.utils.PropertiesUtil;
import com.group4.secbaselinebackend.valueObjects.AlertDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author feng xl
 * @date 2021/7/27 0027 17:04
 */

@Service
public class CntHttpCodeService {

    private int alert404Cnt = 0;
    private int alert500Cnt = 0;

    @Autowired
    private CntHttpCodeMapper cntHttpCodeMapper;

    @Autowired
    AlertInfoService alertInfoService;

    public CntHttpCode selectOne(Integer id) {
        CntHttpCode cntHttpCode = cntHttpCodeMapper.selectById(id);
        doAlert(cntHttpCode);
        return cntHttpCode;
    }

    public void doAlert(CntHttpCode cntHttpCode) {
        Integer flag = this.judgeAlert(cntHttpCode);
        if(flag > 0){
            alertInfoService.insertAlert(createAlertInfo(flag, cntHttpCode));
        }
    }

    private Integer judgeAlert(CntHttpCode cntHttpCode) {
        Integer avgCnt404 = Integer.parseInt((String) PropertiesUtil.getValueByKey("avg.cnt404"));
        Integer  top20Cnt404 = Integer.parseInt((String) PropertiesUtil.getValueByKey("top20.cnt404"));
        Integer avgCnt500 = Integer.parseInt((String) PropertiesUtil.getValueByKey("avg.cnt500"));
        Integer  aAvgCnt404 = Integer.parseInt(cntHttpCode.getCnt400() + "");
        Integer  aAvgCnt500 = Integer.parseInt(cntHttpCode.getCnt500() + "");
        if(aAvgCnt500 > avgCnt500){
            this.alert500Cnt++;
        } else{
            this.alert500Cnt = 0;
        }
        if(aAvgCnt404 > avgCnt404){
            this.alert404Cnt++;
        } else{
            this.alert404Cnt = 0;
        }
        if(alert500Cnt >= 4)
            return 4;
        if(aAvgCnt404 > top20Cnt404 && this.alert404Cnt >= 10)
            return 3;
        if(aAvgCnt404 > top20Cnt404)
            return 2;
        if(this.alert404Cnt >= 10)
            return 1;
        return 0;
    }

    private AlertInfo createAlertInfo(Integer flag, CntHttpCode cntHttpCode) {
        AlertInfo alertInfo = new AlertInfo();
        alertInfo.setAlertType("请求状态异常");
        alertInfo.setAlertTypeId(5);
        alertInfo.setLevel(flag);
        alertInfo.setIsDone(0);
        alertInfo.setTs(cntHttpCode.getTs());
        switch (flag){
            case 1 : alertInfo.setAlertDesc(AlertDesc.LAST_404_CODE.getCode());break;
            case 2 : alertInfo.setAlertDesc(AlertDesc.MIDDLE_NUM_404.getCode());break;
            case 3 : alertInfo.setAlertDesc(
                    AlertDesc.LAST_404_CODE.getCode() + ";" + AlertDesc.MASSIVE_NUM_404.getCode()
            );break;
            case 4 : alertInfo.setAlertDesc(AlertDesc.LAST_INTERNAL_500.getCode());break;
            default : break;
        }
        return alertInfo;
    }

}
