package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.ListParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix extends AbstractProblem {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int h = matrix.length;
        int w = matrix[0].length;
        int cnt = h*w;
        List ret = new ArrayList(cnt);
        int[] bx = {0, h-1, h-1, 0};
        int[] by = {w-1, w-1, 0, 0};
        int[] hd = {0,1,0,-1};
        int[] wd = {1,0,-1,0};
        int d=0;
        int cx = 0;
        int cy = 0;
        int c = 0;
        while(c<cnt) {
            ret.add(matrix[cx][cy]);
            if (cx == bx[d] && cy == by[d]) {
                if (d == 0) {
                    bx[0]++;
                    bx[3]++;
                } else if (d == 1) {
                    by[0]--;
                    by[1]--;
                } else if (d == 2) {
                    bx[1]--;
                    bx[2]--;
                } else {
                    by[2]++;
                    by[3]++;
                }
                d=(d+1)%4;
            }
            cx = cx+hd[d];
            cy = cy+wd[d];
            c++;
        }


        return ret;
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionArrayParser(new IntParser()));
        setOutputParser(new ListParser(new IntParser()));


        addInput("[\n" +
                " [ 1],\n" +
                " [ 4],\n" +
                " [ 7]\n" +
                "]");
        addOutput("[1,4,7]");

        addInput("[\n" +
                " [ 1, 2, 3 ],\n" +
                " [ 4, 5, 6 ],\n" +
                " [ 7, 8, 9 ]\n" +
                "]");
        addOutput("[1,2,3,6,9,8,7,4,5]");

        addInput("[\n" +
                "  [1, 2, 3, 4],\n" +
                "  [5, 6, 7, 8],\n" +
                "  [9,10,11,12]\n" +
                "]");
        addOutput("[1,2,3,4,8,12,11,10,9,5,6,7]");
    }

    @Override
    Object execute(Object[] input) {
        return spiralOrder((int[][]) input[0]);
    }

    public static void main(String[] args) {
        new SpiralMatrix().runTest();
    }
}
