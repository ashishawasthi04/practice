package com.practice;

// A Java program to print topological
// sorting of a DAG

import java.util.*;

// This class represents a directed graph
// using adjacency list representation
class TopologicalSort {
    // No. of vertices
    private final int V;

    // Adjacency List as ArrayList of ArrayList's
    private final ArrayList<Integer>[] adj;

    // Constructor
    TopologicalSort(int v) {
        V = v;
        adj = new ArrayList[v];
        Arrays.fill(adj, new ArrayList<Integer>());
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean[] visited,
                             Stack<Integer> stack) {
        // Mark the current node as visited.
        visited[v] = true;

        // Recur for all the vertices adjacent
        // to this vertex
        for (Integer integer : adj[v]) {
            if (!visited[integer])
                topologicalSortUtil(integer, visited, stack);
        }

        // Push current vertex to stack
        // which stores result
        stack.push(v);
    }

    // The function to do Topological Sort.
    // It uses recursive topologicalSortUtil()
    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited (Defaults to false on declaration)
        boolean[] visited = new boolean[V];

        // Call the recursive helper
        // function to store
        // Topological Sort starting
        // from all vertices one by one
        for (int i = 0; i < V; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);

        // Print contents of stack
        while (!stack.empty())
            System.out.println(stack.pop());
    }

    // Driver code
    public static void main(String[] args) {
        // Create a graph given in the above diagram
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Topological Sort:");
        // Function Call
        g.topologicalSort();
    }
}
// This code is contributed by Aakash Hasija

