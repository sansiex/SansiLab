package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.*;

// https://leetcode.com/problems/first-missing-positive/
public final class FirstMissingPositive {
    public static void main(String[] args) {
        FirstMissingPositive sln = new FirstMissingPositive();
        int[] p1 = {1};
        //3
        long s = System.currentTimeMillis();
        Object output = sln.firstMissingPositive(p1);
        long e = System.currentTimeMillis();
        System.out.println(e - s + " ms");
        System.out.println(JsonUtils.toJson(output));
    }

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length;) {
            int v = nums[i];
            if (v!=i+1 && v>0 && v<nums.length && nums[v-1]!=v) {
                nums[i]=nums[v-1];
                nums[v-1]=v;
            } else {
                i++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=i+1) {
                return i+1;
            }
        }
        return nums.length+1;
    }
}

