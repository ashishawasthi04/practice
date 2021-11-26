package com.practice;

import java.util.Arrays;

class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        sudokuSolve(board);
    }

    static boolean sudokuSolve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValid(board, k, i, j)) {
                            board[i][j] = k;
                            //System.out.println(i + ": " + j + "  => " + board[i][j]);
                            if (sudokuSolve(board)) return true;
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        for(char[] row : board) System.out.println(Arrays.toString(row));
        return true;
    }

    static boolean isValid(char[][] board, int num, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == num) return false;
            if (board[i][y] == num) return false;
        }

        int boxX = x / 3, boxY = y / 3;
        for (int i = 3 * boxX; i < (3 * boxX) + 3; i++) {
            for (int j = 3 * boxY; j < (3 * boxY) + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }
}

