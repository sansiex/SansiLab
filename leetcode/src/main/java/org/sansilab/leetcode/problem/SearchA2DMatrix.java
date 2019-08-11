package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

public class SearchA2DMatrix extends AbstractProblem {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        return recursice(matrix, target, 0, matrix.length*matrix[0].length-1);
    }

    boolean recursice(int[][] matrix, int t, int s, int e) {
        int h = matrix.length;
        int w = matrix[0].length;
        int size = h*w;
        int m = (s+e)/2;
        int mh = m/w;
        int mw = m%w;
        int v = matrix[mh][mw];
        if (v == t) {
            return true;
        } else {
            if (s == e) {
                return false;
            } else if (v<t){
                return recursice(matrix, t, m+1, e);
            } else {
                if (m == s) {
                    return false;
                } else {
                    return recursice(matrix, t, s, m-1);
                }
            }
        }
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionArrayParser(new IntParser()));
        parserList.add(new IntParser());

        addInput("[\n" +
                "  [1,   3,  5,  7],\n" +
                "  [10, 11, 16, 20],\n" +
                "  [23, 30, 34, 50]\n" +
                "]", "3");
        outputList.add(true);

        addInput("[\n" +
                "  [1,   3,  5,  7],\n" +
                "  [10, 11, 16, 20],\n" +
                "  [23, 30, 34, 50]\n" +
                "]", "13");
        outputList.add(false);

        addInput("[\n" +
                "  [1,   3,  5,  7],\n" +
                "  [10, 11, 16, 20],\n" +
                "  [23, 30, 34, 50]\n" +
                "]", "0");
        outputList.add(false);


        addInput("[\n" +
                "  [1,   3,  5,  7],\n" +
                "  [10, 11, 16, 20],\n" +
                "  [23, 30, 34, 50]\n" +
                "]", "1");
        outputList.add(true);


        addInput("[\n" +
                "  [1,   3,  5,  7],\n" +
                "  [10, 11, 16, 20],\n" +
                "  [23, 30, 34, 50]\n" +
                "]", "99");
        outputList.add(false);
        addInput("[\n" +
                "  [1,   3,  5,  7],\n" +
                "  [10, 11, 16, 20],\n" +
                "  [23, 30, 34, 50]\n" +
                "]", "49");
        outputList.add(false);

        addInput("[\n" +
                "  [1,   3,  5,  7],\n" +
                "  [10, 11, 16, 20],\n" +
                "  [23, 30, 34, 50]\n" +
                "]", "50");
        outputList.add(true);

    }

    @Override
    Object execute(Object[] input) {
        return searchMatrix((int[][])input[0], (Integer) input[1]);
    }

    public static void main(String[] args) {
        new SearchA2DMatrix().runTest();
    }
}
