package com.group4.log.handler;

import com.group4.log.tableddl.CreateTable;
import lombok.Data;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author feng xl
 * @date 2021/7/11 0011 17:08
 */

@Data
public class LogHandler {

    private StreamTableEnvironment env;

    public LogHandler(){

    }

    public LogHandler(StreamTableEnvironment streamTabEnv){
        this.env = streamTabEnv;
    }

    public void getHttpCodeCountAndRatio(){
        String selectSql1 = "SELECT http_code, count(*) AS cnt \n" +
                "FROM fs_log_source\n" +
                "GROUP BY(http_code) ";
        String selectSql2 =
                "INSERT INTO count_http_code_table (cnt_400, cnt_500, cnt_total, ts)\n"+
                "SELECT COUNT(\n" +
                "CASE \n" +
                "WHEN http_code >= 400 AND http_code < 500\n" +
                "THEN 1 \n" +
                "ELSE NULL \n" +
                "END\n" +
                ") AS code_400,\n" +
                "COUNT (\n" +
                "CASE \n" +
                "WHEN http_code >= 500 \n" +
                "THEN 1\n" +
                "ELSE NULL\n" +
                "END\n" +
                ") AS code_500,\n" +
                "COUNT(*) AS total,\n" +
                "TUMBLE_END(ts, INTERVAL '15' SECOND) as ts\n" +
                "FROM fs_log_source\n" +
                "GROUP BY TUMBLE(ts, INTERVAL '15' SECOND)";
        env.executeSql(CreateTable.countHttpCodeSinkMysqlDDL);
        env.executeSql(selectSql2).print();
    }

    public void getAvgReqTime(){
        String selectSql1 =
                "INSERT INTO avg_req_table (avg_res_time, ts)\n"+
                "SELECT avg(request_time) AS avg_req_time, TUMBLE_END(ts, INTERVAL '15' SECOND) as ts\n" +
                "FROM fs_log_source\n" +
                "GROUP BY TUMBLE(ts, INTERVAL '15' SECOND)";
        env.executeSql(CreateTable.avgReqTimeSinkMysqlDDL);
        env.executeSql(selectSql1).print();
    }

    public void getAvgResponseTime(){
        String selectSql1 =
                "INSERT INTO avg_res_table (avg_res_time, ts)\n"+
                "SELECT avg(upstream_response_time) AS avg_response_time, TUMBLE_END(ts, INTERVAL '15' SECOND) as ts\n" +
                "FROM fs_log_source\n" +
                "GROUP BY TUMBLE(ts, INTERVAL '15' SECOND)";
        env.executeSql(CreateTable.avgResTimeSinkMysqlDDL);
        env.executeSql(selectSql1).print();
    }

    public void getAvgSentSize(){
        String selectSql1 = "SELECT avg(send_size) AS avg_size\n" +
                "FROM fs_log_source";
        String selectSql2 =
                "INSERT INTO avg_send_size_table (avg_send_size, ts)\n"+
                "SELECT send_size, agent_timestamp\n" +
                "FROM (\n" +
                "SELECT send_size,\n" +
                "ROW_NUMBER() OVER (\n" +
                "ORDER BY send_size DESC) AS row_num, \n" +
                "TUMBLE_END(ts, INTERVAL '15' SECOND) as ts\n" +
                "FROM fs_log_source)\n" +
                "WHERE row_num = 1000\n" +
                "GROUP BY TUMBLE(ts, INTERVAL '15' SECOND)";
        env.executeSql(CreateTable.avgSendSizeSinkMysqlDDL);
        env.executeSql(selectSql2).print();

    }

    public void getScript(){
        String selectSql1 = "SELECT  COUNT(*) AS total, \n" +
                    "COUNT(IF(http_user_agent IS NULL OR REGEXP(LOWER(http_user_agent), '.*(java|prometheus|okhttp|stargate).*'), true, NULL)) AS robot_num\n" +
                "FROM fs_log_source\n";
        String selectSql2 = "SELECT\n" +
                "(\n" +
                "SELECT COUNT(*)  FROM fs_log_source \n" +
                "WHERE \n" +
                "http_user_agent IS NULL \n" +
                "OR \n" +
                "REGEXP(LOWER(http_user_agent), '.*(java|prometheus|okhttp|stargate).*')\n" +
                ") AS robot_num, \n" +
                "(\n" +
                "SELECT COUNT(*) FROM fs_log_source\n" +
                ") AS total";
        String cntRobotNumSinkToMysql =
                "INSERT INTO count_robot_table (robot_num, total_num, ts)\n"+
                "SELECT COUNT(\n" +
                "CASE WHEN http_user_agent IS NULL\n" +
                "OR REGEXP(LOWER(http_user_agent), '.*(java|prometheus|okhttp|stargate).*')\n" +
                "THEN 1 ELSE NULL END ) AS robot_num, COUNT(*) AS total, TUMBLE_END(ts, INTERVAL '15' SECOND) as ts\n" +
                "FROM fs_log_source\n " +
                "GROUP BY TUMBLE(ts, INTERVAL '15' SECOND)";
        env.executeSql(CreateTable.countRobotNumSinkMysqlDDL);
        env.executeSql(cntRobotNumSinkToMysql);
    }
}
