package com.keer.job.admin.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface XxlJobLogDao {
    public int triggerCountByHandleCode(@Param("handleCode") int handleCode);

    public List<Map<String, Object>> triggerCountByDay(@Param("from") Date from,
                                                       @Param("to") Date to,
                                                       @Param("handleCode") int handleCode);
}
