package org.sansilab.leetcode.problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum extends BaseProblem {
    public static void main(String[] args) {
        TwoSum problem = new TwoSum();
        int[] input11 = {2, 7, 11, 15};
        int input12 = 9;
        int[] result1 = problem.twoSum(input11, input12);
        problem.printResult(result1);

        int[] input21 = {0,4,3,0};
        int input22 = 0;
        int[] result2 = problem.twoSum(input21, input22);
        problem.printResult(result2);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int sup = target-val;
            if (map.containsKey(sup)) {
                int ind = map.get(sup);
                return new int[]{ind, i};
            }
            map.put(val,i);
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

}
