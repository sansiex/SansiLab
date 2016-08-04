package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-the-duplicate-number/
public final class FindTheDuplicateNumber {
    public static void main(String[] args) {
        FindTheDuplicateNumber sln = new FindTheDuplicateNumber();
        List<Object[]> parr = new ArrayList<>();
        parr.add(new Object[]{
                new int[]{1,3,6,6,2,4,6}
        });
        parr.add(new Object[]{
                new int[]{1,3,4,2,2}
        });
        //3
        for (Object[] arr:parr) {
            int[] p1 = (int[]) arr[0];

            long s = System.currentTimeMillis();
            Object output = sln.findDuplicate(p1);
            long e = System.currentTimeMillis();
            System.out.println(e - s + " ms");
            System.out.println(JsonUtils.toJson(output));
            System.out.println("=================================================");
        }
    }

    public int findDuplicate(int[] nums) {
        int l=1;
        int h=nums.length-1;
        while (l<=h) {
            int m=(l+h)/2;
            int cnt=0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i]<=m) {
                    cnt++;
                }
            }
            if (cnt<=m) {
                l=m+1;
            } else if (cnt>m) {
                h=m-1;
            }
        }
        return l;
    }
}

