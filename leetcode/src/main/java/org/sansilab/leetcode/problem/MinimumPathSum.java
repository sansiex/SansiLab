package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

public class MinimumPathSum extends AbstractProblem {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int up = dp[i-1][j];
                int left = dp[i][j-1];
                dp[i][j] = Math.min(up, left) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionArrayParser(new IntParser()));
        parserList.add(new IntParser());

        addInput("[\n" +
                "  [1,3,1],\n" +
                "  [1,5,1],\n" +
                "  [4,2,1]\n" +
                "]");
        outputList.add(7);
        addInput("[\n" +
                "  [1,8,1],\n" +
                "  [1,5,1],\n" +
                "  [4,2,1]\n" +
                "]");
        outputList.add(9);
    }

    @Override
    Object execute(Object[] input) {
        return minPathSum((int[][])input[0]);
    }

    public static void main(String[] args) {
        new MinimumPathSum().runTest();
    }
}
