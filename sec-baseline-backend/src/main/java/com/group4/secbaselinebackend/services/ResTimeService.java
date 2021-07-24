package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.ResTimeMapper;
import com.group4.secbaselinebackend.models.AvgResponseTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResTimeService {

    @Autowired
    private ResTimeMapper resTimeMapper;



    public AvgResponseTime selectOne(Integer id){
        return resTimeMapper.selectById(id);
    }
}
