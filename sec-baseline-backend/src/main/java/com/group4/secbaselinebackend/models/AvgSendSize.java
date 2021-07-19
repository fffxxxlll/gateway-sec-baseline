package com.group4.secbaselinebackend.models;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author feng xl
 * @date 2021/7/17 0017 19:39
 */
@Data
public class AvgSendSize {

    private BigInteger id;
    private BigInteger avgSendSize;
    private BigInteger ts;
}
