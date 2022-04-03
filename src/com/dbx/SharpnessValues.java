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
/**
 * Description 2:
 * Given a 2-d array of "sharpness" values. Find a path from the leftmost column to the rightmost column which has
 * the highest minimum sharpness.
 * Output the highest minimum sharpness. Each move can only move to the top right, right or bottom right grid.
 * Example: 3*3 matrix
 * 5 7 2
 * 7 5 8
 * 9 1 5
 The path with the highest minimum sharpness is 7-->7-->8, because 7 is the highest minimum value in all the paths.
 */
public class SharpnessValues {
    public static void main(String[] args) {
        int[][] image = {{5,7,2}, {7,5,8}, {9,1,5}};
        System.out.println(highestMinSharpness(image));
    }
    public static int highestMinSharpness(int[][] image) {
        int row = image.length;
        int col = image[0].length;
        int[] dp = new int[row];
        for (int i = 0; i < row; i++) {
            dp[i] = image[i][0];
        }
        int temp = 0; // dp[j - 1] is already updated, so set old value to temp
        for (int i = 1; i < col; i++) { // one column at each time
            for (int j = 0; j < row; j++) {
                int max = Integer.MIN_VALUE;
                if (j > 0) {
                    max = Math.max(temp, dp[j]);
                }
                if (j + 1 < row) {
                    max = Math.max(dp[j + 1], max);
                }
                temp = dp[j]; // dp[j] will be overridden, so save it for next loop
                dp[j] = Math.min(max, image[j][i]);
            }
            //PrintArray.printArray(dp);
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;
    }
    // Follow up - what if the 2D array is 1M * 1M?
}