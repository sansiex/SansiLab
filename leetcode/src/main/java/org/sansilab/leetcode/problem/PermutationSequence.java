package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;

public class PermutationSequence extends AbstractProblem {
    public String getPermutation(int n, int k) {
        if (n == 1) return "1";
        int[] cnt = new int[n-1];
        cnt[n-2] = 1;
        for (int i = n-3; i > -1; i--) {
            cnt[i] = cnt[i+1]*(n-i-1);
        }

        int[] orders = new int[n-1];
        int rem = k;
        for (int i = 0; i <n-1 ; i++) {
            orders[i] = (rem-1)/cnt[i];
            rem -= orders[i]*cnt[i];
        }
        int[] nums = new int[n];

        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            int order = i == n-1?0:orders[i];
            int p = -1;
            while (order>=0) {
                p++;
                order --;
                while (nums[p] == 1) {
                    p++;
                }

            }
            val[i] = p+1;
            nums[p]=1;

        }
        StringBuilder sb = new StringBuilder();
        for (int v:val) {
            sb.append(v);
        }

        return sb.toString();
    }

    @Override
    void init() {
        parserList.add(new IntParser());
        parserList.add(new IntParser());

        addInput("3", "3");
        outputList.add("213");

        addInput("4", "9");
        outputList.add("2314");
        addInput("4", "10");
        outputList.add("2341");
        addInput("4", "11");
        outputList.add("2413");
        addInput("4", "12");
        outputList.add("2431");
        addInput("4", "13");
        outputList.add("3124");
    }

    @Override
    Object execute(Object[] input) {
        return getPermutation((int)input[0], (int)input[1]);
    }

    public static void main(String[] args) {
        new PermutationSequence().runTest();
    }
}
