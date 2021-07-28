package com.group4.secbaselinebackend.valueObjects;

/**
 * @author feng xl
 * @date 2021/7/27 0027 19:11
 */
public enum AlertDesc {

    /*延迟*/
    LAST_LATENCY("持续性延迟"),
    MIDDLE_LATENCY("延迟较高"),
    HIGH_LATENCY("延迟过高"),
    /*流量*/
    LAST_OVERSIZE_DATA_FLOW("持续性较多数据流"),
    MIDDLE_DATA_FLOW("数据流较大"),
    MASSIVE_DATA_FLOW("大型数据流"),

    /*脚本数量*/
    LAST_MANY_ROBOT("持续性过多脚本"),
    MIDDLE_NUM_ROBOT("脚本较多"),
    MASSIVE_NUM_ROBOT("大量脚本"),

    /*http状态码*/
    LAST_404_CODE("持续性较多页面丢失"),
    MIDDLE_NUM_404("页面丢失较多"),
    MASSIVE_NUM_404("大量页面丢失"),
    LAST_INTERNAL_500("持续性服务器内部错误")
    ;

    private final String code;
    AlertDesc(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
