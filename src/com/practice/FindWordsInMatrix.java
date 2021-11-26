package com.practice;

import java.util.*;

class Point{
    int x, y, d;
    public Point(int x, int y, int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

//find words in matrix => in any direction => All 8 directions
public class FindWordsInMatrix {
    public static void findWords(char[][] matrix, String[] words){
        Map<Character, List<Point>> map = new HashMap<>();
        for(int i=0; i<matrix.length; i++){
           for(int j=0; j<matrix[0].length; j++){
               Point point = new Point(i, j, 0);
               map.compute(matrix[i][j], (k, v) -> {
                   if(v == null) v = new ArrayList<>();
                   v.add(point);
                   return v;
               });
           }
        }

        for(String word : words){
            List<Point> points = map.get(word.charAt(0));
            if(points == null){
                System.out.println(word + " Not Found");
                continue;
            }
            boolean found = false;
            for(Point point : points){
                found = bfs(matrix, point, word);
                if(found) break;
            }
            System.out.println(word + (found ? " Found" : " Not Found"));
        }
    }

    public static boolean bfs(char[][] matrix, Point point, String word){
        int rows = matrix.length;
        int cols = matrix[0].length;
        //Arrays to for all possible directions
        int[] x = new int[]{1, -1, 0, 0, -1, 1, 1, -1};
        int[] y = new int[]{0, 0, 1, -1, -1, 1, -1, 1};
        boolean[][] visited = new boolean[rows][cols];
        Queue<Point> queue = new LinkedList<>();
        queue.add(point);
        visited[point.x][point.y] = true;
        while(!queue.isEmpty()){
            Point curPoint = queue.poll();
            // Check neighbors
            for(int i=0; i<x.length; i++){
                int newX = curPoint.x + x[i];
                int newY = curPoint.y + y[i];
                if(newX >= 0 && newY >= 0 && newX < rows && newY < cols){
                    if(visited[newX][newY]) continue;
                    if(word.charAt(curPoint.d+1) == matrix[newX][newY]){
                        if(curPoint.d+1 == word.length()-1) return true;
                        visited[newX][newY] = true;
                        queue.add(new Point(newX, newY, curPoint.d+1));
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        char[][] mat = new char[][]{
                {'a', 'b', 'c', 'd', 'e'},
                {'g', 'h', 'i', 'j', 'k'},
                {'l', 'm', 'n', 'o', 'q'},
                {'r', 's', 't', 'u', 'v'},
                {'w', 'x', 'y', 'z', 'a'},
                {'b', 'c', 'd', 'e', 'f'}
        };
        String[] words = new String[]{"aze", "dyxt", "hicefkj", "mhijonu"};
        findWords(mat, words);
    }
}
