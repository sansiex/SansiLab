package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

public class MaximumGap extends AbstractProblem {
    public int maximumGap(int[] nums) {
        if (nums.length<2) {
            return 0;
        }


        return 0;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));

        addInput("[3,6,9,1]");
        outputList.add(3);

        addInput("[10]");
        outputList.add(0);
    }

    @Override
    Object execute(Object[] input) {
        return maximumGap((int[]) input[0]);
    }

    public static void main(String[] args) {
        new MaximumGap().runTest();
    }
}
