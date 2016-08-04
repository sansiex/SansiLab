package org.sansilab.leetcode.problem;

import java.util.*;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
public final class BestTimeToBuyAndSellStock4 {
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock4 sln = new BestTimeToBuyAndSellStock4();
//        int k = 2;
//        int[] prices = {1,2,4,2,5,7,2,4,9,0,9};
        //17
//        int k = 2;
//        int[] prices = {2,6,8,7,8,7,9,4,1,2,4,5,8};
        //14
//        int k = 2;
//        int[] prices = {8,6,4,3,3,2,3,5,8,3,8,2,6};
        //11
        int k = 2;
        int[] prices = {1,4,2};
        //3
        long s = System.currentTimeMillis();
        Object output = sln.maxProfit(k, prices);
        long e = System.currentTimeMillis();
        System.out.println(e - s + " ms");
        System.out.println(output.toString());
    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length <= 1 || k == 0) {
            return 0;
        }
        if (k>prices.length/2) {
            return solveBigK(k, prices);
        }

        int l = prices.length;
        int[][] local = new int[k+1][l];
        int[][] global = new int[k+1][l];

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < l; j++) {
                int diff = prices[j]-prices[j-1];
                local[i][j] = Math.max(global[i-1][j-1]+diff, local[i][j-1]+diff);
                global[i][j] = Math.max(global[i][j-1], local[i][j]);
            }
        }

        return global[k][l-1];
    }

    private int solveBigK(int k, int[] prices){
        int s = 0;
        int p=0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= prices[i-1]) {
                if (i==prices.length-1) {
                    p+=prices[i]-prices[s];
                }
                continue;
            } else {
                if (i == s + 1) {
                    s=i;
                } else {
                    int profit = prices[i-1]-prices[s];
                    p+=profit;
                    s=i;
                }
            }
        }

        return p;
    }
}

