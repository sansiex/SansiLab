package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
public final class FindMinimumInRotatedSortedArray2 {
    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray2 sln = new FindMinimumInRotatedSortedArray2();
        List<Object[]> parr = new ArrayList<>();
        parr.add(new Object[]{
                new int[]{4,5, 6, 7, 0, 1, 2}
        });
        parr.add(new Object[]{
                new int[]{0, 1, 2, 4, 5, 6, 7}
        });
        parr.add(new Object[]{
                new int[]{1}
        });
        parr.add(new Object[]{
                new int[]{5,5,5,6,8,8,9,2,2,3,5,5}
        });
        parr.add(new Object[]{
                new int[]{1,1}
        });
        //3
        for (Object[] arr:parr) {
            int[] p1 = (int[]) arr[0];

            long s = System.currentTimeMillis();
            Object output = sln.findMin(p1);
            long e = System.currentTimeMillis();
            System.out.println(e - s + " ms");
            System.out.println(JsonUtils.toJson(output));
            System.out.println("=================================================");
        }
    }

    public int findMin(int[] nums) {
        return bs(nums, 0, nums.length-1);
    }

    private int bs(int[] nums, int s, int e){
        if (s==e) {
            return nums[s];
        }
        int m = (s+e)/2;
        if (nums[m]<nums[e]) {
            if (nums[m]>= nums[s]) {
                return nums[s];
            } else {
                return bs(nums, s, m);
            }
        } else if (nums[m]==nums[e]) {
            if (nums[m]>nums[s]) {
                return nums[s];
            } else if (nums[m]<nums[s]) {
                return bs(nums, s, m);
            } else {
                return Math.min(bs(nums, s, m), bs(nums, m, e-1));
            }
        } else {
            return bs(nums, m+1, e);
        }
    }
}

