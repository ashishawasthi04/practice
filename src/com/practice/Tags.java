package com.practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tags {
    //Coding Question: you are given the start and end associated with a tag in form of list and a string. Put these tags in xml format
    //Example: tags = [[“0”, “3”, “a”], [“4”,”7”, “b”], [“5”, “10”, “c”] and string = “Hello world”
    //You need to output “”.
    /*
     * [a]hell[/a]
     *    [b]o
     *       [c] wo
     *    [/b]
     *  [c]rld[/c] */
    class Node {
        int pos;
        String tag;
        boolean isEnd = false;

        public Node(int pos, String tag, boolean isEnd) {
            this.pos = pos;
            this.tag = tag;
            this.isEnd = isEnd;
        }
    }

    public static void main(String[] args) {
        Tags tags = new Tags();
        List<String[]> input = new ArrayList<>();
        input.add(new String[]{"0", "3", "a"});
        input.add(new String[]{"4", "7", "b"});
        input.add(new String[]{"5", "10", "c"});
        String inputString = "Hello world";
        System.out.println(tags.solve(input, inputString));
    }

    public String solve(List<String[]> tags, String input) {
        Comparator<Node> comp = Comparator.comparingInt(node -> node.pos);
        List<Node> nodes = new ArrayList<>();
        tags.stream().forEach(tag -> {
            nodes.add(new Node(Integer.parseInt(tag[0]), "<" + tag[2] + ">", false));
            nodes.add(new Node(Integer.parseInt(tag[1]), "<" + tag[2] + "/>", true));
        });
        nodes.sort(Comparator.comparingInt(node -> -node.pos));
        StringBuilder sb = new StringBuilder(input);

        nodes.forEach(node -> sb.insert(node.pos + (node.isEnd ? 1 : 0), node.tag));
        return sb.toString();
    }
}
