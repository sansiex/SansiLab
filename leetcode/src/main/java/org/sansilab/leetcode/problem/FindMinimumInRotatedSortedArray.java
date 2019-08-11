package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

public class FindMinimumInRotatedSortedArray extends AbstractProblem {
    public int findMin(int[] nums) {
        int t = bsTurn(nums, 0, nums.length-1);
        if (t == -1 || t == nums.length-1) {
            return nums[0];
        }
        return nums[t+1];
    }

    int bsTurn(int[] nums, int s, int e) {
        if (s>=e-1) {
            if (nums[e]<nums[s]) {
                return s;
            } else {
                return -1;
            }
        }
        int m = (s+e)/2;
        if (nums[m+1] < nums[m]) {
            return m;
        } else {
            int t = bsTurn(nums, s, m);
            if (t != -1) {
                return t;
            }
            return bsTurn(nums, m+1, e);
        }
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));

        addInput("[3,4,5,1,2]");
        outputList.add(1);

        addInput("[4,5,6,7,0,1,2]");
        outputList.add(0);
    }

    @Override
    Object execute(Object[] input) {
        return findMin((int[]) input[0]);
    }

    public static void main(String[] args) {
        new FindMinimumInRotatedSortedArray().runTest();
    }
}
