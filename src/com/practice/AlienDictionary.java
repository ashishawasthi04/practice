package com.practice;

import java.util.*;
//https://leetcode.com/problems/alien-dictionary/
public class AlienDictionary {

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();
        String order = ad.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"});
        System.out.println(order);
    }


    List<Integer>[] map = new List[26];
    int[] inOrder = new int[26];

    public String alienOrder(String[] words) {
        if (words.length == 1) return preProcess(words[0]);

        String fullString = String.join("", words);
        String uniqueString = preProcess(fullString);

        for (int i = 0; i < words.length - 1; i++) {
            if (!process(words[i], words[i + 1])) return "";
        }

        //Logic to process the characters in lexicographical order
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == 0) {
                queue.offer(i);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.append((char) (cur + 'a'));
            for (int next : map[cur]) {
                inOrder[next]--;
                if (inOrder[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return res.length() != uniqueString.length() ? "" : res.toString();
    }

    //Preprocess combined string of words and return string of unique characters
    public String preProcess(String fullString) {
        Arrays.fill(inOrder, -1);
        Set<Character> set = new HashSet<>();
        StringBuilder uniqueStr = new StringBuilder();
        for (char c : fullString.toCharArray()) {
            if (!set.contains(c)) {
                set.add(c);
                uniqueStr.append(c);
                inOrder[c - 'a'] = 0;
                map[c - 'a'] = new ArrayList<>();
            }
        }
        return uniqueStr.toString();
    }

    //Compare a pair of words in sequence and maintain the inOrder count and dependency list
    public boolean process(String first, String second) {
        if (first.equals(second)) return true;
        int minLen = Math.min(first.length(), second.length());
        int idx = -1;
        boolean misMatch = false;
        while (++idx < minLen) {
            char fChar = first.charAt(idx);
            char sChar = second.charAt(idx);
            if (fChar != sChar) {
                inOrder[sChar - 'a']++;
                map[fChar - 'a'].add(sChar - 'a');
                misMatch = true;
                break;
            }
        }
        //If mismatch found or and first string is smaller than second string
        return misMatch || first.length() <= second.length();
    }
}

