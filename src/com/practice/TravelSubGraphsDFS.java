package com.practice;

import java.util.*;

public class TravelSubGraphsDFS {
    public static int solution(int totalNodes, String[] edges){
        Set<Integer> visited = new HashSet<>();
        List<Integer>[] adjacentList = new ArrayList[totalNodes+1];
        Arrays.fill(adjacentList, new ArrayList<>());
        for(int i=0; i<edges.length; i++){
            String[] nodes = edges[i].split(" ");
            Integer first = Integer.parseInt(nodes[0]);
            Integer second = Integer.parseInt(nodes[1]);
            adjacentList[first].add(second);
        }
        int sum = 0;
        for(int i=1; i<=totalNodes; i++){
            if(!visited.contains(i)){
                int count = dfs(visited, adjacentList, i);
                sum += count;
                System.out.println("Count for node " + i + " is: " + count);
                //System.out.println("Visited size for " + i + " is: " + visited.size());
                System.out.println("SQRT: " + Math.sqrt(sum));
                System.out.println("Ceil: " + Math.ceil(Math.sqrt(sum)));
            }
        }
        return sum;
    }

    public static int dfs(Set<Integer> visited, List<Integer>[] adjacentList, Integer node){
        int sum = 0;
        visited.add(node);
        sum++;
        for(int curNode : adjacentList[node]){
            if(!visited.contains(curNode)) {
                sum += dfs(visited, adjacentList, curNode);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int totalNodes = 8;
        String[] edges = new String[]{"1 2", "2 3", "1 4", "3 4"};
        solution(totalNodes, edges);
    }
}
