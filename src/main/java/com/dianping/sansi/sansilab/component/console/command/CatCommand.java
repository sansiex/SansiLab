package com.dianping.sansi.sansilab.component.console.command;

import com.dianping.sansi.sansilab.component.console.Console;
import com.dianping.sansi.sansilab.component.file.TextFileReader;

import java.io.*;

/**
 * Created by lenovo on 2014/6/12.
 */
public class CatCommand implements ConsoleCommand{
    @Override
    public void doCommand(String[] cmd, Reader reader, Writer writer, Console csl) throws IOException {
        String name=cmd[1];
        String pwd=csl.getPwd();
        String path=pwd+File.separator+name;
        if(name.indexOf(":"+ File.separator)==1 || name.startsWith("/")){
            path=name;
        }

        TextFileReader tfr=null;
        try {
           tfr=new TextFileReader(path);
        } catch (FileNotFoundException e) {
            writeLine(writer,Console.OUTPUT_ERROR_HEAD);
            writeLine(writer,e.getMessage());
        }

        String line=tfr.readLine();
        while(line!=null){
            writeLine(writer,line);
            line=tfr.readLine();
        }
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        CatCommand gc=new CatCommand();
        gc.doCommand(new String[]{"cat","e:\\data\\test.txt"},null,new OutputStreamWriter(System.out),new Console());
    }
}
