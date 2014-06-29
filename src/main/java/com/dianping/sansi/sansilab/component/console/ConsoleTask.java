package com.dianping.sansi.sansilab.component.console;

import com.dianping.sansi.interpreter.interpreter.script.node.*;
import com.dianping.sansi.sansilab.component.console.command.CommandManager;
import com.dianping.sansi.sansilab.component.console.command.ConsoleCommand;
import com.dianping.sansi.sansilab.component.console.exception.CommandNotFountException;
import com.dianping.sansi.sansilab.component.console.exception.InvalidCommandException;

import java.io.*;

/**
 * Created by lenovo on 2014/6/25.
 */
public class ConsoleTask {
    protected boolean error=false;
    protected StatementNode node;
    protected Console csl;
    protected Writer out;

    public ConsoleTask(StatementNode node,Writer writer,Console csl){
        this.node=node;
        this.csl=csl;
        out=writer;
    }

    public void executeTask(Reader reader, Writer writer) throws InvalidCommandException, IOException, CommandNotFountException {
        Node cld=node.jjtGetChild(0);
        execUnit(cld,reader,writer);
        writer.flush();
    }

    protected void execUnit(Node n, Reader reader, Writer writer) throws IOException, CommandNotFountException, InvalidCommandException {
        if(n instanceof ExpressionNode){
            execExpression((ExpressionNode) n,reader,writer);
        }else if(n instanceof CommandNode){
            execCommand((CommandNode) n,reader,writer);
        }else if(n instanceof PipeNode){
            execPipe((PipeNode) n,reader,writer);
        }
    }

    protected void execExpression(ExpressionNode en, Reader reader, Writer writer) throws IOException {
        int val=en.getValue();
        writer.write(String.valueOf(val));
    }

    protected void execPipe(PipeNode pn, Reader reader, Writer writer) throws IOException, CommandNotFountException, InvalidCommandException {
        PipedReader pr=new PipedReader();
        PipedWriter pw=new PipedWriter(pr);

        Node c1=pn.jjtGetChild(0);
        Node c2=pn.jjtGetChild(1);

        new Thread(() -> {
            try{
                execUnit(c1, reader, pw);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CommandNotFountException e) {
                e.printStackTrace();
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }finally{
                try {
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        execUnit(c2,pr,writer);
        pr.close();
    }

    protected void execCommand(CommandNode cn, Reader reader, Writer writer) throws CommandNotFountException, InvalidCommandException, IOException {
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

    synchronized public void raiseError(String message, ConsoleCommand cmd){
        if(error){
            return;
        }

        error=true;

        try {
            String sname=cmd.getClass().getSimpleName();
            out.write("Error occurs when executing command: "+sname.substring(0,sname.length()-7).toLowerCase()+"\n");
            out.write(message+"\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isError() {
        return error;
    }

//    public boolean setError(boolean error) {
//        boolean ret=this.error;
//        this.error = error;
//        return ret;
//    }

    public StatementNode getNode() {
        return node;
    }

    public void setNode(StatementNode node) {
        this.node = node;
    }

    public Console getConsole() {
        return csl;
    }

    public void setConsolel(Console csl) {
        this.csl = csl;
    }

    public Writer getOut() {
        return out;
    }

    public void setOut(Writer out) {
        this.out = out;
    }
}
