package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.ReqTimeMapper;
import com.group4.secbaselinebackend.models.AvgRequestTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author feng xl
 * @date 2021/7/17 0017 19:21
 */
@Service
public class ReqTimeService {

    @Autowired
    ReqTimeMapper reqTimeMapper;

    public void selectAll(){
        List<AvgRequestTime> reqTimes = reqTimeMapper.selectList(null);
        reqTimes.forEach(System.out::println);
    }
}
