package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.AlertInfoMapper;
import com.group4.secbaselinebackend.models.AlertInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author feng xl
 * @date 2021/7/27 0027 17:08
 */

@Service
public class AlertInfoService {

    @Autowired
    private AlertInfoMapper alertInfoMapper;


    public Integer insertAlert(AlertInfo alertInfo) {
        return alertInfoMapper.insert(alertInfo);
    }

    public Integer delAlertById(Integer id) {
        return alertInfoMapper.deleteById(id);
    }
}
