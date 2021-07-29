package com.group4.secbaselinebackend.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public String wrapInfo() {
        String wrap =
                      "\n风险类型 : " + alertType +
                      "\n风险详情 : " + alertDesc +
                      "\n风险级别 : " + level +
                      "\n时间     : " + toDateString(ts);
        return wrap;
    }

    private String toDateString(BigInteger ts) {
        Date date = new Date(Long.parseLong(ts+""));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
