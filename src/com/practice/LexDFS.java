package com.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


class Result {
    public static List<Integer> lexdfs(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> r, List<Integer> v) {
        List<Integer> result = new ArrayList<>();
        List<Integer>[] adjacentList = new ArrayList[gNodes];
        Map<Integer, Integer>[] calculatedRank = new HashMap[gNodes];

        IntStream.range(0, gNodes).forEach(i -> {
            //Initialize adjacent list
            adjacentList[i] = new ArrayList<>();
        });

        // Set graph edges
        for (int i = 0; i < gFrom.size(); i++) {
            int from = gFrom.get(i);
            int to = gTo.get(i);
            adjacentList[from].add(to);
            adjacentList[to].add(from);
        }

        // Sort child nodes in reverse order
        for (int i = 0; i < gNodes; i++) {
            Collections.sort(adjacentList[i], Collections.reverseOrder());
        }

        // Prepare distinct list of query start node
        List<Integer> srcDistinct = r.stream().distinct().collect(Collectors.toList());
        // Precalculate position of all connected nodes from source node (i.e. Query start node)
        for (int src : srcDistinct) {
            calculatedRank[src] = dfs(src, adjacentList);
        }

        // Calculate rank for every source destination nodes in the graph
        for (int i = 0; i < r.size(); i++) {
            int rank = calculatedRank[r.get(i)].get(v.get(i));
            result.add(rank);
        }
        // Return result
        return result;
    }

    // This method returns ranks of all reachable nodes from given start node
    public static Map<Integer, Integer> dfs(int start, List<Integer>[] adjacentList) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] isVisited = new boolean[adjacentList.length];
        stack.push(start);
        int counter = 0;
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if(isVisited[current]) continue;
            isVisited[current] = true;
            result.put(current, counter++);
            for (int dest : adjacentList[current]) {
                if (!isVisited[dest])
                    stack.push(dest);
            }
        }
        return result;
    }

}


public class LexDFS {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromTo[0]));
                gTo.add(Integer.parseInt(gFromTo[1]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int rCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> r = IntStream.range(0, rCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int vCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> v = IntStream.range(0, vCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.lexdfs(gNodes, gFrom, gTo, r, v);

//        bufferedWriter.write(
//                result.stream()
//                        .map(Object::toString)
//                        .collect(joining("\n"))
//                        + "\n"
//        );

        bufferedReader.close();
        //bufferedWriter.close();
    }
}

