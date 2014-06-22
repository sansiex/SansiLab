package com.dianping.sansi.sansilab.action;

import org.apache.struts2.json.annotations.JSON;

/**
 * Created by lenovo on 2014/6/22.
 */
public class JsonAction extends BaseAction {
    protected int code=DirectoryAction.CODE_SUCCESS;

    @JSON(name="code")
    public int getCode(){
        return code;
    }
}
