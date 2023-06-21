# SqlHelper

## 介绍
在代码中拼接sql
```java
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
```