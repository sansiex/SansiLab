package com.dianping.sansi.sansilab.component.console.command;

import com.dianping.sansi.sansilab.component.console.Console;
import com.dianping.sansi.sansilab.component.file.TextFileReader;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2014/6/22.
 */
public class GrepCommand implements ConsoleCommand{
    @Override
    public void doCommand(String[] cmd, Reader reader, Writer writer, Console csl) throws IOException {
        int index=1;
        if(index>=cmd.length){
            writeLine(writer, Console.OUTPUT_ERROR_HEAD);
            writeLine(writer, "Lack of arguments.");
            return;
        }

        String arg1=cmd[index++];
        String reg=null;
        int flag=0;
        if(arg1.startsWith("-")){
            for (int i = 1; i < arg1.length(); i++) {
                char c=arg1.charAt(i);
                switch(c){
                    case 'i':
                        flag|=Pattern.CASE_INSENSITIVE;
                        break;
                    default:
                        writeLine(writer, Console.OUTPUT_ERROR_HEAD);
                        writeLine(writer, "Can't recognize option '" + c + "'.");
                        return;
                }
            }

            if(index>=cmd.length){
                writeLine(writer, Console.OUTPUT_ERROR_HEAD);
                writeLine(writer, "Lack of pattern.");
                return;
            }
            reg=cmd[index++];
        }else{
            reg=arg1;
        }

        Pattern p= Pattern.compile(reg,flag);
        Reader r=reader;
        if(r==null){
            if(index>=cmd.length){
                writeLine(writer, Console.OUTPUT_ERROR_HEAD);
                writeLine(writer, "Lack of input.");
                return;
            }
            r=new FileReader(cmd[index++]);
        }
        TextFileReader tfr=new TextFileReader(r);

        String line=tfr.readLine();
        while(line!=null){
            Matcher m=p.matcher(line);
            if(m.find()){
                writeLine(writer, line);
            }
            line=tfr.readLine();
        }
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        GrepCommand gc=new GrepCommand();
        gc.doCommand(new String[]{"grep","-i","a","e:\\data\\test.txt"},null,new OutputStreamWriter(System.out),new Console());
    }
}
