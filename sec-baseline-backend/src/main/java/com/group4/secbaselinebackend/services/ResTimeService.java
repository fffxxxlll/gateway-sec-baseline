package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.ResTimeMapper;
import com.group4.secbaselinebackend.models.AvgResponseTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author feng xl
 * @date 2021/7/17 0017 19:33
 */
@Service
public class ResTimeService {

    @Autowired
    private ResTimeMapper resTimeMapper;

    public void selectAll() {
        List<AvgResponseTime> avgResponseTimes = resTimeMapper.selectList(null);
        avgResponseTimes.forEach(System.out::println);
    }
}
