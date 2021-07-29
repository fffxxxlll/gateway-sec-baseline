package com.group4.secbaselinebackend.models;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author feng xl
 * @date 2021/7/27 0027 16:55
 */

@Data
public class CntHttpCode {

    private BigInteger id;
    @TableField(value = "cnt_400")
    private BigInteger cnt400;
    @TableField(value = "cnt_500")
    private BigInteger cnt500;
    private BigInteger cntTotal;
    private BigInteger ts;
}
