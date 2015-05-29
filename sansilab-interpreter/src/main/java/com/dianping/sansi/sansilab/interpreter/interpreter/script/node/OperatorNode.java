package com.dianping.sansi.sansilab.interpreter.interpreter.script.node;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.SansiScript;

/**
 * Created by lenovo on 2014/6/14.
 */
public abstract class OperatorNode extends ValueNode{
    public OperatorNode(int i) {
        super(i);
    }



    public OperatorNode(SansiScript p, int i) {
        super(p, i);
    }

    public int getOperand(int i){
        ValueNode vn= (ValueNode) this.jjtGetChild(i);
        return vn.getValue();
    }
}
