package com.dianping.sansi.sansilab.service;

import com.dianping.sansi.sansilab.component.console.Console;

import java.util.HashMap;

/**
 * Created by lenovo on 2014/6/15.
 */
public class ConsoleService {
    private static HashMap<Long,Console> consoleMap=new HashMap<>();

    public Console createConsole(Long userId){
        Console csl=new Console();
        consoleMap.put(userId, csl);
        return csl;
    }

    public Console removeConsole(Long userId){
        return consoleMap.remove(userId);
    }

    public Console getConsole(Long userId){
        return consoleMap.get(userId);
    }

    public Console getConsole(Long userId, boolean create){
        Console csl = consoleMap.get(userId);
        if(create && csl==null){
            return createConsole(userId);
        }
        return csl;
    }
}
