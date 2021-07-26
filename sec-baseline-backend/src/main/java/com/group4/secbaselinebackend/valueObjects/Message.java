package com.group4.secbaselinebackend.valueObjects;

import lombok.Data;

/**
 * @author feng xl
 * @date 2021/7/23 0023 15:11
 */

@Data
public class Message {

    private String msg;
    public Message(String msg) {
        this.msg = msg;
    }


}
