package com.keer.job.admin.service.impl;

import com.keer.job.admin.core.model.XxlJobGroup;
import com.keer.job.admin.dao.XxlJobGroupDao;
import com.keer.job.admin.dao.XxlJobInfoDao;
import com.keer.job.admin.dao.XxlJobLogDao;
import com.keer.job.admin.service.XxlJobService;
import com.keer.job.biz.model.ReturnT;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class XxlJobServiceImpl implements XxlJobService {
    @Resource
    private XxlJobGroupDao xxlJobGroupDao;
    @Resource
    private XxlJobInfoDao xxlJobInfoDao;
    @Resource
    private XxlJobLogDao xxlJobLogDao;

    public Map<String, Object> dashboardInfo() {
        int jobInfoCount = xxlJobInfoDao.findAllCount();
        int jobLogCount = xxlJobLogDao.triggerCountByHandleCode(-1);
        int jobLogSuccessCount = xxlJobLogDao.triggerCountByHandleCode
                (ReturnT.SUCCESS_CODE);

        // executor count
        Set<String> executorAddressSet = new HashSet<String>();
        List<XxlJobGroup> groupList = xxlJobGroupDao.findAll();

        if (CollectionUtils.isNotEmpty(groupList)) {
            for (XxlJobGroup group :
                    groupList) {
                if (CollectionUtils.isNotEmpty(group.getRegitryList())) {
                    executorAddressSet.addAll(group.getRegitryList());
                }
            }
        }

        int executorCount = executorAddressSet.size();

        Map<String, Object> dashboardMap = new HashMap<String, Object>();
        dashboardMap.put("jobInfoCount", jobInfoCount);
        dashboardMap.put("jobLogCount", jobLogCount);
        dashboardMap.put("jobLogSuccessCount", jobLogSuccessCount);
        dashboardMap.put("executorCount", executorCount);

        return dashboardMap;
    }
}
