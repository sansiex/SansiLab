package org.sansilab.leetcode.problem;

// https://leetcode.com/problems/sudoku-solver/
public final class SudokuSolver {
    public static void main(String[] args) {
        SudokuSolver sln = new SudokuSolver();

        char[][] p1 = {
                 {'5', '3', '.'     , '.', '7', '.'     , '.', '.', '.'}
                ,{'6', '.', '.'     , '1', '9', '5'     , '.', '.', '.'}
                ,{'.', '9', '8'     , '.', '.', '.'     , '.', '6', '.'}

                ,{'8', '.', '.'     , '.', '6', '.'     , '.', '.', '3'}
                ,{'4', '.', '.'     , '8', '.', '3'     , '.', '.', '1'}
                ,{'7', '.', '.'     , '.', '2', '.'     , '.', '.', '6'}

                ,{'.', '6', '.'     , '.', '.', '.'     , '2', '8', '.'}
                ,{'.', '.', '.'     , '4', '1', '9'     , '.', '.', '5'}
                ,{'.', '.', '.'     , '.', '8', '.'     , '.', '7', '9'}
        };
        //1
        long s = System.currentTimeMillis();
        sln.solveSudoku(p1);
        long e = System.currentTimeMillis();
        System.out.println(e - s + " ms");
        InitUtils.print2d(p1);
    }

    public void solveSudoku(char[][] board) {
        search(board, 0);
    }

    private boolean search(char[][] b, int p){
        if (p==81) {
            return true;
        }
        int i=p/9;
        int j=p%9;
        if (b[i][j]=='.') {
            for (char m = '1'; m <= '9'; m++) {
                b[i][j]= m;
                if (check(b, i, j)) {
                    if (search(b, p+1)) {
                        return true;
                    }
                }
            }
            b[i][j]='.';
            return false;
        } else if (p==80){
            return true;
        } else {
            return search(b, p+1);
        }
    }

    private boolean check(char[][] t, int i, int j){
        char v = t[i][j];
        for (int k = 0; k < 9; k++) {
            if (k!=i && v==t[k][j]) {
                return false;
            }
            if (k!=j && v==t[i][k]) {
                return false;
            }
        }

        int is = (i/3)*3;
        int js = (j/3)*3;
        for (int k = is; k < is+3; k++) {
            for (int l = js; l < js+3; l++) {
                if (k!=i || l!=j) {
                    if (v==t[k][l]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

