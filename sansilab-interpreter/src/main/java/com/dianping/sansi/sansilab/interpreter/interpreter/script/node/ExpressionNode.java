package com.dianping.sansi.sansilab.interpreter.interpreter.script.node;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.SansiScript;

/**
 * Created by lenovo on 2014/6/14.
 */
public class ExpressionNode extends ValueNode {

    public ExpressionNode(int i) {
        super(i);
    }

    public ExpressionNode(SansiScript p, int i) {
        super(p, i);
    }

    public int getValue(){
        ValueNode vn= (ValueNode) this.jjtGetChild(0);
        return vn.getValue();
    }
}
