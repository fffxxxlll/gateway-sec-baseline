package com.group4.secbaselinebackend.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author feng xl
 * @date 2021/7/17 0017 19:47
 */
@Data
@Component
@TableName("admin")
public class Admin {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_name")
    private String userName;
    private String pwd;
    private String salt;
}
