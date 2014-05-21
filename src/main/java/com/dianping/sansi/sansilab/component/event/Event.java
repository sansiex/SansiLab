package com.dianping.sansi.sansilab.component.event;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by sansi on 2014/5/10.
 */
public class Event {
    public String eventName;
    public Object src=null;
    private HashMap<String,Object> params=new HashMap<>();
    public boolean bubble=true;
    public Date date=new Date();

    public Event(){}

    public Event(String eventName, HashMap<String,Object> params, Object src, boolean bubble){
        this.eventName=eventName;
        if(params!=null){
            this.params=params;
        }
        this.src=src;
        this.bubble=bubble;
    }

    public Event(String eventName, HashMap<String,Object> params, Object src){
        new Event(eventName, params, src, true);
    }

    public void setParam(String key,Object param){
        params.put(key,param);
    }

    public Object getParam(String key){
        return params.get(key);
    }
}
