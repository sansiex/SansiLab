package com.dianping.sansi.sansilab.action;

import com.dianping.sansi.interpreter.interpreter.script.ParseException;
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
public class ConsoleAction extends JsonAction {
    //services
    ConsoleService consoleService;

    //output

    //input
    Long userId;
    String pwd;
    String script;

    public String exec(){
        PipedReader pr=new PipedReader();
        PipedWriter pw= null;
        try {
            pw = new PipedWriter(pr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Console console=consoleService.getConsole(userId,true);
        final PipedWriter finalPw = pw;
        new Thread(()->{
            try {
                console.execScript(script, null, finalPw);
                finalPw.close();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CommandNotFountException e) {
                e.printStackTrace();
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }
        }).start();


        String line=null;
        try (BufferedReader br=new BufferedReader(pr);){
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
