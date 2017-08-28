package com.keer.job.admin.service;

import com.keer.job.biz.model.ReturnT;

import java.util.Map;

/**
 * core job action for keer-job
 */
public interface XxlJobService {
    public Map<String,Object> dashboardInfo();
    public ReturnT<Map<String,Object>> triggerChartDate();
}
