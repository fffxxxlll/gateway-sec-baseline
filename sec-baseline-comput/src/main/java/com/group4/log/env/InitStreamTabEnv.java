package com.group4.log.env;

import com.group4.log.handler.LogHandler;
import com.group4.log.tableddl.CreateTable;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author feng xl
 * @date 2021/7/12 0012 15:30
 */
public class InitStreamTabEnv {

    public static StreamTableEnvironment streamTableEnvironment;
    public static LogHandler logHandler;
    static {
        EnvironmentSettings settings = EnvironmentSettings.newInstance()
                .useBlinkPlanner()
                .inStreamingMode()
                .build();
        StreamExecutionEnvironment streamEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment streamTableEnv = StreamTableEnvironment.create(streamEnv, settings);
        streamTableEnv.executeSql(CreateTable.logSourceDDL);
        streamTableEnvironment = streamTableEnv;
        logHandler = new LogHandler(streamTableEnv);
    }
}
