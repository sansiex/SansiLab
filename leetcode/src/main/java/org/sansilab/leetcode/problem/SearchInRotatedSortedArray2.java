package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

public final class SearchInRotatedSortedArray2 extends AbstractProblem {


    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int t = bsTurn(nums, 0, nums.length-1);
        if (t == -1) {
            return bs(nums, target, 0, nums.length-1);
        } else {
            boolean ret = bs(nums, target, 0, t);
            if (ret) {
                return true;
            }
            return bs(nums, target, t+1, nums.length-1);
        }
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

    boolean bs(int[] nums, int t, int s, int e) {
        if (s == e) {
            return nums[s] == t;
        }

        int m = (s+e)/2;
        if (nums[m] == t) {
            return true;
        } else if (nums[m] > t) {
            return bs(nums, t, s, m);
        } else {
            return bs(nums, t, m+1, e);
        }
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));
        parserList.add(new IntParser());

        addInput("[2,5,6,0,0,1,2]", "0");
        outputList.add(true);

        addInput("[2,5,6,0,0,1,2]", "3");
        outputList.add(false);

        addInput("[2]", "3");
        outputList.add(false);

        addInput("[2]", "1");
        outputList.add(false);

        addInput("[2]", "2");
        outputList.add(true);

        addInput("[1,2,3]", "2");
        outputList.add(true);

        addInput("[]", "1");
        outputList.add(false);
    }

    @Override
    Object execute(Object[] input) {
        return search((int[])input[0], (Integer) input[1]);
    }

    public static void main(String[] args) {
        new SearchInRotatedSortedArray2().runTest();
    }
}

