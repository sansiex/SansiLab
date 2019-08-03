package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

public class RotateImage extends AbstractProblem {

    public int[][] rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) {
            return matrix;
        }

        int h = n/2;
        int w = n/2 + n%2;

        int[] hs={0, n-1, n-1, 0};
        int[] ws={n-1, n-1, 0, 0};
        int[] hi={-1, -1, 1, 1};
        int[] wi={1, -1, -1, 1};

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int val = matrix[i][j];
                int ci = i;
                int cj = j;
                for (int k = 0; k < 4; k++) {
                    int si = hs[k];
                    int sj = ws[k];

                    int oi = ci*hi[k];
                    int oj = cj*wi[k];
                    if (k%2==0) {
                        int t = oi;
                        oi = oj;
                        oj = t;
                    }
                    int ni = si+oi;
                    int nj = sj+oj;

                    int nv = matrix[ni][nj];
                    matrix[ni][nj] = val;
                    val = nv;
                }
            }
        }

        return matrix;
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionArrayParser(new IntParser()));
        setOutputParser(new TwoDimensionArrayParser(new IntParser()));

        addInput("[\n" +
                "  [1,2,3,4],\n" +
                "  [4,5,6,7],\n" +
                "  [7,8,9,10],\n" +
                "  [8,9,10,11]\n" +
                "]");
        addOutput("[\n" +
                "  [8,7,4,1],\n" +
                "  [9,8,5,2],\n" +
                "  [10,9,6,3],\n" +
                "  [11,10,7,4]\n" +
                "]");

        addInput("[\n" +
                "  [1,2,3],\n" +
                "  [4,5,6],\n" +
                "  [7,8,9]\n" +
                "]");
        addOutput("[\n" +
                "  [7,4,1],\n" +
                "  [8,5,2],\n" +
                "  [9,6,3]\n" +
                "]");

        addInput("[\n" +
                "  [1,2],\n" +
                "  [3,4]\n" +
                "]");
        addOutput("[\n" +
                "  [3,1],\n" +
                "  [4,2]\n" +
                "]");
    }

    @Override
    Object execute(Object[] input) {
        return rotate((int[][]) input[0]);
    }

    public static void main(String[] args) {
        new RotateImage().runTest();
    }
}
