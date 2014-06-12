package com.dianping.sansi.sansilab.action;

import com.dianping.sansi.sansilab.service.FileService;
import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by lenovo on 2014/5/20.
 */
public class ConsoleAction extends BaseAction {
    //services
    FileService fileService;

    //output
    int code=DirectoryAction.CODE_SUCCESS;

    //input
    String pwd;
    String script;

    public String exec(){

        new Thread(new Runnable() {
            int num=1;
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Event event = Event.createDataEvent("/console/response");
                event.setField("msg", "Unicast message "+num++);
                Dispatcher.getInstance().unicast(event,"145"); // 向ID为piero的用户推送
            }
        }).start();

        return SUCCESS;
    }



    public int getCode(){
        return code;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
