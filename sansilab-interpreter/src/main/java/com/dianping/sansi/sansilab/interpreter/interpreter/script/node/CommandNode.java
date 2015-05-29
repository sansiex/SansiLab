package com.dianping.sansi.sansilab.interpreter.interpreter.script.node;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.SansiScript;
import com.google.common.base.Joiner;

import java.util.ArrayList;

/* Generated By:JJTree: Do not edit this line. CommandNode.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class CommandNode extends SimpleNode {
    private ArrayList<String> cmd = new ArrayList<>();

    public CommandNode(int id) {
        super(id);
    }

    public CommandNode(SansiScript p, int id) {
        super(p, id);
    }

    public String getCommand(int i) {
        if (i >= cmd.size() || i<0) {
            return null;
        }
        return cmd.get(i);
    }

    public int getSize(){
        return cmd.size();
    }

    public void add(String part){
        cmd.add(part);
    }

    public String getCommand(){
        return Joiner.on(" ").join(cmd);
    }

    public String[] toArray(){
        String[] ret=new String[cmd.size()];
        for (int i = 0; i < cmd.size(); i++) {
            ret[i]=cmd.get(i);
        }
        return ret;
    }
}
/* JavaCC - OriginalChecksum=9879f1b869a250e11a1738e9769cbad2 (do not edit this line) */
