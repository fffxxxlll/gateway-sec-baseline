package com.group4.secbaselinebackend.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author feng xl
 * @date 2021/7/27 0027 16:56
 */

@Data
public class AlertInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;
    private Integer alertTypeId;
    private String alertType;
    private String alertDesc;
    private Integer level;
    private Integer isDone;
    private BigInteger ts;
}
