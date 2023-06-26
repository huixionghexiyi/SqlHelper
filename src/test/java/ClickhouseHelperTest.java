import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import lombok.Data;

import com.google.common.collect.Lists;

import com.cloudwise.clickhouse.helper.ClickhouseHelper;
import com.cloudwise.clickhouse.helper.annotations.ClickhouseTable;
import com.cloudwise.clickhouse.helper.annotations.ClickhouseTableField;
import com.cloudwise.clickhouse.helper.condition.WhereCondition;

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
        Assert.assertTrue(
            "select name,hostIp,hostId from cw_db.doop_host_list where hostIp=13".equals(StringUtils.trim(sql)));
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
            .asSubSelect();
        String sql = ClickhouseHelper.selectBuilder()
            .select("ci_id,ci_name,model_id,toString(toDateTime(execute_time/1000)),execute_time")
            .from("cw_doop_120086658539777.doop_resource_info a global join "
                  + rightTable + " b on a.ci_id = b.ci_id and a.execute_time = b.execute_time")
            .orderBy("execute_time desc")
            .build();
        Assert.assertTrue(sql,
            "select ci_id,ci_name,model_id,toString(toDateTime(execute_time/1000)),execute_time from cw_doop_120086658539777.doop_resource_info a global join (select ci_id, ci_name, model_id, toString(toDateTime(execute_time/1000)), execute_time from cw_doop_120086658539777.doop_resource_info where execute_time > 1687190454000 and ci_id = '118696109082370' group by ci_id, ci_name, execute_time, model_id having count(*) > 1 order by execute_time desc limit 10) b on a.ci_id = b.ci_id and a.execute_time = b.execute_time order by execute_time desc".equals(
                StringUtils.trim(sql)));
    }

    @Test
    public void testWhereSingleCondition() {
        WhereCondition whereCondition = new WhereCondition();
        whereCondition.eq("task_execute_time", 1111);
        String subSelect = ClickhouseHelper.selectBuilder()
            .select("1")
            .from("table1")
            .where(whereCondition)
            .asSubSelect();

        String sql = ClickhouseHelper.selectBuilder()
            .select("count(*)")
            .from(subSelect)
            .groupBy("host_ip", "host_name", "host_id")
            .build();
        System.out.println(sql);
    }

    @Test
    public void testGraceSelect() {
        String sql = ClickhouseHelper.selectBuilder()
            .select("h", HostMonitor.class)
            .from("cw_doop_120086658539777.doop_host_list h")
            .where("host_ip=13")
            .groupBy(Lists.newArrayList("host_ip", "host_id"))
            .orderByDesc("host_ip")
            .build();
        Assert.assertTrue(sql,
            "select h.name,h.host_ip,h.host_id from cw_doop_120086658539777.doop_host_list h where host_ip=13 group by host_ip,host_id order by host_ip desc".equals(
                StringUtils.trim(sql)));
    }

    @Test
    public void testAppendSelect() {
        String sql = ClickhouseHelper.selectBuilder()
            .select("h", HostMonitor.class)
            .appendSelect("hp", ProcessMonitor.class)
            .from("h", "cw_doop_120086658539777.doop_host_list")
            .where("host_ip=13")
            .groupBy(Lists.newArrayList("host_ip", "host_id"))
            .orderByDesc("host_ip")
            .build();
        Assert.assertTrue(sql,
            "select h.name,h.host_ip,h.host_id ,hp.name,hp.process_id,hp.cpu_used,hp.host_id from cw_doop_120086658539777.doop_host_list AS h  where host_ip=13 group by host_ip,host_id order by host_ip desc".equals(
                StringUtils.trim(sql)));
    }

    @Test
    public void testGraceWhere() {
        WhereCondition condition = new WhereCondition();
        condition.in("host_ip", Lists.newArrayList(13, 12))
            .notIn("host_id", Lists.newArrayList("1", "2"))
            .and(new WhereCondition().eq("name", "timothy"));
        String sql = ClickhouseHelper.selectBuilder()
            .select("h", HostMonitor.class)
            .from("h", "cw_doop_120086658539777.doop_host_list")
            .where(condition)
            .groupBy(Lists.newArrayList("host_ip", "host_id"))
            .orderByDesc("host_ip")
            .build();
        Assert.assertTrue(sql,
            "select h.name,h.host_ip,h.host_id from cw_doop_120086658539777.doop_host_list AS h  where (host_ip in (13,12) and host_id not in ('1','2')) AND (name = 'timothy') group by host_ip,host_id order by host_ip desc".equals(
                StringUtils.trim(sql)));
    }

    //    @Test
    //    public void testProcessAndHost(){
    //        WhereCondition condition = new WhereCondition();
    //        ClickhouseHelper.selectBuilder()
    //            .select("h", Model.HostMonitor.class)
    //            .appendSelect("hp", Model.ProcessMonitor.class)
    //            .from("hp", "cw_doop_120808049271554.host_processes_info")
    //            .globalJoin("h", "cw_doop_120808049271554.doop_host_list", "h.host_id = hp.host_id")
    //            .where("hp.update_time >= 1687659189036 and hp.update_time <= 1687659789037 and (LOWER(h.os_type) like LOWER('%L%'))")
    //            .groupBy("hp.host_id, hp.pid, hp.create_time")
    //            .orderByDesc("systemProcessCpuPercent");
    //    }

    @Test
    public void testAggregation() {
        String subSelect = ClickhouseHelper.selectBuilder()
            .select("h", HostMonitor.class)
            .from("h", "cw_doop_120086658539777.doop_host_list")
            .where("host_ip=13").asSubSelect();
        String sql = ClickhouseHelper.selectBuilder()
            .select()
            .appendSelect("argMax(hp.host_name, hp.update_time) AS hostName")
            .from("hp", "cw_doop_120808049271554.host_processes_info")
            .globalJoin("h", subSelect, "h.host_id = hp.host_id")
            .build();
        Assert.assertTrue(
            sql,
            "select argMax(hp.host_name, hp.update_time) AS hostName from cw_doop_120808049271554.host_processes_info AS hp  global join (select h.name,h.host_ip,h.host_id from cw_doop_120086658539777.doop_host_list AS h  where host_ip=13 ) as h on h.host_id = hp.host_id".equals(
                sql.trim()));
    }

    @Test
    public void testWhereSupportIgnoreCase() {
        WhereCondition condition = new WhereCondition();
        condition.in("host_ip", Lists.newArrayList(13, 12), true)
            .notIn("host_id", Lists.newArrayList("A", "B", "C"), false)
            .and(new WhereCondition().eq("name", "TIMOTHY", true));
        String sql = ClickhouseHelper.selectBuilder()
            .select("h", HostMonitor.class)
            .from("h", "cw_doop_120086658539777.doop_host_list")
            .where(condition)
            .groupBy(Lists.newArrayList("host_ip", "host_id"))
            .orderByDesc("host_ip")
            .build();
        Assert.assertTrue(sql,
            "select h.name,h.host_ip,h.host_id from cw_doop_120086658539777.doop_host_list AS h  where (lower(host_ip) in (13,12) and host_id not in ('A','B','C')) AND (lower(name) = 'timothy') group by host_ip,host_id order by host_ip desc".equals(
                StringUtils.trim(sql)));
    }

    @Data
    @ClickhouseTable(db = "cw_doop", table = "cw_db.doop_host_list", alias = "h")
    class HostMonitor {
        @ClickhouseTableField("name")
        private String name;
        @ClickhouseTableField("host_ip")
        private String hostIp;
        @ClickhouseTableField("host_id")
        private String hostId;
    }

    class ProcessMonitor {
        @ClickhouseTableField("name")
        private String name;
        @ClickhouseTableField("process_id")
        private String processId;
        @ClickhouseTableField("cpu_used")
        private String cpuUsed;
        @ClickhouseTableField("host_id")
        private String hostId;
    }

}
