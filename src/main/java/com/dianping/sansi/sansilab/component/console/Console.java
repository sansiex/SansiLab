package com.dianping.sansi.sansilab.component.console;

import com.dianping.sansi.interpreter.controller.ScriptExecutor;
import com.dianping.sansi.sansilab.component.console.command.CommandManager;
import com.dianping.sansi.sansilab.component.console.command.ConsoleCommand;
import com.dianping.sansi.sansilab.component.console.exception.CommandNotFountException;
import com.dianping.sansi.sansilab.component.console.exception.InvalidCommandException;

import java.io.*;
import java.util.HashMap;

/**
 * Created by sansi on 2014/5/21.
 */
public class Console {
    public static final String OPT_END_OUTPUT="end_output";

    private String pwd;
    private HashMap<String,String> env=new HashMap<>();

    public void execStatement(String stm, Reader reader, Writer writer) throws CommandNotFountException, InvalidCommandException {
        ScriptExecutor.exec(stm,writer);
    }

    public void execStatement(String stm, PipedInputStream is, PipedOutputStream os) throws CommandNotFountException, InvalidCommandException {
        ScriptExecutor.exec(stm,os);
    }

    private void execCommand(String cmd, Reader reader, Writer writer) throws CommandNotFountException, InvalidCommandException {
        String[] tokens = cmd.trim().split(" ");
        if (tokens == null || tokens.length == 0) {
            throw new InvalidCommandException();
        }
        String name = tokens[0];

        ConsoleCommand cc = CommandManager.getInstance().getCommand(name);
        if (cc == null) {
            throw new CommandNotFountException(name);
        }

        cc.doCommand(tokens, reader, writer, this);
    }

    private void execCommand(String cmd, PipedInputStream is, PipedOutputStream os) throws CommandNotFountException, InvalidCommandException {
        String[] tokens = cmd.trim().split(" ");
        if (tokens == null || tokens.length == 0) {
            throw new InvalidCommandException();
        }
        String name = tokens[0];

        ConsoleCommand cc = CommandManager.getInstance().getCommand(name);
        if (cc == null) {
            throw new CommandNotFountException(name);
        }

        cc.doCommand(tokens, is, os, this);
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEnvVar(String name){
        return env.get(name);
    }

    public void setEnvVar(String name,String value){
        env.put(name,value);
    }

    public void removeEnvVar(String name){
        env.remove(name);
    }
}
