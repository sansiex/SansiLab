package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

public class RemoveDuplicatesFromSortedArray2 extends AbstractProblem {
    public int removeDuplicates(int[] nums) {
        if (nums.length<3) {
            return nums.length;
        }
        int p=1;
        int last = nums[0];
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            int v = nums[i];
            if (v == last) {
                len++;
                if (len<3) {
                    if (i>p) {
                        nums[p] = v;
                    }
                    p++;
                }
            } else {
                len = 1;
                last = v;
                if (i>p) {
                    nums[p] = v;
                }
                p++;
            }
        }
        return p;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));

        addInput("[0,0,1,1,1,1,2,3,3]");
        outputList.add(7);

        addInput("[1,1,1,2,2,3]");
        outputList.add(5);

        addInput("[]");
        outputList.add(0);

        addInput("[1,2,3]");
        outputList.add(3);
    }

    @Override
    Object execute(Object[] input) {
        Object v = removeDuplicates((int[]) input[0]);
        printResult(input[0]);
        return v;
    }

    public static void main(String[] args) {
        new RemoveDuplicatesFromSortedArray2().runTest();
    }
}
