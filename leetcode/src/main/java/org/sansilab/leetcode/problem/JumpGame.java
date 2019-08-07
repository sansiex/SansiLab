package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.Arrays;

public class JumpGame extends AbstractProblem {
    public boolean canJump(int[] nums) {
        int[] arr = new int[nums.length];
        Arrays.fill(arr, -1);
        int last = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int end = Math.min(i + val + 1, nums.length);
            if (end > last) {
                if (last == i && i>0) {
                    return false;
                }
                Arrays.fill(arr, last, end, i);
                last = end;
                if (last >= nums.length) {
                    break;
                }
            } else {
                continue;
            }
        }
        return true;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));

        addInput("[2,3,1,1,4]");
        outputList.add(true);

        addInput("[3,2,1,0,4]");
        outputList.add(false);
    }

    @Override
    Object execute(Object[] input) {
        return canJump((int[]) input[0]);
    }

    public static void main(String[] args) {
        new JumpGame().runTest();
    }
}
