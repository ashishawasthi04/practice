package com.dbx;

public class GameOfLife {
    //https://leetcode.com/problems/game-of-life/
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                checkAndSet(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) board[i][j] = 1;
                else if (board[i][j] == -1) board[i][j] = 0;
            }
        }
    }

    public void checkAndSet(int[][] board, int i, int j) {
        int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        int newX = 0, newY = 0, liveCount = 0;
        for (int idx = 0; idx < 8; idx++) {
            newX = i + dx[idx];
            newY = j + dy[idx];
            if (newX < 0 || newY < 0 || newX >= board.length || newY >= board[0].length) {
                continue;
            }
            if (board[newX][newY] == 1 || board[newX][newY] == -1) {
                liveCount++;
            }

        }
        if (board[i][j] == 1) {
            if (liveCount < 2 || liveCount > 3) board[i][j] = -1;
        } else {
            if (liveCount == 3) board[i][j] = 2;
        }
    }
}
