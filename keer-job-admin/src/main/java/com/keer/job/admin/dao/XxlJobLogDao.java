package com.keer.job.admin.dao;

import org.apache.ibatis.annotations.Param;

public interface XxlJobLogDao {
    public int triggerCountByHandleCode(@Param("handleCode") int handleCode);
}
