package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.CharParser;
import org.sansilab.leetcode.parser.QuotedParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

public class ValidSudoku extends AbstractProblem {

    public boolean isValidSudoku(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 宫格内，某位数字是否已经被摆放
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int blockIndex = i / 3 * 3 + j / 3;
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionArrayParser(new CharParser()));

        addInput("[" +
                "  [\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"]," +
                "  [\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"]," +
                "  [\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"]," +
                "  [\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"]," +
                "  [\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"]," +
                "  [\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"]," +
                "  [\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"]," +
                "  [\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"]," +
                "  [\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]" +
                "]");
        outputList.add(true);
        addInput("[" +
                "  [\"8\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"]," +
                "  [\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"]," +
                "  [\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"]," +
                "  [\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"]," +
                "  [\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"]," +
                "  [\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"]," +
                "  [\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"]," +
                "  [\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"]," +
                "  [\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]" +
                "]");
        outputList.add(false);
    }

    @Override
    Object execute(Object[] input) {
        return isValidSudoku((char[][]) input[0]);
    }

    public static void main(String[] args) {
        new ValidSudoku().runTest();
    }
}
