package com.dianping.sansi.sansilab.interpreter.interpreter.calculator;

import com.dianping.sansi.sansilab.interpreter.interpreter.script.node.ExpressionNode;
import com.dianping.sansi.sansilab.interpreter.interpreter.script.node.SimpleNode;

import java.io.*;

/**
 * Created by lenovo on 2014/6/15.
 */
public class CalculatorInterpretor {
    public void interpret(Reader in, Writer out){
        Calculator parser = new Calculator(in);
        SimpleNode root = null;
        try {
            root = parser.statement();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ExpressionNode en = (ExpressionNode) root.jjtGetChild(0);
        int result=en.getValue();
        try {
            out.write(String.valueOf(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interpret(InputStream in, OutputStream out){
        Calculator parser = new Calculator(in);
        SimpleNode root = null;
        try {
            root = parser.statement();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ExpressionNode en = (ExpressionNode) root.jjtGetChild(0);
        int result=en.getValue();
        try {
            byte[] bytes=String.valueOf(result).getBytes();
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
