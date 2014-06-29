package com.dianping.sansi.sansilab.component.console.command;

import com.dianping.sansi.sansilab.component.console.Console;
import com.dianping.sansi.sansilab.component.console.ConsoleTask;
import com.dianping.sansi.sansilab.component.file.TextReader;
import com.dianping.sansi.sansilab.utils.StringHelper;

import java.io.*;

/**
 * Created by lenovo on 2014/6/12.
 */
public class CatCommand implements ConsoleCommand{
    @Override
    public void doCommand(String[] cmd, Reader reader, Writer writer, ConsoleTask task) throws IOException {
        if(cmd.length<2){
            task.raiseError("Error: Lack of argument.",this);
        }
        String name=cmd[1];
        Console csl=task.getConsole();
        String pwd=csl.getPwd();
        String path=pwd+File.separator+name;
        if(name.indexOf(":"+ File.separator)==1 || name.startsWith("/")){
            path=name;
        }

        TextReader tfr=null;
        try {
            tfr=new TextReader(path);
        } catch (FileNotFoundException e) {
            tfr.close();
            task.raiseError(e.getMessage(),this);
            return;
        }

        String line=tfr.readLine();
        while(line!=null){
            if(task.isError()){
                tfr.close();
                return;
            }
            writeLine(writer,line);
            line=tfr.readLine();
        }
        tfr.close();
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        CatCommand gc=new CatCommand();
        gc.doCommand(new String[]{"cat","e:\\data\\test.txt"},null,new OutputStreamWriter(System.out),null);
    }
}
