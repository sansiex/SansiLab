package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueens extends AbstractProblem {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new LinkedList<>();
        char[][] b = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(b[i], '.');
        }

        back(ret, b, 0);
        return ret;
    }

    void back(List<List<String>> ret, char[][] b, int n) {
        if (n == b.length) {
            List<String> val = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                val.add(new String(b[i]));
            }
            ret.add(val);
            return;
        }

        for (int i = 0; i < b.length; i++) {
            b[n][i] = 'Q';
            if (check(b, n, i)) {
                back(ret, b, n+1);
            }
            b[n][i] = '.';
        }
    }

    boolean check(char[][] b, int x, int y){
        int n = b.length;
        for (int i = 0; i < n; i++) {
            if (b[x][i] == 'Q' && i!=y) {
                return false;
            }
            if (b[i][y] == 'Q' && i!=x) {
                return false;
            }
            int d = y - i;
            int x0 = x-d;
            int x1 = x+d;
            if (x0 >-1 && x0 < n && b[x0][i] == 'Q' && i!=y) {
                return false;
            }
            if (x1 >-1 && x1 < n && b[x1][i] == 'Q' && i!=y) {
                return false;
            }
        }
        return true;
    }

    @Override
    void init() {
        parserList.add(new IntParser());
        setOutputParser(new TwoDimensionListParser(new StringParser()));

        addInput("4");
        addOutput("[\n" +
                " [\".Q..\",\n" +
                "  \"...Q\",\n" +
                "  \"Q...\",\n" +
                "  \"..Q.\"],\n" +
                "\n" +
                " [\"..Q.\",\n" +
                "  \"Q...\",\n" +
                "  \"...Q\",\n" +
                "  \".Q..\"]\n" +
                "]\n" +
                "\n");
    }

    @Override
    Object execute(Object[] input) {
        return solveNQueens((Integer) input[0]);
    }

    public static void main(String[] args) {
        new NQueens().runTest();
    }
}
