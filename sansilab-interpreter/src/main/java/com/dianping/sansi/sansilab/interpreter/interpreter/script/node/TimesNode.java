package com.dianping.sansi.sansilab.interpreter.interpreter.script.node;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.SansiScript;

/**
 * Created by lenovo on 2014/6/14.
 */
public class TimesNode extends OperatorNode {
    public TimesNode(int i) {
        super(i);
    }

    public TimesNode(SansiScript p, int i) {
        super(p, i);
    }

    @Override
    public int getValue() {
        return this.getOperand(0) * this.getOperand(1);
    }
}
