package com.practice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class ShortestCellPath {

    static int shortestCellPath(int[][] grid, int sr, int sc, int tr, int tc) {
        // your code goes here
        if(grid == null) return 0;
        int res = Integer.MAX_VALUE;
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new int[]{sr, sc, 0});

        while(!queue.isEmpty()){
            int[] point = queue.poll();
            if(point[0] == tr && point[1] == tc){
                res = Math.min(res, point[2]);
                continue;
            }
            visited.add(point[0] + "-" + point[1]);
            for(int[] dir : dirs){
                int[] neighbor = new int[]{point[0] + dir[0], point[1] + dir[1], point[2] + 1};
                if(isValid(grid, visited, neighbor)){
                    if(!visited.contains(neighbor[0] + "-" + neighbor[1])
                            && grid[neighbor[0]][neighbor[1]] == 1){
                        queue.offer(neighbor);
                    }
                }
            }
        }
        if(res == Integer.MAX_VALUE) return -1;
        return res;
    }

    static boolean isValid(int[][] grid, Set<String> visited, int[] point){
        int rows = grid.length;
        int cols = grid[0].length;
        if(point[0] < 0 || point[0] >= rows || point[1] < 0 || point[1] >= cols){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,1,1,1},
                {0,0,0,1},
                {1,1,1,1}};
        int res = shortestCellPath(grid, 0, 0, 2, 0);
        System.out.println(res);
    }
}

