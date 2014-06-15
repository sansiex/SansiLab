package com.dianping.sansi.sansilab.action;

import com.dianping.sansi.sansilab.component.console.Console;
import com.dianping.sansi.sansilab.component.console.exception.CommandNotFountException;
import com.dianping.sansi.sansilab.component.console.exception.InvalidCommandException;
import com.dianping.sansi.sansilab.service.ConsoleService;
import com.dianping.sansi.sansilab.service.FileService;
import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by lenovo on 2014/5/20.
 */
public class ConsoleAction extends BaseAction {
    //services
    ConsoleService consoleService;

    //output
    int code=DirectoryAction.CODE_SUCCESS;

    //input
    Long userId;
    String pwd;
    String script;

    public String exec(){
        final PipedReader pr=new PipedReader();
        PipedWriter pw= null;
        try {
            pw = new PipedWriter(pr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final PipedWriter finalPw = pw;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Console console=consoleService.getConsole(userId,true);
                try {
                    console.execStatement(script, null, finalPw);
                } catch (CommandNotFountException e) {
                    Event event = Event.createDataEvent("/console/output");
                    event.setField("msg", "Command \""+e.getCommand()+"\" not fount.");
                    Dispatcher.getInstance().unicast(event,userId.toString()); // 向ID为piero的用户推送
                } catch (InvalidCommandException e) {
                    Event event = Event.createDataEvent("/console/output");
                    event.setField("msg", "Invalid command.");
                    Dispatcher.getInstance().unicast(event,userId.toString()); // 向ID为piero的用户推送
                } finally {
                    try {
                        finalPw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        BufferedReader br=new BufferedReader(pr);
        String line=null;
        try {
            while((line=br.readLine())!=null){
                System.out.println(line);
                Event event = Event.createDataEvent("/console/output");
                event.setField("msg", line);
                Dispatcher.getInstance().unicast(event,userId.toString()); // 向ID为piero的用户推送
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                pr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Event event = Event.createDataEvent("/console/output");
            event.setField("opt", Console.OPT_END_OUTPUT);
            Dispatcher.getInstance().unicast(event,userId.toString()); // 向ID为piero的用户推送
        }

        return SUCCESS;
    }

    public int getCode(){
        return code;
    }

    public void setConsoleService(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
