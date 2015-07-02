package com.dianping.sansi.sansilab.blackhole.handlers;

/**
 * Created by zuhai.jiang on 2015/6/29.
 */
public class TestHandler implements DataHandler{

    @Override
    public void init() {
        System.out.println("init test handler");
    }

    @Override
    public boolean handle(String record) {
        System.out.println(record);
        return false;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
