package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.*;

public class FindPeakElement extends AbstractProblem {

    public int findPeakElement(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        return find(nums, 0, nums.length-1);
    }

    public int find(int[] nums, int s, int e) {
        if (e - s < 1) {
            return e;
        }
        if (nums[s+1] < nums[s]) {
            return s;
        }
        if (nums[e-1] < nums[e]) {
            return e;
        }
        int mid = (e + s)/2;
        if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
            return mid;
        }
        if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid] + 1) {
            return find(nums, mid, e);
        }
        return find(nums, s, mid);

    }


    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));
        setOutputParser(new IntParser());

        addInput("[1,2,3,1]");
        addOutput("2");

        addInput("[1,2,1,3,5,6,4]");
        addOutput("5");

        addInput("[1,2,1,3,5,6,4]");
        addOutput("5");

        addInput("[14]");
        addOutput("0");

        addInput("[1,2]");
        addOutput("1");

    }

    @Override
    Object execute(Object[] input) {
        return findPeakElement((int[]) input[0]);
    }

    public static void main(String[] args) {
        new FindPeakElement().runTest();
    }
}
