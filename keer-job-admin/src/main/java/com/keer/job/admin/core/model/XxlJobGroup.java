package com.keer.job.admin.core.model;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XxlJobGroup {
    private int id;
    private String appName;
    private String title;
    private int order;
    private int addressType;    // 执行器地址类型：0=自动注册、1=手动录入
    private String addressList;    // 执行器地址列表，多地址逗号分隔(手动录入)

    // registry list
    private List<String> regitryList;//执行器地址列表（系统注册)

    public List<String> getRegitryList() {
        if (StringUtils.isNotBlank(addressList)) {
            regitryList = new ArrayList<String>(Arrays.asList(addressList.split
                    (",")));
        }
        return regitryList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public String getAddressList() {
        return addressList;
    }

    public void setAddressList(String addressList) {
        this.addressList = addressList;
    }
}
