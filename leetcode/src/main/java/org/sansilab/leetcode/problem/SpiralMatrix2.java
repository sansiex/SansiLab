package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.ListParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix2 extends AbstractProblem {

    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        if (n == 0) {
            return ret;
        }
        int cnt = n*n;
        int[] bx = {0, n-1, n-1, 0};
        int[] by = {n-1, n-1, 0, 0};
        int[] hd = {0,1,0,-1};
        int[] wd = {1,0,-1,0};

        int d=0;
        int cx = 0;
        int cy = 0;
        int c = 0;
        while(c<cnt) {
            ret[cx][cy] = c+1;
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
        parserList.add(new IntParser());
        setOutputParser(new TwoDimensionArrayParser(new IntParser()));

        addInput("2");
        addOutput("[\n" +
                " [ 1, 2],\n" +
                " [ 4, 3]\n" +
                "]");

        addInput("3");
        addOutput("[\n" +
                " [ 1, 2, 3 ],\n" +
                " [ 8, 9, 4 ],\n" +
                " [ 7, 6, 5 ]\n" +
                "]");
    }

    @Override
    Object execute(Object[] input) {
        return generateMatrix((Integer) input[0]);
    }

    public static void main(String[] args) {
        new SpiralMatrix2().runTest();
    }
}
