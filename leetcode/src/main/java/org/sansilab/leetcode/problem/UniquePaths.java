package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;

public class UniquePaths extends AbstractProblem {
    public int uniquePaths(int m, int n) {
        long u = 1;
        int b = Math.max(m-1, n-1);
        int s = Math.min(m-1, n-1);
        for (int i = b+1; i <= b+s; i++) {
            u*=i;
        }
        for (int i = 1; i <= s; i++) {
            u/=i;
        }
        return (int) u;

    }

    @Override
    void init() {
        parserList.add(new IntParser());
        parserList.add(new IntParser());

        addInput("3","2");
        outputList.add(3);
        addInput("7","3");
        outputList.add(28);
        addInput("7","33");
        outputList.add(28);
    }

    @Override
    Object execute(Object[] input) {
        return uniquePaths((int)input[0], (int)input[1]);
    }

    public static void main(String[] args) {
        new UniquePaths().runTest();
    }
}
