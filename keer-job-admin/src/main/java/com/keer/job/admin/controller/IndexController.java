package com.keer.job.admin.controller;

import com.keer.job.admin.service.XxlJobService;
import com.keer.job.biz.model.ReturnT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * index controller
 */
@Controller
public class IndexController {
    @Resource
    private XxlJobService xxlJobService;

    @RequestMapping("/")
    public String index(Model model) {
        Map<String, Object> dashboardMap = xxlJobService.dashboardInfo();
        model.addAllAttributes(dashboardMap);
        return "index";
    }

    @RequestMapping("/triggerChartDate")
    @ResponseBody
    public ReturnT<Map<String,Object>> triggerCharDate(){
        ReturnT<Map<String,Object>> trigger = xxlJobService.triggerCharDate();
        return trigger;
    }
}
