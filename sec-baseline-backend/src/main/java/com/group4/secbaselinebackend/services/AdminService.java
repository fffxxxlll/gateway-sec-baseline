package com.group4.secbaselinebackend.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group4.secbaselinebackend.exceptions.UnAuthorizedException;
import com.group4.secbaselinebackend.infrastructure.usercontext.IdentityContext;
import com.group4.secbaselinebackend.mapper.AdminMapper;
import com.group4.secbaselinebackend.models.Admin;
import com.group4.secbaselinebackend.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private Encryption encryption;

    @Resource(name = "tokenIdentityContext")
    private IdentityContext identityContext;

    public Admin selectOneByUserName(Admin admin) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getUserName, admin.getUserName()));
    }

    public Integer judgeLogin(Admin admin) throws Exception {
        Admin admin1 = selectOneByUserName(admin);
        if(admin1 == null){
            return 0;
        }
        return judgeAdminByPwd(admin1, admin.getPwd());
    }

    private Integer judgeAdminByPwd(Admin admin, String pwd) throws Exception {
        if(admin.getPwd().equals(encryption.getPassword(pwd, admin.getSalt()))){
            return 1;
        }else
            return 2;
    }

    public Boolean loginCheck() {
        Admin admin = null;
        try {
            admin = (Admin) identityContext.getIdentity();
        } catch (UnAuthorizedException e) {
            e.printStackTrace();
        }
        return admin != null;
    }

    public Admin insertOne() throws Exception {
        String name = "admin";
        String pwd = "admin";
        String salt = encryption.getSalt();
        String encPwd = encryption.getPassword(pwd, salt);
        Admin admin = new Admin();
        admin.setUserName(name);
        admin.setPwd(encPwd);
        admin.setSalt(salt);
        adminMapper.insert(admin);
        return admin;
    }
}
