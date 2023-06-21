import org.junit.Test;

import lombok.Data;
import sun.jvm.hotspot.utilities.Assert;

import com.baomidou.mybatisplus.annotation.TableField;

import com.cloudwise.clickhouse.helper.ClickhouseHelper;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:25
 **/
public class ClickhouseHelperTest {
    @Test
    public void test() {
        String sql = ClickhouseHelper
            .selectBuilder()
            .select("name,hostIp,hostId")
            .from("cw_db.doop_host_list")
            .where("hostIp=13")
            .build();
        Assert.that("select name,hostIp,hostId from cw_db.doop_host_list where hostIp=13 ".equals(sql),
            "sql not equals");
    }

    @Test
    public void testJoin() {

        String rightTable = ClickhouseHelper.selectBuilder()
            .select("ci_id, ci_name, model_id, toString(toDateTime(execute_time/1000)), execute_time")
            .from("cw_doop_120086658539777.doop_resource_info")
            .where("execute_time > 1687190454000 and ci_id = '118696109082370'")
            .groupBy("ci_id, ci_name, execute_time, model_id having count(*) > 1")
            .orderBy("execute_time desc")
            .limit("10")
            .asJoin();
        String result = ClickhouseHelper.selectBuilder()
            .select("ci_id,ci_name,model_id,toString(toDateTime(execute_time/1000)),execute_time")
            .from("cw_doop_120086658539777.doop_resource_info a global join "
                  + rightTable + " b on a.ci_id = b.ci_id and a.execute_time = b.execute_time")
            .orderBy("execute_time desc")
            .build();
        System.out.println(result);
    }

    @Data
    class HostMonitor {
        String name;
        @TableField("host_ip")
        String hostIp;
        @TableField("host_id")
        String hostId;
    }

    class ProcessMonitor {
        @TableField("name")
        String name;
        @TableField("process_id")
        String processId;
        @TableField("cpu_used")
        String cpuUsed;
        @TableField("host_id")
        String hostId;
    }

}
