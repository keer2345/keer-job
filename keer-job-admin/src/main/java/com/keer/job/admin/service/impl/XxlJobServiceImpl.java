package com.keer.job.admin.service.impl;

import com.keer.job.admin.core.model.XxlJobGroup;
import com.keer.job.admin.dao.XxlJobGroupDao;
import com.keer.job.admin.dao.XxlJobInfoDao;
import com.keer.job.admin.dao.XxlJobLogDao;
import com.keer.job.admin.service.XxlJobService;
import com.keer.job.biz.model.ReturnT;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
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

    public ReturnT<Map<String, Object>> triggerCharDate() {
        Date from = DateUtils.addDays(new Date(), -30);
        Date to = new Date();

        List<String> triggerDayList = new ArrayList<String>();
        List<Integer> triggerDayCountSucList = new ArrayList<Integer>();
        List<Integer> triggerDayCountFailList = new ArrayList<Integer>();

        int triggerCountSucTotal = 0;
        int triggerCountFailTotal = 0;

        List<Map<String, Object>> triggerCountMapAll = xxlJobLogDao
                .triggerCountByDay(from, to, -1);
        List<Map<String, Object>> triggerCountMapSuc = xxlJobLogDao
                .triggerCountByDay(from, to, ReturnT.SUCCESS_CODE);

        if (CollectionUtils.isNotEmpty(triggerCountMapAll)) {
            for (Map<String, Object> item : triggerCountMapAll) {
                String day = String.valueOf(item.get("triggerDay"));
                int dayAllCount = Integer.valueOf(String.valueOf(item.get
                        ("triggerCount")));
                int daySucCount = 0;
                int dayFailCount = dayAllCount - daySucCount;

                if (CollectionUtils.isNotEmpty(triggerCountMapSuc)) {
                    for (Map<String, Object> sucItem : triggerCountMapSuc) {
                        String daySuc = String.valueOf(sucItem.get
                                ("triggerDay"));
                        if (day.equals(daySuc)) {
                            daySucCount = Integer.valueOf(String.valueOf
                                    (sucItem.get("triggerCount")));
                            dayFailCount = dayAllCount - daySucCount;
                        }
                    }
                }

                triggerDayList.add(day);
                triggerDayCountSucList.add(daySucCount);
                triggerDayCountFailList.add(dayFailCount);
                triggerCountSucTotal += daySucCount;
                triggerCountFailTotal += dayFailCount;
            }
        } else {
            for (int i = 4; i > -1; i--) {
                triggerDayList.add(FastDateFormat.getInstance("yyyy-MM-dd").format(DateUtils
                        .addDays(new Date(), -i)));
                triggerDayCountSucList.add(0);
                triggerDayCountFailList.add(0);
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("triggerDayList", triggerDayList);
        result.put("triggerDayCountSucList", triggerDayCountSucList);
        result.put("triggerDayCountFailList", triggerDayCountFailList);
        result.put("triggerCountSucTotal", triggerCountSucTotal);
        result.put("triggerCountFailTotal", triggerCountFailTotal);

        return new ReturnT<Map<String, Object>>(result);
    }
}
