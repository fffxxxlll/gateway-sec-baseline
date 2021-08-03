package com.group4.log.tableddl;

/**
 * @author feng xl
 * @date 2021/7/12 0012 15:25
 */
public class CreateTable {

    public static String logSourceDDL= "CREATE TABLE fs_log_source(" +
            "rowkey   string," +
            "active   bigint," +
            "agent_timestamp   bigint," +
            "arg_name   string," +
            "args   string," +
            "city   string," +
            "client_ip   string," +
            "continent   string," +
            "country   string," +
            "dispatch_ip   string," +
            "forwarded_ip   string," +
            "host   string," +
            "http_code   bigint," +
            "http_referer   string," +
            "http_user_agent   string," +
            "latitude   string," +
            "listen_addr   string," +
            "listen_port   string," +
            "location   string," +
            "longitude   string," +
            "monitor_type   bigint," +
            "recv_size   bigint," +
            "region   string," +
            "region_name   string," +
            "request_method   string," +
            "request_time    float," +
            "request_trace_id   string," +
            "scheme   string," +
            "send_size   bigint," +
            "server_name   string," +
            "server_port   string," +
            "`timestamp`    bigint," +
            "type   string," +
            "upstream_addr   string," +
            "upstream_name   string," +
            "upstream_name_origin   string," +
            "upstream_response_time    float," +
            "url   string," +
            "workgroup_id   bigint," +
            "workgroup_name   string,"+
            "ts AS TO_TIMESTAMP(FROM_UNIXTIME(agent_timestamp / 1000, 'yyyy-MM-dd HH:mm:ss'))," +
            "WATERMARK FOR ts AS ts - INTERVAL '5' SECOND"+
            ") WITH (" +
            " 'connector' = 'filesystem'," +
            " 'path' = 'D:\\download\\unlayer_20210601\\part-00630-1cd0b8fb-f3ce-48c4-b375-b97121c4f2a8.c000'," +
            " 'format' = 'orc'" +
            ")";


    public static String rawLogSinkToKafkaDDL = "CREATE TABLE sink_to_kafka (" +
            "agent_timestamp BIGINT," +
            "http_code        BIGINT," +
            "upstream_response_time FLOAT," +
            "recv_size   BIGINT," +
            "request_time    FLOAT," +
            "PRIMARY KEY (ts) NOT ENFORCED" +
            ") WITH (" +
            " 'connector' = 'kafka'," +
            " 'topic' = 'raw_log'," +
            " 'properties.bootstrap.servers' = 'hadoop01:9092'," +
            " 'properties.group.id' = 'testGroup'," +
            " 'scan.startup.mode' = 'earliest-offset'," +
            " 'format' = 'orc'" +
            ")";

    public static String avgReqTimeSinkMysqlDDL = "CREATE TABLE avg_req_table (" +
            "avg_req_time FLOAT," +
            "ts        BIGINT," +
            "PRIMARY KEY (ts) NOT ENFORCED" +
            ") WITH (" +
            " 'connector' = 'jdbc'," +
            " 'username' = 'root'," +
            " 'password' = '123456'," +
            " 'url' = 'jdbc:mysql://localhost:3307/sec_baseline'," +
            " 'table-name' = 'avg_request_time'" +
            ")";
    public static String avgResTimeSinkMysqlDDL = "CREATE TABLE avg_res_table (" +
            "avg_res_time FLOAT," +
            "ts        BIGINT," +
            "PRIMARY KEY (ts) NOT ENFORCED" +
            ") WITH (" +
            " 'connector' = 'jdbc'," +
            " 'username' = 'root'," +
            " 'password' = '123456'," +
            " 'url' = 'jdbc:mysql://localhost:3307/sec_baseline'," +
            " 'table-name' = 'avg_response_time'" +
            ")";

    public static String avgSendSizeSinkMysqlDDL = "CREATE TABLE avg_send_size_table (" +
            "avg_send_size BIGINT," +
            "ts        BIGINT," +
            "PRIMARY KEY (ts) NOT ENFORCED" +
            ") WITH (" +
            " 'connector' = 'jdbc'," +
            " 'username' = 'root'," +
            " 'password' = '123456'," +
            " 'url' = 'jdbc:mysql://localhost:3307/sec_baseline'," +
            " 'table-name' = 'avg_send_size'" +
            ")";
    public static String countRobotNumSinkMysqlDDL = "CREATE TABLE count_robot_table (" +
            "robot_num BIGINT," +
            "total_num BIGINT," +
            "ts        BIGINT," +
            "PRIMARY KEY (ts) NOT ENFORCED" +
            ") WITH (" +
            " 'connector' = 'jdbc'," +
            " 'username' = 'root'," +
            " 'password' = '123456'," +
            " 'url' = 'jdbc:mysql://localhost:3307/sec_baseline'," +
            " 'table-name' = 'cnt_user_agent'" +
            ")";
    public static String countHttpCodeSinkMysqlDDL = "CREATE TABLE count_http_code_table (" +
            "cnt_400 BIGINT," +
            "cnt_500 BIGINT," +
            "cnt_total BIGINT," +
            "ts        BIGINT," +
            "PRIMARY KEY (ts) NOT ENFORCED" +
            ") WITH (" +
            " 'connector' = 'jdbc'," +
            " 'username' = 'root'," +
            " 'password' = '123456'," +
            " 'url' = 'jdbc:mysql://localhost:3307/sec_baseline'," +
            " 'table-name' = 'cnt_http_code'" +
            ")";
}
