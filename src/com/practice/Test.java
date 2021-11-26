package com.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Given a board and an end position for the player, write a function to determine if it is possible to travel from every
// open cell on the board to the given end position.
//
// board1 = [
//     [ 0,  0,  0, 0, -1 ],
//     [ 0, -1, -1, 0,  0 ],
//     [ 0,  0,  0, 0,  0 ],
//     [ 0, -1,  0, 0,  0 ],
//     [ 0,  0,  0, 0,  0 ],
//     [ 0,  0,  0, 0,  0 ],
// ]
//
// board2 = [
//     [  0,  0,  0, 0, -1 ],
//     [  0, -1, -1, 0,  0 ],
//     [  0,  0,  0, 0,  0 ],
//     [ -1, -1,  0, 0,  0 ],
//     [  0, -1,  0, 0,  0 ],
//     [  0, -1,  0, 0,  0 ],
// ]
//
// board3 = [
//     [ 0,  0,  0,  0,  0,  0, 0 ],
//     [ 0, -1, -1, -1, -1, -1, 0 ],
//     [ 0, -1,  0,  0,  0, -1, 0 ],
//     [ 0, -1,  0,  0,  0, -1, 0 ],
//     [ 0, -1,  0,  0,  0, -1, 0 ],
//     [ 0, -1, -1, -1, -1, -1, 0 ],
//     [ 0,  0,  0,  0,  0,  0, 0 ],
// ]
//
// board4 = [
//     [0,  0,  0,  0, 0],
//     [0, -1, -1, -1, 0],
//     [0, -1, -1, -1, 0],
//     [0, -1, -1, -1, 0],
//     [0,  0,  0,  0, 0],
// ]
//
// board5 = [
//     [0],
// ]
//
// end1 = (0, 0)
// end2 = (5, 0)
//
// Expected output:
//
// isReachable(board1, end1) -> True
// isReachable(board1, end2) -> True
// isReachable(board2, end1) -> False
// isReachable(board2, end2) -> False
// isReachable(board3, end1) -> False
// isReachable(board4, end1) -> True
// isReachable(board5, end1) -> True
//
//
// n: width of the input board
// m: height of the input board

// DONE 2nd question => Fixed the missing case. Thank you!
// Ashish Awasthi

public class Test {
    public static void main(String[] argv) {
        int[][] board1 = new int[][] {
                { 0,  0,  0, 0, -1 },
                { 0, -1, -1, 0,  0 },
                { 0,  0,  0, 0,  0 },
                { 0, -1,  0, 0,  0 },
                { 0,  0,  0, 0,  0 },
                { 0,  0,  0, 0,  0 },
        };

        int[][] board2 = new int[][] {
                {  0,  0,  0, 0, -1 },
                {  0, -1, -1, 0,  0 },
                {  0,  0,  0, 0,  0 },
                { -1, -1,  0, 0,  0 },
                {  0, -1,  0, 0,  0 },
                {  0, -1,  0, 0,  0 },
        };

        int[][] board3 = new int[][] {
                { 0,  0,  0,  0,  0,  0, 0 },
                { 0, -1, -1, -1, -1, -1, 0 },
                { 0, -1,  0,  0,  0, -1, 0 },
                { 0, -1,  0,  0,  0, -1, 0 },
                { 0, -1,  0,  0,  0, -1, 0 },
                { 0, -1, -1, -1, -1, -1, 0 },
                { 0,  0,  0,  0,  0,  0, 0 },
        };

        int[][] board4 = new int[][] {
                { 0,  0,  0,  0, 0 },
                { 0, -1, -1, -1, 0 },
                { 0, -1, -1, -1, 0 },
                { 0, -1, -1, -1, 0 },
                { 0,  0,  0,  0, 0 },
        };

        int[][] board5 = new int[][] {
                { 0 },
        };

        int[] end1 = new int[] {0, 0};
        int[] end2 = new int[] {5, 0};

        //List<Integer[]> result = findLegalMoves(board, start7);
        //result.forEach(ar -> System.out.println(Arrays.toString(ar)));

        boolean result = traverse(board1, end1);
        System.out.println(result);

        //ALL CASES PASSED!
        //NICE!!
        //this looks great!
        //Thank you very much !
        //nicely done! it was great meeting you :)
        //AA:  Same here :)
    }

    public static boolean traverse(int[][] board, int[] position){
        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] point = new Integer[]{position[0], position[1]};
        queue.offer(point);

        while(!queue.isEmpty()){
            Integer[] temp = queue.poll();
            List<Integer[]> neighbors = findLegalMoves(board, temp);
            board[temp[0]][temp[1]] = 1;
            if(neighbors != null){
                neighbors.forEach(neighbor -> queue.offer(neighbor));
            }
        }

//     for(int i=0; i<board.length; i++){
//       for(int j=0; j<board[0].length; j++){
//         System.out.print("   " + board[i][j]);
//       }
//       System.out.println();
//     }

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == 0) return false;
            }
        }
        return true;
    }

    public static List<Integer[]> findLegalMoves(int[][] board, Integer[] position){
        int x = position[0];
        int y = position[1];

        if(board[x][y] == 1) return null;

        List<Integer[]> result = new ArrayList<>();
        int[][] moves = new int[][]{{x,  y+1}, {x+1, y},  {x, y-1}, {x-1, y}};

        int xMax = board.length;
        int yMax = board[0].length;

        for(int i=0; i<moves.length; i++){
            int newX = moves[i][0];
            int newY = moves[i][1];
            if(newX >=0 && newY >=0 && newX < xMax && newY < yMax && board[newX][newY] == 0){
                Integer[] tuple = new Integer[]{newX, newY};
                result.add(tuple);
            }
        }
        return result;
    }
}