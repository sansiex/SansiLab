package com.dianping.sansi.sansilab.component.console.command;

import com.dianping.sansi.sansilab.component.console.Console;
import com.dianping.sansi.sansilab.component.console.ConsoleTask;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2014/6/29.
 */
public class CdCommand implements ConsoleCommand{

    @Override
    public void doCommand(String[] cmd, Reader reader, Writer writer, ConsoleTask task) throws IOException {
        if(cmd.length<2){
            task.raiseError("Error: Lack of argument.",this);
        }

        Console csl=task.getConsole();
        String pwd=cmd[1];
        String os = System.getProperty("os.name").toLowerCase();
        Pattern p=null;
        if(os.contains("windows")){
            p=Pattern.compile("a");
        }
    }
}
