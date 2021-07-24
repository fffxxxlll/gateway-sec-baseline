package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.ReqTimeMapper;
import com.group4.secbaselinebackend.models.AvgRequestTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReqTimeService {

    @Autowired
    ReqTimeMapper reqTimeMapper;


    public AvgRequestTime selectOne(Integer id){
        return reqTimeMapper.selectById(id);
    }
}
