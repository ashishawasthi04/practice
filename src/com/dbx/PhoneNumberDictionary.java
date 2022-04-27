package com.dbx;

import java.util.*;

//Simple implementation:
public class PhoneNumberDictionary {

    private final static String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private static Set<String> dict;

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() < 1) return combinations;

        findCombinations(combinations, new StringBuilder(), digits);

        return combinations;
    }

    public void findCombinations(List<String> combinations, StringBuilder sb, String digits) {
        int i = sb.length();
        if (i == digits.length()) {
            String comb = sb.toString();
            if (isWord(comb))
                combinations.add(comb);
        } else {
            char[] letters = KEYS[digits.charAt(i) - '0'].toCharArray();
            for (char letter : letters) {
                sb.append(letter);
                findCombinations(combinations, sb, digits);
                sb.setLength(i);
            }
        }
    }

    public boolean isWord(String word) {
        return dict.contains(word);
    }

    public static void main(String[] args) {
        dict = new HashSet<>();
        dict.add("drop");
        dict.add("box");
        dict.add("dropbox");

        List<String> combinations = new PhoneNumberDictionary().letterCombinations("3767269");
        for (String comb : combinations) {
            System.out.println(comb);
        }
    }


    //Follow up: Trie Tree
    static class Trie {
        private static class Node {
            Map<Character, Node> children;
            boolean isWord;

            public Node() {
                children = new HashMap<Character, Node>();
                isWord = false;
            }
        }

        private final Node root;

        // Initialize your data structure here.
        public Trie() {
            root = new Node();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                Node temp = curr.children.get(word.charAt(i));
                if (temp == null) {
                    temp = new Node();
                    curr.children.put(word.charAt(i), temp);
                }
                curr = temp;
            }
            curr.isWord = true;
        }

        //Returns if the word is in the trie.
        public boolean search(String word) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                curr = curr.children.get(word.charAt(i));
                if (curr == null) return false;
            }
            return curr.isWord;
        }

        public Node search(Node trieNode, Character c) {
            return trieNode.children.get(c);
        }

        // Returns if there is any word in the trie that starts with the given prefix.
        public boolean startsWith(String prefix) {
            Node curr = root;
            for (int i = 0; i < prefix.length(); i++) {
                curr = curr.children.get(prefix.charAt(i));
                if (curr == null) return false;
            }
            return true;
        }
    }
}
