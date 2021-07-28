package com.group4.secbaselinebackend.models;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author feng xl
 * @date 2021/7/27 0027 16:52
 */

@Data
public class CntUserAgent {

    private BigInteger id;
    private BigInteger robotNum;
    private BigInteger totalNum;
    private BigInteger ts;
}
