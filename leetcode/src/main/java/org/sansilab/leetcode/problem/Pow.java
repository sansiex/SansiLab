package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.DoubleParser;
import org.sansilab.leetcode.parser.IntParser;

public class Pow extends AbstractProblem {
    public double myPow(double x, int n) {
        if (n == 1) {
            return x;
        } else if (n==0) {
            return 1d;
        }
        int half = Math.abs(n/2);
        double ret = 0;

        double val = myPow(x, half);
        if (n%2==0) {
            ret = val*val;
        } else {
            ret = x*val*val;
        }
        if (n<0) {
            return 1/ret;
        }
        return ret;
    }

    @Override
    void init() {
        parserList.add(new DoubleParser());
        parserList.add(new IntParser());
        setOutputParser(new DoubleParser());

        addInput("2.00000", "10");
        addOutput("1024.00000");

        addInput("2.10000", "3");
        addOutput("9.26100");

        addInput("2.00000", "-2");
        addOutput("0.25000");
    }

    @Override
    Object execute(Object[] input) {
        return myPow((Double)input[0], (Integer) input[1]);
    }

    public static void main(String[] args) {
        new Pow().runTest();
    }
}
