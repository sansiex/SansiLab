package com.dianping.sansi.sansilab.components.console.exception;

/**
 * Created by lenovo on 2014/6/14.
 */
public class CommandNotFountException extends Exception {
    private String cmd;

    public CommandNotFountException(String cmd){
        super(cmd);this.cmd=cmd;
    }

    public String getCommand(){
        return cmd;
    }
}
