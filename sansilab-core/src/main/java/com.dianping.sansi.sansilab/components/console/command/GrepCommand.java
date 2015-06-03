package com.dianping.sansi.sansilab.components.console.command;

import com.dianping.sansi.sansilab.components.console.ConsoleTask;
import com.dianping.sansi.sansilab.components.file.TextReader;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2014/6/22.
 */
public class GrepCommand extends com.dianping.sansi.sansilab.components.console.command.ConsoleCommand {
    @Override
    public void doCommand(String[] cmd, Reader reader, Writer writer, ConsoleTask task) throws IOException {
        //System.out.println("Execute command: "+StringHelper.join(cmd));
        int index=1;
        if(index>=cmd.length){
            task.raiseError("Error: Lack of arguments.",this);
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
                        task.raiseError("Error: Can't recognize option '" + c + "'.",this);
                        return;
                }
            }

            if(index>=cmd.length){
                task.raiseError("Error: Lack of pattern.",this);
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
                task.raiseError("Error: Lack of input.",this);
                return;
            }
            r=new FileReader(cmd[index++]);
        }
        TextReader tfr=new TextReader(r);

        String line=tfr.readLine();
        while(line!=null){
            Matcher m=p.matcher(line);

            if(m.find()){
                if(task.isError()){
                    tfr.close();
                    return;
                }
                writeLine(writer, line);
            }
            line=tfr.readLine();
        }
        tfr.close();
        if(reader==null){
            r.close();
        }
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        GrepCommand gc=new GrepCommand();
        gc.doCommand(new String[]{"grep","-i","a","e:\\data\\test.txt"},null,new OutputStreamWriter(System.out),null);
    }
}
