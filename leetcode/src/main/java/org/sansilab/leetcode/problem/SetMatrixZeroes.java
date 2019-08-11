package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.ArrayList;
import java.util.Arrays;

public class SetMatrixZeroes extends AbstractProblem {
    public int[][] setZeroes(int[][] matrix) {
        if (matrix.length == 0) {
            return matrix;
        }

        int h = matrix.length;
        int w = matrix[0].length;
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int val = matrix[i][j];
                if (val == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i = 0; i < w; i++) {
            for(int v:rows) {
                matrix[v][i] = 0;
            }
        }


        for (int i = 0; i < h; i++) {
            for(int v:cols) {
                matrix[i][v] = 0;
            }
        }
        return matrix;
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionArrayParser(new IntParser()));
        setOutputParser(new TwoDimensionArrayParser(new IntParser()));

        addInput("[\n" +
                "  [1,1,1],\n" +
                "  [1,0,1],\n" +
                "  [1,1,1]\n" +
                "]");
        addOutput("[\n" +
                "  [1,0,1],\n" +
                "  [0,0,0],\n" +
                "  [1,0,1]\n" +
                "]");

        addInput("[\n" +
                "  [0,1,2,0],\n" +
                "  [3,4,5,2],\n" +
                "  [1,3,1,5]\n" +
                "]");
        addOutput("[\n" +
                "  [0,0,0,0],\n" +
                "  [0,4,5,0],\n" +
                "  [0,3,1,0]\n" +
                "]");
    }

    @Override
    Object execute(Object[] input) {
        return setZeroes((int[][]) input[0]);
    }

    public static void main(String[] args) {
        new SetMatrixZeroes().runTest();
    }
}
