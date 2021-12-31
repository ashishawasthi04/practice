package com.practice;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/************************** First Problem**********************
While your players are waiting for a game, you've developed a solitaire game for the players to pass the time with.
The player is given an NxM board of tiles from 0 to 9 like this:
  4   4   4   4
  5   5   5   4
  2   5   7   5
The player selects one of these tiles, and that tile will disappear, along with any tiles with the same number that are connected with that tile (up, down, left, or right),
and any tiles with the same number connected with those, and so on. For example, if the 4 in the upper left corner is selected, these five tiles disappear
 >4< >4< >4< >4<
  5   5   5  >4<
  2   5   7   5
If the 5 just below it is selected, these four tiles disappear. Note that tiles are not connected diagonally.
  4   4   4   4
 >5< >5< >5<  4
  2  >5<  7   5
Write a function that, given a grid of tiles and a selected row and column of a tile, returns how many tiles will disappear.
grid1 = [[4, 4, 4, 4],
         [5, 5, 5, 4],
         [2, 5, 7, 5]]

        [[0, 4, 4, 4],
         [5, 4, 5, 4],
         [2, 5, 7, 5]]


disappear(grid1, 0, 0)  => 5
disappear(grid1, 1, 1)  => 4
disappear(grid1, 1, 0)  => 4
 */
public class ConnectedCards {
    static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    public static void main1(String[] args) {
        int[][] grid1 =
                {{4, 4, 4, 4},
                {5, 5, 5, 4},
                {2, 5, 7, 5}};
        int[][] grid2 =
                {{0, 3, 3, 3, 3, 3, 3},
                {0, 1, 1, 1, 1, 1, 3},
                {0, 2, 2, 0, 2, 1, 4},
                {0, 1, 2, 2, 2, 1, 3},
                {0, 1, 1, 1, 1, 1, 3},
                {0, 0, 0, 0, 0, 0, 0}};
        int[][] grid3 = {{0}};
        int[][] grid4 =
                {{1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}};

        Assert.assertEquals(5, disappear(grid1, 0, 0));
        Assert.assertEquals(disappear(grid1, 1, 1), 4);
        Assert.assertEquals(disappear(grid1, 1, 0), 4);
        Assert.assertEquals(disappear(grid2, 0, 0), 12);
        Assert.assertEquals(disappear(grid2, 3, 0), 12);
        Assert.assertEquals(disappear(grid2, 1, 1), 13);
        Assert.assertEquals(disappear(grid2, 2, 2), 6);
        Assert.assertEquals(disappear(grid2, 0, 3), 7);
        Assert.assertEquals(disappear(grid3, 0, 0), 1);
        Assert.assertEquals(disappear(grid4, 0, 0), 9);
    }
    public static int disappear(int[][] grid, int row, int col){
        return disappear(grid, row, col, grid[row][col]);
    }

    //TC: O(N * M)
    //SC: O(N * M)
    public static int disappear(int[][] grid, int row, int col, int val){
        if(!(row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] > 0 && grid[row][col] == val)) return 0;
        int sum = 1;
        grid[row][col] = -1;
        for(int i = 0; i < 4; i++){
            int newRow = row + dir[i][0];
            int newCol = col + dir[i][1];
            sum += disappear(grid, newRow, newCol, val);
        }
        return sum;
    }

    /************************** Second Problem**********************
    11222 -> true
    21212 -> true
    1122233 -> false (more than one pair)
    1 -> false
    11111222 -> true, pair - 1s, triple- 1s & 2
    12211211  == 11 111 222 -> true, pair - 1s, triple- 1s & 2
     */
    public static void main(String[] args) {
        Assert.assertTrue(validInput("11222"));
        Assert.assertTrue(validInput("21212"));
        Assert.assertFalse(validInput("1122233"));
        Assert.assertFalse(validInput("1"));
        Assert.assertTrue(validInput("11111222"));
        Assert.assertTrue(validInput("12211211"));
    }

    public static boolean validInput(String input){
        Map<Integer, Integer> map = new HashMap<>();
        input.chars().forEach(c -> map.merge(c, 1, Integer::sum));
        boolean pairFound = false;
        for(Integer count : map.values()){
            if((count%3 == 2 && pairFound) || (count%3 != 0 && count%3 != 2)) return false;
            if(count%3 == 2) pairFound = true;
        }
        return pairFound;
    }
}
