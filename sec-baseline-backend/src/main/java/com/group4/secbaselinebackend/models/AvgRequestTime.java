package com.group4.secbaselinebackend.models;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author feng xl
 * @date 2021/7/17 0017 18:52
 */

@Data
public class AvgRequestTime {

    private BigInteger id;
    private Float avgReqTime;
    private BigInteger ts;
}
