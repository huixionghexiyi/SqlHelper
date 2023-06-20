import org.junit.Test;

import lombok.Data;
import sun.jvm.hotspot.utilities.Assert;

import com.cloudwise.clickhouse.helper.builder.ClickhouseHelper;

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
        Assert.that("select name,hostIp,hostId from cw_db.doop_host_list where hostIp=13 ".equals(sql), "sql not equals");
    }

    @Data
    class HostMonitor {
        String name;
        String hostIp;
        String hostId;
    }
}
