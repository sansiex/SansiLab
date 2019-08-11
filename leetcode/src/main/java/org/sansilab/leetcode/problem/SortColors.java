package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

public class SortColors extends AbstractProblem {
    public void sortColors(int[] nums) {
        if (nums.length < 1) {
            return;
        }
        int p0 = 0;
        int p2 = nums.length-1;
        while (p0<p2 && nums[p0] == 0) {
            p0++;
        }
        while (p2>p0 && nums[p2] == 2) {
            p2--;
        }
        if (p0 == p2) {
            return;
        }

        int p1 = p0;
        while (p1 <= p2) {
            if (nums[p1] == 1) {
                p1++;
            } else {
                if (nums[p1] == 0) {
                    if (p0 != p1) {
                        swap(nums, p0, p1);
                    } else {
                        p1++;
                    }
                    p0++;
                } else if (nums[p1] == 2) {
                    if (p2 != p1) {
                        swap(nums, p2, p1);
                    } else {
                        p1++;
                    }
                    p2--;
                }
            }
        }
    }

    void swap(int[] arr, int a, int b){
        int t=arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));
        setOutputParser(new ArrayParser(new IntParser()));

        addInput("[2,0]");
        addOutput("[0,2]");

        addInput("[2,0,2,1,1,0]");
        addOutput("[0,0,1,1,2,2]");

        addInput("[2]");
        addOutput("[2]");

        addInput("[2,2,2,2]");
        addOutput("[2,2,2,2]");

        addInput("[1,2,0]");
        addOutput("[0,1,2]");

    }

    @Override
    Object execute(Object[] input) {
        sortColors((int[]) input[0]);
        return input[0];
    }

    public static void main(String[] args) {
        new SortColors().runTest();
    }
}
