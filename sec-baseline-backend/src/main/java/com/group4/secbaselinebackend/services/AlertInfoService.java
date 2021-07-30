package com.group4.secbaselinebackend.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group4.secbaselinebackend.mapper.AlertInfoMapper;
import com.group4.secbaselinebackend.models.AlertInfo;
import com.group4.secbaselinebackend.utils.PropertiesUtil;
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

    public Page<AlertInfo> selectByPage(Integer pageNum) {
        Page<AlertInfo> page = new Page<>(pageNum, 10);
        Page<AlertInfo> selectPage = alertInfoMapper.selectPage(page, null);
        return selectPage;
    }

    public Page<AlertInfo> getPageByType(Integer type, Integer pageNum) {
        Page<AlertInfo> page = new Page<>(pageNum, 10);
        QueryWrapper<AlertInfo> queryWrapper = new QueryWrapper<>();
        return alertInfoMapper.selectPage(page, queryWrapper.lambda().eq(AlertInfo::getAlertTypeId, type));
    }


//    public Integer handleAlertById(Integer id) {
//        alertInfoMapper.
//        return 1;
//    }

    public Integer modifyConfig(String key, String value) {
        Integer res = PropertiesUtil.setValueOfKey(key, value);
        return res;
    }
}
