package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.Arrays;
import java.util.HashSet;

public class MaximumGap extends AbstractProblem {
    public int maximumGap(int[] nums) {
        if (nums.length<2) {
            return 0;
        }

        int len = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
            set.add(nums[i]);
        }
        int n = set.size();

        int bsize = (max - min + 1)/n;
        if ((max - min + 1)%n > 0) {
            bsize ++;
        }
        if (bsize == 1) {
            return 0;
        }

        int[] minb = new int[n];
        int[] maxb = new int[n];
        Arrays.fill(minb, Integer.MAX_VALUE);
        Arrays.fill(maxb, Integer.MIN_VALUE);
        for (int val:set) {
            int b = (val - min) / bsize;
            minb[b] = Math.min(val, minb[b]);
            maxb[b] = Math.max(val, maxb[b]);
        }

        int gap = 0;
        int last = 0;
        for (int i = 1; i < n; i++) {
            if (minb[i] == Integer.MAX_VALUE) {
                continue;
            }
            int cg = minb[i] - maxb[last];
            gap = Math.max(gap, cg);
            last = i;
        }

        return gap;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));

        addInput("[1,1,1,1,1,5,5,5,5,5]");
        outputList.add(4);

        addInput("[1,1,1,1]");
        outputList.add(0);

        addInput("[1,2,999999]");
        outputList.add(999997);

        addInput("[3,6,9,1,4,5,7,8,10]");
        outputList.add(2);

        addInput("[3,6,9,1,4,5,7,8]");
        outputList.add(2);

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
