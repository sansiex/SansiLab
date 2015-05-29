package com.dianping.sansi.sansilab.interpreter.interpreter.script.node;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.SansiScript;

/**
 * Created by lenovo on 2014/6/15.
 */
public class ProcedureNode extends SimpleNode {
    public ProcedureNode(int i) {
        super(i);
    }

    public ProcedureNode(SansiScript p, int i) {
        super(p, i);
    }
}
