package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subset extends AbstractProblem {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new LinkedList<>();
        recursive(nums, 0, new LinkedList<>(), ret);
        return ret;
    }

    void recursive(int[] nums, int s, List<Integer> pre, List<List<Integer>> ret) {
        if (s == nums.length) {
            ret.add(pre);
            return;
        }

        recursive(nums, s+1, pre, ret);
        List<Integer> l = new LinkedList<>();
        l.addAll(pre);
        l.add(nums[s]);
        recursive(nums, s+1, l, ret);
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));
        setOutputParser(new TwoDimensionListParser(new IntParser()));

        addInput("[]");
        addOutput("[[]]");

        addInput("[1,2,3]");
        addOutput("[[],[3],[2],[2,3],[1],[1,3],[1,2],[1,2,3]]");
    }

    @Override
    Object execute(Object[] input) {
        return subsets((int[]) input[0]);
    }

    public static void main(String[] args) {
        new Subset().runTest();
    }
}
