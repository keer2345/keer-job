package com.keer.job.admin.service.impl;

import com.keer.job.admin.dao.XxlJobInfoDao;
import com.keer.job.admin.dao.XxlJobLogDao;
import com.keer.job.admin.service.XxlJobService;
import com.keer.job.biz.model.ReturnT;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class XxlJobServiceImpl implements XxlJobService {
    @Resource
    private XxlJobInfoDao xxlJobInfoDao;
    @Resource
    private XxlJobLogDao xxlJobLogDao;

    public Map<String, Object> dashboardInfo() {
        int jobInfoCount = xxlJobInfoDao.findAllCount();
        int jobLogCount = xxlJobLogDao.triggerCountByHandleCode(-1);
        int jobLogSuccessCount = xxlJobLogDao.triggerCountByHandleCode
                (ReturnT.SUCCESS_CODE);


        Map<String, Object> dashboardMap = new HashMap<String, Object>();
        dashboardMap.put("jobInfoCount", jobInfoCount);
        dashboardMap.put("jobLogCount", jobLogCount);
        dashboardMap.put("jobLogSuccessCount", jobLogSuccessCount);

        return dashboardMap;
    }
}
