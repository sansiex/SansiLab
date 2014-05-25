package com.dianping.sansi.sansilab.action;

import com.opensymphony.xwork2.ActionSupport;
import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lenovo on 2014/5/20.
 */
public class TestAction  extends BaseAction {

    public String broadcast(){
        Timer t=new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                myBroadcast();
            }
        },1000);
        return SUCCESS;
    }

    private void myUnicast() {

        Event event = Event.createDataEvent("/myevent/msg");
        event.setField("msg", "Unicast message");
        Dispatcher.getInstance().unicast(event, "piero"); // 向ID为piero的用户推送
        System.out.println("Unicast success....");
    }

    private void myMulticast() {
        Event event = Event.createDataEvent("/myevent/msg");
        //Event event = Event.createDataEvent("/guoguo");
        event.setField("msg", "Multicast msg");
        Dispatcher.getInstance().multicast(event); // 向所有和myevent1名称匹配的事件推送

        System.out.println("Multicast success....");

    }

    private void myBroadcast() {
        Event event = Event.createDataEvent("/myevent/msg"); // 向所有的事件推送，不要求和这儿的myevent1名称匹配
        event.setField("msg", "Broadcast msg");
        Dispatcher.getInstance().broadcast(event);

        System.out.println("Broadcast success....");
    }
}
