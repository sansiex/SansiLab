package com.dianping.sansi.sansilab.entities;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/5/29.
 */
public class Result extends HashMap<String, Object> {
    public Result(){
        this.put("isSuccess", true);
    }

    public void setErrMsg(String msg){
        this.put("isSuccess", false);
        this.put("errMsg", msg);
    }
}
