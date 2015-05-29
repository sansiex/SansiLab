package com.dianping.sansi.sansilab.interpreter.controller;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.ParseException;
import com.dianping.sansi.sansilab.interpreter.interpreter.script.SansiScript;
import com.dianping.sansi.sansilab.interpreter.interpreter.script.node.Node;
import com.dianping.sansi.sansilab.interpreter.interpreter.script.node.ProcedureNode;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2014/6/15.
 */
public class ScriptInterpretor {
    public static List<Node> execScript(String script) throws ParseException {
        StringReader sr=new StringReader(script);
        SansiScript parser=new SansiScript(sr);
        ProcedureNode pn= (ProcedureNode) parser.procedure();
        sr.close();
        ArrayList<Node> list=new ArrayList<>(pn.jjtGetNumChildren());
        for (int i = 0; i < pn.jjtGetNumChildren(); i++) {
            list.add(pn.jjtGetChild(i));
        }
        return list;
    }
}
