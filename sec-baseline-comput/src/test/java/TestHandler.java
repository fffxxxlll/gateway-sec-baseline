import com.group4.log.env.InitStreamTabEnv;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author feng xl
 * @date 2021/7/12 0012 15:00
 */
public class TestHandler {

    @Test
    public void testAvgReq(){
        InitStreamTabEnv.logHandler.getAvgReqTime();
    }

    @Test
    public void testAvgRes(){
        InitStreamTabEnv.logHandler.getAvgResponseTime();
    }

    @Test
    public void testScript(){
        InitStreamTabEnv.logHandler.getScript();
    }

    @Test
    public void testHttpCode(){
        InitStreamTabEnv.logHandler.getHttpCodeCountAndRatio();
    }

    @Test
    public void testAvgSentSize(){
        InitStreamTabEnv.logHandler.getAvgSentSize();
    }

    @Test
    public void testRegex(){
        StreamTableEnvironment env = InitStreamTabEnv.logHandler.getEnv();
        String sql = "SELECT  COUNT(*) AS robot_num\n" +
                "FROM fs_log_source\n" +
                "WHERE http_user_agent IS  NULL OR REGEXP(LOWER(http_user_agent), '.*(java|prometheus|okhttp|stargate).*') \n";
        env.executeSql(sql).print();
    }

    @Test
    public void testRegex1(){
        String s1 = "Java/1.8.0_212";
        String reg = ".*(java|Prometheus|okhttp|stargate).*";
        boolean matches = Pattern.compile(reg).matcher(s1).matches();
    }

    @Test
    public void test(){
        double a = 475401;
        double c = 13831;
        double b = 23558509;
        System.out.println(c/b);

    }
}
