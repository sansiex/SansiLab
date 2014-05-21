package com.dianping.sansi.sansilab.component.event;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by sansi on 2014/5/10.
 */
public class EventCenter {
    private EventCenter(){}
    private static EventCenter instance=new EventCenter();
    public static EventCenter getInstance(){
        return instance;
    }

    private HashMap<String, LinkedList<Listener>> listeners=new HashMap<>();

    public void listen(String event,Listener lsn){
        LinkedList<Listener> list=listeners.get(event);
        if(list==null){
            list=new LinkedList<>();
            listeners.put(event,list);
        }
        list.add(lsn);
        System.out.println("Add listeners:"+lsn.getClass().getSimpleName()+" to event:"+event);
    }

    public void remove(String event,Listener lsn){
        LinkedList<Listener> list=listeners.get(event);
        if(list==null){
            return;
        }
        list.remove(lsn);
    }

    public void dispatchAsyn(String event, HashMap<String,Object> params, Object src){
        dispatchAsyn(event,params,src,true);
    }

    public void dispatchAsyn(String event, HashMap<String,Object> params, Object src, boolean bubble){
        final Event e=new Event(event,params,src,bubble);

        LinkedList<Listener> list=listeners.get(event);
        if(list==null){
            return;
        }else{
            for(final Listener lsn:list){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        lsn.act(e);
                    }
                }).start();
                if(!e.bubble){
                    break;
                }
            }
        }
    }

    public void dispatchSyn(String event, HashMap<String,Object> params, Object src){
        dispatchSyn(event,params,src,true);
    }

    public void dispatchSyn(String event, HashMap<String,Object> params, Object src, boolean bubble){
        final Event e=new Event(event,params,src,bubble);

        LinkedList<Listener> list=listeners.get(event);
        if(list==null){
            return;
        }else{
            for(Listener lsn:list){
                lsn.act(e);
                if(!e.bubble){
                    break;
                }
            }
        }
    }
}
