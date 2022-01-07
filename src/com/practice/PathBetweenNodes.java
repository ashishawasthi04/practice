package com.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 Question 1: Given two integer nodes in a binary tree,
 find the shortest path from the first to the second node in string format,
 using the words “up”, “left”, and “right”.

 Input:

 Nodes: 4, 6

 Tree:

 8
 /   \
 4     6

 {“up”, “right”}


 2-8  -> up, up
 root -
 8
 /   \
 4     6
 | \
 2. 3


 8
 /\
 3  6
 / \
 4   5


 bfs - 4(up), 6(up)

 dfs -

 Node {
 left
 right
 val
 }

 find a, find b
 generate path from a to b


 */
public class PathBetweenNodes {
    static class Node {
        Node left;
        Node right;
        int val;
        public Node(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(6);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        findPath(root, 4, 6);
    }

    public static void findPath(Node root, int start, int end){
        LinkedList<String> firstPath = new LinkedList<>();
        LinkedList<String> secondPath = new LinkedList<>();
        buildPath(root, start, firstPath, "");
        buildPath(root, end, secondPath, "");

        int count = 0;
        while(firstPath.get(count).equals(secondPath.get(count))){
            count++;
            if(count >= firstPath.size() || count >= secondPath.size()) break;
        }
        List<String> res = new ArrayList<>();
        while(firstPath.size() != count){
            res.add("Up");
            firstPath.removeLast();
        }

        while(secondPath.size() != count){
            res.add(secondPath.get(count));
            secondPath.remove(count);
        }
        System.out.println(String.join(", ", res));
    }

    public static boolean buildPath(Node node, int val, LinkedList<String> path, String dir){
        if(node == null) return false;
        path.add(dir);
        if(node.val == val
                || buildPath(node.left, val, path, "Left")
                || buildPath(node.right, val, path, "Right")){
            return true;
        }
        path.removeLast();
        return false;
    }
}
