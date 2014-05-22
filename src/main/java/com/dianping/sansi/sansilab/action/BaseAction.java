package com.dianping.sansi.sansilab.action;

import com.dianping.sansi.sansilab.service.LogService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by sansi on 2014/5/22.
 */
public class BaseAction extends ActionSupport {
    public static final int CODE_SUCCESS=0;

    protected LogService logService;

    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
