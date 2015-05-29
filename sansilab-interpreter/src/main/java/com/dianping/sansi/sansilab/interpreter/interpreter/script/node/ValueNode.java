package com.dianping.sansi.sansilab.interpreter.interpreter.script.node;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.SansiScript;

/**
 * Created by lenovo on 2014/6/14.
 */
public abstract class ValueNode extends SimpleNode{
    public ValueNode(int i) {
        super(i);
    }

    public ValueNode(SansiScript p, int i) {
        super(p, i);
    }

    public abstract int getValue();
}
