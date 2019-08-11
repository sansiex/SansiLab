package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.TwoDimensionCharArrayParser;

public class SurroundedRegions extends AbstractProblem {
    int[] dh = {0,1,0,-1};
    int[] dw = {1,0,-1,0};

    public void solve(char[][] board) {
        int h = board.length;
        if (h == 0) {
            return;
        }
        int w = board[0].length;
        for (int i = 0; i < h; i++) {
            flip(board, i, 0);
            flip(board, i, w-1);
        }
        for (int i = 1; i < w-1; i++) {
            flip(board, 0, i);
            flip(board, h-1, i);
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                char c = board[i][j];
                if (c == 'F') {
                    board[i][j] = 'O';
                } else if (c == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void flip(char[][] board, int i, int j) {
        char c = board[i][j];
        if (c == 'O') {
            board[i][j] = 'F';
            for (int k = 0; k < 4; k++) {
                int ni = i+dh[k];
                int nj = j+dw[k];
                if (ni >=0 && ni<board.length && nj >=0 && nj<board[0].length) {
                    flip(board, ni, nj);
                }
            }
        }
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionCharArrayParser());
        setOutputParser(new TwoDimensionCharArrayParser());

        addInput("[\"XXXX\"," +
                "\"XOOX\"," +
                "\"XXOX\"," +
                "\"XOXX\"]");
        addOutput("[\"XXXX\"," +
                "\"XXXX\"," +
                "\"XXXX\"," +
                "\"XOXX\"]");

        addInput("[\"XOOX\"," +
                "\"XOXX\"," +
                "\"XXOX\"," +
                "\"XOOX\"]");
        addOutput("[\"XOOX\"," +
                "\"XOXX\"," +
                "\"XXOX\"," +
                "\"XOOX\"]");

        addInput("[[\"OXO\"],[\"XOX\"],[\"OXO\"]]");
        addOutput("[[\"OXO\"],[\"XXX\"],[\"OXO\"]]");
    }

    @Override
    Object execute(Object[] input) {
        solve((char[][]) input[0]);
        return input[0];
    }

    public static void main(String[] args) {
        new SurroundedRegions().runTest();
    }
}
