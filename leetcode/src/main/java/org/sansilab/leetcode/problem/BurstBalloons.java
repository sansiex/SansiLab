package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/burst-balloons/
public final class BurstBalloons {
    public static void main(String[] args) {
        BurstBalloons sln = new BurstBalloons();
        List<Object[]> parr = new ArrayList<>();
        parr.add(new Object[]{
                new int[]{3, 1, 5, 8}
        });
        parr.add(new Object[]{
                new int[]{1,3,4,2,2}
        });
        //3
        for (Object[] arr:parr) {
            int[] p1 = (int[]) arr[0];

            long s = System.currentTimeMillis();
            Object output = sln.maxCoins(p1);
            long e = System.currentTimeMillis();
            System.out.println(e - s + " ms");
            System.out.println(JsonUtils.toJson(output));
            System.out.println("=================================================");
        }
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[nums.length+2];
        arr[0]=1;
        arr[nums.length+1]=1;
        for (int i = 0  ; i < nums.length; i++) {
            arr[i+1]=nums[i];
        }
        int[][] dp = new int[n+2][n+2];
        for (int i = n; i >= 0; i--) {
            for (int j = i+1; j <= n+1; j++) {
                for (int k = i+1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], arr[i]*arr[k]*arr[j]+dp[i][k]+dp[k][j]);
                }
            }
        }
        return dp[0][n+1];
    }
}

