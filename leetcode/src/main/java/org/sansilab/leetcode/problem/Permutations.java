package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations extends AbstractProblem {
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        int cnt = 1;
        for (int i = 2; i <= nums.length; i++) {
            cnt *= i;
        }
        List ret = new ArrayList(cnt);
        recursive(nums, 0, ret);

        return ret;
    }

    public void recursive(int[] nums, int start, List ret){
        for (int i = start; i < nums.length; i++) {
            if (i == start) {
                if (start == nums.length - 1) {
                    List arr = new ArrayList(nums.length);
                    for (int j = 0; j < nums.length; j++) {
                        arr.add(nums[j]);
                    }
                    ret.add(arr);
                }
                recursive(nums, start+1, ret);
            } else {
                swap(nums, start, i);
                recursive(nums, start+1, ret);
                swap(nums, start, i);
            }
        }
    }

    public void swap(int[] arr, int a, int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));
        setOutputParser(new TwoDimensionListParser(new IntParser()));

        addInput("[]");
        addOutput("[]");

        addInput("[1]");
        addOutput("[\n" +
                "  [1]\n" +
                "]");

        addInput("[1,2]");
        addOutput("[\n" +
                "  [1,2],\n" +
                "  [2,1]\n" +
                "]");

        addInput("[1,2,3]");
        addOutput("[\n" +
                "  [1,2,3],\n" +
                "  [1,3,2],\n" +
                "  [2,1,3],\n" +
                "  [2,3,1],\n" +
                "  [3,2,1],\n" +
                "  [3,1,2]\n" +
                "]");
    }

    @Override
    Object execute(Object[] input) {
        return permute((int[]) input[0]);
    }

    public static void main(String[] args) {
        new Permutations().runTest();
    }
}
