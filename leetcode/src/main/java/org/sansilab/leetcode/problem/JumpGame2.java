package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.Arrays;

public class JumpGame2 extends AbstractProblem {

    public int jump(int[] nums) {
        int[] arr = new int[nums.length];
        Arrays.fill(arr, -1);
        int last = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int end = Math.min(i + val + 1, nums.length);
            if (end > last) {
                Arrays.fill(arr, last, end, i);
                last = end;
                if (last >= nums.length) {
                    break;
                }
            } else {
                continue;
            }
        }

        int cnt = 0;
        int ind = nums.length - 1;
        while (ind > 0) {
            ind = arr[ind];
            cnt++;
        }
        return cnt;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));

        addInput("[1,2,3]");
        outputList.add(2);
        addInput("[2,3,1,1,4]");
        outputList.add(2);
        addInput("[2]");
        outputList.add(0);
        addInput("[9,8,7,6,5,4,3,2,1,1]");
        outputList.add(1);
        addInput("[3,1,1,3,1,1,3,1,1,3,1,1,3,1,1,1]");
        outputList.add(5);
        addInput("[3,1,1,3,1,5,3,1,1,3,5,1,3,1,1,1]");
        outputList.add(4);
    }

    @Override
    Object execute(Object[] input) {
        return jump((int[]) input[0]);
    }

    public static void main(String[] args) {
        new JumpGame2().runTest();
    }
}
