package com.group4.secbaselinebackend.services;

import com.group4.secbaselinebackend.mapper.SendSizeMapper;
import com.group4.secbaselinebackend.models.AvgSendSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author feng xl
 * @date 2021/7/17 0017 19:42
 */
@Service
public class SendSizeService {

    @Autowired
    SendSizeMapper sendSizeMapper;

    public void selectAll() {
        List<AvgSendSize> avgSendSizes = sendSizeMapper.selectList(null);
        avgSendSizes.forEach(System.out::println);
    }
}
