package com.keer.job.admin.service.impl;

import com.keer.job.admin.dao.XxlJobInfoDao;
import com.keer.job.admin.service.XxlJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class XxlJobServiceImpl implements XxlJobService {
    @Resource
    private XxlJobInfoDao xxlJobInfoDao;

    public Map<String, Object> dashboardInfo() {
        int jobInfoCount = xxlJobInfoDao.findAllCount();
        Map<String, Object> dashboardMap = new HashMap<String, Object>();
        dashboardMap.put("jobInfoCount", jobInfoCount);

        return dashboardMap;
    }
}
