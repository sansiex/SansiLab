package com.dianping.sansi.sansilab.interpreter.interpreter.script.node;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.SansiScript;

/**
 * Created by lenovo on 2014/6/15.
 */
public class StatementNode extends SimpleNode {
    public StatementNode(int i) {
        super(i);
    }

    public StatementNode(SansiScript p, int i) {
        super(p, i);
    }
}
