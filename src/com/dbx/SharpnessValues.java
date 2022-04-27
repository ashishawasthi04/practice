package com.dbx;

/*
 * Description 1:
 * You’re given an elevation map for a rectangular area of land. The map is represented by 2-D array of numbers where
 * each cell contains the elevation above sea level of the corresponding area of the map.
 * You need a path that connects the west edge of the map with the east edge of the map.
 * Starting at the west edge of the map you can only move in single cell steps east, southeast, or northeast.
 * You need to find how much can the sea level rise before submerging all such paths.
 * Write a function that takes in a 2-D array and returns a single number.
 * west         east
 * ----------------- north
 * | 1 | 2 | 3 | 9 |
 * -----------------
 * | 8 | 6 | 10| 8 |
 * -----------------
 * | 9 | 4 | 11| 12|
 * -----------------  south// sea=6
 */

import java.util.Arrays;

/**
 * Description 2:
 * Given a 2-d array of "sharpness" values. Find a path from the leftmost column to the rightmost column which has
 * the highest minimum sharpness.
 * Output the highest minimum sharpness. Each move can only move to the top right, right or bottom right grid.
 * Example: 3*3 matrix
 * 5 7 2
 * 7 5 8
 * 9 1 5
 * The path with the highest minimum sharpness is 7-->7-->8, because 7 is the highest minimum value in all the paths.
 */
public class SharpnessValues {
    public int findSharpnessValue(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return -1;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] sharpness = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            sharpness[row][0] = matrix[row][0];
        }

        for (int col = 1; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                //Find the max sharpness from the left, upper left, and lower left path
                int pathPrev = sharpness[row][col - 1];
                if (row > 0) {
                    pathPrev = Math.max(pathPrev, sharpness[row - 1][col - 1]);
                }
                if (row < rows - 1) {
                    pathPrev = Math.max(pathPrev, sharpness[row + 1][col - 1]);
                }
                sharpness[row][col] = Math.min(pathPrev, matrix[row][col]);
            }
        }
        for(int[] row : sharpness) System.out.println(Arrays.toString(row));
        int maxSharpness = Integer.MIN_VALUE;
        for (int row = 0; row < rows; row++) {
            maxSharpness = Math.max(maxSharpness, sharpness[row][cols - 1]);
        }
        return maxSharpness;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1}, {2}, {3}};
        SharpnessValues solver = new SharpnessValues();
        assert solver.findSharpnessValue(matrix) == 3;

        int[][] matrix2 = {{1, 2, 3}};
        assert solver.findSharpnessValue(matrix2) == 1;

        int[][] matrix3 = {{3}};
        assert solver.findSharpnessValue(matrix3) == 3;

        int[][] matrix4 = {{5, 7, 2}, {7, 5, 8}, {9, 1, 5}};
        System.out.println(solver.findSharpnessValue(matrix4));
    }
    //{5, 7, 2},
    //{7, 5, 8},
    //{9, 1, 5}
    /*
    Follow-up:
    If the matrix is very large:
    - Can process the matrix in horizontal strips, minding the boundary of the strip depends on the previous and next strips.
    - Or you can read it column by column each time (many disk seek() because of the way array is stored).
    Helps to have it stored in random access files
    - Or transpose the file: same if read row, output col, many disk seek() when written;
    - if read col, output row, many disk seek() when read
    - we can according to the memory size, each time read a square matrix, and do the transpose of it.
    - Then do the processing row by row.
     */
}