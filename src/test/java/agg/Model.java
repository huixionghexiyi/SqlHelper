package agg;

import lombok.Data;

import com.cloudwise.clickhouse.helper.annotations.ClickhouseTable;
import com.cloudwise.clickhouse.helper.annotations.ClickhouseTableField;

/**
 * @author timothy
 * @DateTime: 2023/6/25 10:59
 **/
public class Model {
    @Data
    @ClickhouseTable(db = "cw_doop", table = "cw_db.doop_host_list", alias = "h")
    public class HostMonitor {
        @ClickhouseTableField
        private String name;
        @ClickhouseTableField("os_type")
        private String osType;
        @ClickhouseTableField("os_bitness")
        private String osBitness;
        @ClickhouseTableField("host_id")
        private String hostId;
        @ClickhouseTableField("task_execute_time")
        private long taskExecuteTime;
    }

    public class ProcessMonitor {
        @ClickhouseTableField("hostName")
        private String hostName;
        @ClickhouseTableField("process_id")
        private String processId;
        @ClickhouseTableField("cpu_used")
        private String cpuUsed;
        @ClickhouseTableField("host_id")
        private String hostId;

        @ClickhouseTableField("pid")
        private String pid;

        @ClickhouseTableField("createTime")
        private String createTime;

        @ClickhouseTableField("command")
        private String command;

        @ClickhouseTableField("processName")
        private String processName;

        @ClickhouseTableField("username")
        private String username;

        @ClickhouseTableField("systemProcessCpuPercent")
        private double systemProcessCpuPercent;

        @ClickhouseTableField("systemProcessRssUsed")
        private double systemProcessRssUsed;
    }
}
