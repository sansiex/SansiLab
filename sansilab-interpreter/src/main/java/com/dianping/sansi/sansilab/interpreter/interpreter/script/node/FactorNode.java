package com.dianping.sansi.sansilab.interpreter.interpreter.script.node;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.SansiScript;

/**
 * Created by lenovo on 2014/6/14.
 */
public class FactorNode extends ValueNode {
    public FactorNode(int i) {
        super(i);
    }

    public FactorNode(SansiScript p, int i) {
        super(p, i);
    }

    @Override
    public int getValue() {
        if(this.jjtGetNumChildren()>0){
            ValueNode vn= (ValueNode) this.jjtGetChild(0);
            return vn.getValue();
        }
        return Integer.parseInt(this.getText());
    }
}
