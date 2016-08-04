package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/n-queens-ii/
public final class NQueens2 {
    public static void main(String[] args) {
        NQueens2 sln = new NQueens2();
        List<Object[]> parr = new ArrayList<>();
        parr.add(new Object[]{3});
        //3
        for (Object[] arr:parr) {
            int p1 = (int) arr[4];

            long s = System.currentTimeMillis();
            Object output = sln.totalNQueens(p1);
            long e = System.currentTimeMillis();
            System.out.println(e - s + " ms");
            System.out.println(JsonUtils.toJson(output));
        }
    }

    public int totalNQueens(int n) {
        int[] qs=new int[n];
        return count(qs, 0, n);
    }

    private int count(int[] qs, int col, int n){
        int c=0;
        for (int i = 0; i < n; i++) {
            if (check(qs, i, col)) {
                if (col==n-1) {
                    c++;
                } else {
                    qs[col]=i;
                    c+=count(qs, col+1, n);
                }
            }
        }
        return c;
    }

    private boolean check(int[] qs, int i, int j) {
        for (int k = 0; k < j; k++) {
            int q = qs[k];
            if (q == i) {
                return false;
            }
            if (i-q==j-k) {
                return false;
            }
            if (i-q==k-j) {
                return false;
            }
        }
        return true;
    }
}

