package com.dianping.sansi.sansilab.component.console;

import com.dianping.sansi.interpreter.controller.ScriptInterpretor;
import com.dianping.sansi.interpreter.interpreter.script.ParseException;
import com.dianping.sansi.interpreter.interpreter.script.node.*;
import com.dianping.sansi.sansilab.component.console.command.CommandManager;
import com.dianping.sansi.sansilab.component.console.command.ConsoleCommand;
import com.dianping.sansi.sansilab.component.console.exception.CommandNotFountException;
import com.dianping.sansi.sansilab.component.console.exception.InvalidCommandException;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sansi on 2014/5/21.
 */
public class Console {
    public static final String OPT_END_OUTPUT="end_output";
    public static final String OUTPUT_ERROR_HEAD="!";

    private String pwd;
    private HashMap<String,String> env=new HashMap<>();

    public void execScript(String script, Reader reader, Writer writer) throws ParseException, IOException, CommandNotFountException, InvalidCommandException {
        List<Node> list=ScriptInterpretor.execScript(script);
        for(Node n:list){
            StatementNode sn= (StatementNode) n;
            execStatement(sn,reader,writer);
        }
    }

    public void execStatement(StatementNode sn, Reader reader, Writer writer) throws IOException, CommandNotFountException, InvalidCommandException {
        Node cld=sn.jjtGetChild(0);
        execUnit(cld,reader,writer);
    }

    public void execUnit(Node n, Reader reader, Writer writer) throws IOException, CommandNotFountException, InvalidCommandException {
        if(n instanceof ExpressionNode){
            execExpression((ExpressionNode) n,reader,writer);
        }else if(n instanceof CommandNode){
            execCommand((CommandNode) n,reader,writer);
        }else if(n instanceof PipeNode){
            execPipe((PipeNode) n,reader,writer);
        }
    }

    public void execExpression(ExpressionNode en, Reader reader, Writer writer) throws IOException {
        int val=en.getValue();
        writer.write(String.valueOf(val));
    }

    public void execPipe(PipeNode pn, Reader reader, Writer writer) throws IOException, CommandNotFountException, InvalidCommandException {
        PipedReader pr=new PipedReader();
        PipedWriter pw=new PipedWriter(pr);

        Node c1=pn.jjtGetChild(0);
        Node c2=pn.jjtGetChild(1);

        new Thread(() -> {
            try {
                execUnit(c1, reader, pw);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CommandNotFountException e) {
                e.printStackTrace();
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }
        }).start();
        execUnit(c2,pr,writer);
    }

    private void execCommand(CommandNode cn, Reader reader, Writer writer) throws CommandNotFountException, InvalidCommandException, IOException {
        String[] cmd=cn.toArray();
        if (cmd == null || cmd.length == 0) {
            throw new InvalidCommandException();
        }
        String name = cmd[0];

        ConsoleCommand cc = CommandManager.getInstance().getCommand(name);
        if (cc == null) {
            throw new CommandNotFountException(name);
        }

        cc.doCommand(cmd, reader, writer, this);
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
