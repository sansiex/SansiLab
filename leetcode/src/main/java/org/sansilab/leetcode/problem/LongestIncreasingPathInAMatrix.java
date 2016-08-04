package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public final class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix sln = new LongestIncreasingPathInAMatrix();
        List<Object[]> parr = new ArrayList<>();
        parr.add(new Object[]{
                new int[][]{
                        {9,9,4}
                        ,{6,6,8}
                        ,{2,1,1}
                }
        });
        parr.add(new Object[]{
                new int[][]{
                        {3,4,5}
                        ,{3,2,6}
                        ,{2,2,1}
                }
        });
        parr.add(new Object[]{
                new int[][]{{1, 1}}
        });
        //3
        for (Object[] arr : parr) {
            int[][] p1 = (int[][]) arr[0];

            long s = System.currentTimeMillis();
            Object output = sln.longestIncreasingPath(p1);
            long e = System.currentTimeMillis();
            System.out.println(e - s + " ms");
            System.out.println(JsonUtils.toJson(output));
            System.out.println("=================================================");
        }
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length==0 || matrix[0].length==0) {
            return 0;
        }
        int[][] p=new int[matrix.length][matrix[0].length];
        int maxl=0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (p[i][j]<1) {
                    maxl=Math.max(1+search(matrix, p, i,j,0), maxl);
                }
            }
        }
        return maxl;
    }

    private int[][] dir=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    private int search(int[][] m, int[][] p, int i, int j, int l){
        p[i][j]=l;
        int maxl=l;
        for (int k = 0; k < dir.length; k++) {
            int[] d=dir[k];
            int ni=i+d[0];
            int nj=j+d[1];
            if (ni<0 || ni>=m.length || nj<0 || nj>=m[0].length) {
                continue;
            }
            if (l<p[ni][nj]) {
                continue;
            }
            if (m[i][j]>=m[ni][nj]) {
                continue;
            }
            maxl=Math.max(search(m, p, ni, nj, l+1), maxl);
        }
        return maxl;
    }
}
