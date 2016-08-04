package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/trapping-rain-water/
public final class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater sln = new TrappingRainWater();
        List<Object[]> parr = new ArrayList<>();
        parr.add(new Object[]{
                new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
        });
        parr.add(new Object[]{
                new int[]{0, 1, 2, 4, 5, 6, 7}
        });
        parr.add(new Object[]{
                new int[]{1}
        });
        parr.add(new Object[]{
                new int[]{5, 5, 5, 6, 8, 8, 9, 2, 2, 3, 5, 5}
        });
        parr.add(new Object[]{
                new int[]{1, 1}
        });
        //3
        for (Object[] arr : parr) {
            int[] p1 = (int[]) arr[0];

            long s = System.currentTimeMillis();
            Object output = sln.trap(p1);
            long e = System.currentTimeMillis();
            System.out.println(e - s + " ms");
            System.out.println(JsonUtils.toJson(output));
            System.out.println("=================================================");
        }
    }

    public int trap(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[max] < height[i]) {
                max = i;
            }
        }
        int ret = 0;
        int ch = 0;
        for (int i = 0; i < max; i++) {
            if (height[i] < ch) {
                ret += ch - height[i];
            } else {
                ch = height[i];
            }
        }
        ch = 0;
        for (int i = height.length - 1; i > max; i--) {
            if (height[i] < ch) {
                ret += ch - height[i];
            } else {
                ch = height[i];
            }
        }
        return ret;
    }
}
