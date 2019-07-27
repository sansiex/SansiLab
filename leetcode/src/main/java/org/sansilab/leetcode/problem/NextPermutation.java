package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.Arrays;

public class NextPermutation extends AbstractProblem {

    public int[] nextPermutation(int[] nums) {
        int last = Integer.MIN_VALUE;
        for (int i = nums.length-1; i > -1 ; i--) {
            int val = nums[i];
            if (val < last) {
                for (int j = nums.length-1; j > i ; j--) {
                    if (nums[j] > val) {
                        swap(nums, i, j);
                        Arrays.sort(nums, i+1, nums.length);
                        return nums;
                    }
                }

                return nums;
            }
            last = val;
        }
        for (int i = 0; i < nums.length/2; i++) {
            swap(nums, i, nums.length-i-1);
        }
        return nums;
    }

    void swap(int[] arr, int ia, int ib){
        int t = arr[ia];
        arr[ia] = arr[ib];
        arr[ib] = t;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));

        addInput("[2,2,0,4,3,1]");
        outputList.add(new int[]{2,2,1,0,3,4});
        addInput("[1,2,3]");
        outputList.add(new int[]{1,3,2});
        addInput("[1,3,2]");
        outputList.add(new int[]{2,1,3});
        addInput("[3,2,1]");
        outputList.add(new int[]{1,2,3});
        addInput("[1,1,5]");
        outputList.add(new int[]{1,5,1});
        addInput("[1,1,9,7,6,6,6,5]");
        outputList.add(new int[]{1,5,1,6,6,6,7,9});
    }

    @Override
    Object execute(Object[] input) {
        return nextPermutation((int[]) input[0]);
    }

    public static void main(String[] args) {
        new NextPermutation().runTest();
    }
}
