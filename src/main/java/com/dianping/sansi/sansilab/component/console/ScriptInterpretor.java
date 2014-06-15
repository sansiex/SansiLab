package com.dianping.sansi.sansilab.component.console;

import java.io.File;

/**
 * Created by lenovo on 2014/6/14.
 */
public interface ScriptInterpretor {
    void interpretScript(String script);
    void interpretStatement(String stm);
    void interpretStatement(File file);
}
