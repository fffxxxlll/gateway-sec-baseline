package com.group4.secbaselinebackend.models;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author feng xl
 * @date 2021/7/17 0017 19:02
 */
@Data
public class AvgResponseTime {

    private BigInteger id;
    private Float avgResTime;
    private BigInteger ts;
}
