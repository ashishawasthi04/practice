package com.practice;

import java.util.*;

public class TravelSubGraphsBFS {
    public static int solution(int totalNodes, String[] edges){
        Set<Integer> visited = new HashSet<>();
        List<Integer>[] adjacentList = new ArrayList[totalNodes+1];
        Arrays.fill(adjacentList, new ArrayList<>());
        for (String edge : edges) {
            String[] nodes = edge.split(" ");
            int first = Integer.parseInt(nodes[0]);
            Integer second = Integer.parseInt(nodes[1]);
            adjacentList[first].add(second);
        }

        int sum = 0;
        for(int i=1; i<=totalNodes; i++){
            if(!visited.contains(i)){
                int count = bfs(visited, adjacentList, i);
                sum += count;
                System.out.println("Count for node " + i + " is: " + count);
                //System.out.println("Visited size for " + i + " is: " + visited.size());
                System.out.println("SQRT: " + Math.sqrt(sum));
                System.out.println("Ceil: " + Math.ceil(Math.sqrt(sum)));
            }
        }
        return sum;
    }

    public static int bfs(Set<Integer> visited, List<Integer>[] adjacentList, Integer node){
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        visited.add(node);
        queue.add(node);
        while(!queue.isEmpty()){
            int curNode = queue.poll();
            count++;
            for(int i : adjacentList[curNode]){
                if(!visited.contains(i)){
                    visited.add(i);
                    queue.add(i);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int totalNodes = 8;
        String[] edges = new String[]{"1 2", "2 3", "1 4", "3 4"};
        solution(totalNodes, edges);
    }
}
