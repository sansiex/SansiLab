package com.dianping.sansi.sansilab.components.console;

import com.dianping.sansi.sansilab.interpreter.controller.ScriptInterpretor;
import com.dianping.sansi.sansilab.interpreter.interpreter.script.ParseException;
import com.dianping.sansi.sansilab.interpreter.interpreter.script.node.*;
import com.dianping.sansi.sansilab.components.console.command.CommandManager;
import com.dianping.sansi.sansilab.components.console.command.ConsoleCommand;
import com.dianping.sansi.sansilab.components.console.exception.CommandNotFountException;
import com.dianping.sansi.sansilab.components.console.exception.InvalidCommandException;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sansi on 2014/5/21.
 */
public class Console {
    public static final String OPT_END_OUTPUT="end_output";

    private String pwd;
    private HashMap<String,String> env=new HashMap<>();

    public Console(){
        pwd="d:\\";
    }

    public void execScript(String script, Reader reader, Writer writer) throws ParseException, IOException, CommandNotFountException, InvalidCommandException {
        List<Node> list=ScriptInterpretor.execScript(script);
        for(Node n:list){
            StatementNode sn= (StatementNode) n;
            execStatement(sn,reader,writer);
        }
    }

    public void execStatement(StatementNode sn, Reader reader, Writer writer) throws IOException, CommandNotFountException, InvalidCommandException {
        ConsoleTask task=new ConsoleTask(sn,writer,this);
        task.executeTask(reader,writer);
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

    public static void main(String[] args) throws CommandNotFountException, InvalidCommandException, ParseException, IOException {
        Console csl=new Console();
        csl.execScript("cat e:\\data\\test.txt | grep -i a | grep -i b\n",null,new OutputStreamWriter(System.out));
    }
}
