package com.group4.secbaselinebackend.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group4.secbaselinebackend.mapper.AdminMapper;
import com.group4.secbaselinebackend.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author feng xl
 * @date 2021/7/17 0017 20:38
 */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin selectOneByUserName(Admin admin) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUserName, admin.getUserName()));
    }
}
