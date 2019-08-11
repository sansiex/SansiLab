package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

public class UniquePaths2 extends AbstractProblem {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] != 1) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] != 1) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                int up = dp[i-1][j];
                int left = dp[i][j-1];
                dp[i][j] = up+left;
            }
        }
        return dp[m-1][n-1];
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionArrayParser(new IntParser()));
        parserList.add(new IntParser());

        addInput("[\n" +
                "  [0,0,0],\n" +
                "  [0,1,0],\n" +
                "  [0,0,0]\n" +
                "]");
        outputList.add(2);
        addInput("[\n" +
                "  [0,0,0],\n" +
                "  [0,1,0],\n" +
                "  [0,1,0]\n" +
                "]");
        outputList.add(1);
        addInput("[\n" +
                "  [0,0,0],\n" +
                "  [0,0,0],\n" +
                "  [0,1,0]\n" +
                "]");
        outputList.add(3);
    }

    @Override
    Object execute(Object[] input) {
        return uniquePathsWithObstacles((int[][])input[0]);
    }

    public static void main(String[] args) {
        new UniquePaths2().runTest();
    }
}
