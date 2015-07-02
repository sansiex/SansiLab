package com.dianping.sansi.sansilab.blackhole.handlers;

/**
 * Created by zuhai.jiang on 2015/6/26.
 */
public interface DataHandler {
    void init();
    boolean handle(String record);
    void destroy();
}
