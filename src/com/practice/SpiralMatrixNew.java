package com.practice;

import java.util.Arrays;

class SpiralMatrixNew {
    static int[] res;
    static int counter = 0;
    static int[] spiralCopy(int[][] mat) {
        int rows = mat.length-1, cols = mat[0].length-1, row = 0, col = 0;
        int total = (rows+1)*(cols+1);
        res = new int[total];

        while(counter < total){
            traverse(row, col, row, cols-col, mat);
            traverse(row+1, cols-col, rows-row, cols-col, mat);
            traverse(rows-row, cols-col-1, rows-row, col, mat);
            traverse(rows-row-1, col, row+1, col, mat);
            row++; col++;
        }
        Arrays.stream(res).forEach(System.out::println);
        return res;
    }

    static void traverse(int x1, int y1, int x2, int y2, int[][] mat){
        if(counter == mat.length * mat[0].length) return;
        if(x1 < 0 || x1 >= mat.length || y1 < 0 || y2 >= mat[0].length) return;
        while(x1 != x2 || y1 != y2){
            if(x1 < x2) res[counter++] = mat[x1++][y1];
            if(x1 > x2) res[counter++] = mat[x1--][y1];
            if(y1 < y2) res[counter++] = mat[x1][y1++];
            if(y1 > y2) res[counter++] = mat[x1][y1--];
        }
        res[counter++] = mat[x1][y1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,    2,   3,  4,    5},
                {6,    7,   8,  9,   10},
                {11,  12,  13,  14,  15},
                {16,  17,  18,  19,  20}
        };
        spiralCopy(arr);
    }

}
