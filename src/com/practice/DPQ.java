package com.practice;

// Java implementation of Dijkstra's Algorithm
// using Priority Queue

import java.util.*;

public class DPQ {
    private final int[] dist;
    private final Set<Integer> settled;
    private final PriorityQueue<Node> pq;
    private final int V; // Number of vertices
    List<List<Node>> adj;

    public DPQ(int V) {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(V, Comparator.comparingInt(a -> a.cost));
    }

    // Function for Dijkstra's Algorithm
    public void dijkstra(List<List<Node>> adj, int src) {
        this.adj = adj;
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        while (settled.size() != V) {

            // remove the minimum distance node
            // from the priority queue
            int u = pq.remove().node;

            // adding the node whose distance is
            // finalized
            settled.add(u);

            exploreNeighbors(u);
        }
    }

    // Function to process all the neighbours
    // of the passed node
    private void exploreNeighbors(int u) {
        int edgeDistance;
        int newDistance;

        // All the neighbors of v
        List<Node> neighbors = adj.get(u);
        for (Node neighbor : neighbors) {

            // If current node hasn't already been processed
            if (!settled.contains(neighbor.node)) {
                edgeDistance = neighbor.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[neighbor.node])
                    dist[neighbor.node] = newDistance;

                // Add the current node to the queue
                pq.add(new Node(neighbor.node, dist[neighbor.node]));
            }
        }
    }

    // Driver code
    public static void main(String[] arg) {
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<Node>> adj = new ArrayList<>();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        // Inputs for the DPQ graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // Calculate the single source shortest path
        DPQ dpq = new DPQ(V);
        dpq.dijkstra(adj, source);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }
}

// Class to represent a node in the graph
class Node {
    public int node;
    public int cost;

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}